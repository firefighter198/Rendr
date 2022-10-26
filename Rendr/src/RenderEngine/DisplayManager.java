package RenderEngine;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.*;

public class DisplayManager
{
    private static final int width = 1280, height = 700;
    private static final int fps = 60;

    //creates a new display with openGL viewport in it
    public static void createDisplay(String title, boolean vSync)
    {
        ContextAttribs attribs = new ContextAttribs(3, 2)
                .withForwardCompatible(true).withProfileCore(true);

        try
        {
            Display.setDisplayMode(new DisplayMode(width, height));
            Display.create(new PixelFormat(), attribs);
            Display.setVSyncEnabled(vSync);
            Display.setTitle(title);
            Display.setResizable(true);
        } catch (LWJGLException e)
        {
            e.printStackTrace();
        }

        GL11.glViewport(0, 0, width, height);
    }

    //updates the display (limited to the given fps)
    public static void updateDisplay()
    {
        if(Display.wasResized())
        {
            GL11.glViewport(0, 0, Display.getWidth(), Display.getHeight());
        }
        Display.sync(fps);
        Display.update();
    }

    //destroys the display
    public static void closeDisplay()
    {
        Display.destroy();
    }

    public static void setFullScreen(boolean fullScreen)
    {
        if(fullScreen)
        {
            try
            {
                Display.setDisplayModeAndFullscreen(Display.getDesktopDisplayMode());
                GL11.glViewport(0, 0, Display.getWidth(), Display.getHeight());
            } catch (LWJGLException e)
            {
                throw new RuntimeException(e);
            }
        }
        else
        {
            try
            {
                Display.setFullscreen(false);
            } catch (LWJGLException e)
            {
                throw new RuntimeException(e);
            }
        }
    }

    public static boolean isFullScreen()
    {
        return Display.isFullscreen();
    }
}
