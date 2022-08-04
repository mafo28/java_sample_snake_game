package de.unikl.seda.snake.gui.tools;

import de.unikl.seda.snake.gui.snake.SnakeGameEnvironment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.InvocationTargetException;

/** This container class is used to simply show an empty window, which contains the @{@link SnakeGameEnvironment}.*/
public class GuiContainer extends JDialog {
    private static final int windowHeightOffset = 39;
    private static final int windowWidthOffset = 16;

    public GuiContainer(String dialogTitle, SnakeGameEnvironment drawingEnvironment) {
        super(createTaskbarFrame(dialogTitle));

        // add the drawing environment to this dialog/window
        add(drawingEnvironment);

        // Configure the dialog (size, title)
        setSize(drawingEnvironment.getWidth(), drawingEnvironment.getHeight()); // the offsets compensate for the operating-system dependent border size of the dialog
        setTitle(dialogTitle);
        setLocationRelativeTo(null);
        setResizable(false); // dialog window is not resizable

        // Dispose/Remove object once it has been closed.
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Calling thread will only continue after this Dialog has been closed.
        setModal(true);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                JFrame taskbarFrame = (JFrame) getParent();
                taskbarFrame.setVisible(false);
                taskbarFrame.dispose();
            }
        });
    }

    /** @{@link JDialog JDialogs} do support modal displaying, but don't have a taskbar entry.
     * @{@link JFrame JFrames} do not support modal displaying, but do have a taskbar entry.
     * ==> Using a @{@link JFrame} which hosts the @{@link JDialog} in order to have both.*/
    private static JFrame createTaskbarFrame(String title) {
        JFrame frame = new JFrame(title);
        frame.setUndecorated(true);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        return frame;
    }

    @Override
    public void setSize(int width, int height) {
        super.setSize(width+ windowWidthOffset, height + windowHeightOffset);
    }

    public static void show(String dialogTitle, SnakeGameEnvironment drawingEnvironment) {
        try {
            EventQueue.invokeAndWait(() -> {
                GuiContainer guiContainer = new GuiContainer(dialogTitle, drawingEnvironment);
                drawingEnvironment.setGuiContainer(guiContainer);
                System.out.println("Game session started.");

                guiContainer.setVisible(true);
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        System.out.println("Game session terminated.");
    }
}
