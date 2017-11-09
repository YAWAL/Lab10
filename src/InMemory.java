import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

class InMemory extends Frame {
    private int w = 100, h = 100;
    private int[] pix = new int[w * h];
    private Image img;

    InMemory(String s)

    {
        super(s);
        int i = 0;
        for (int y = 0; y < h; y++) {
            int red = 255 * y / (h - 1);
            for (int x = 0; x < w; x++) {
                int green = 255 * x / (w - 1);
                pix[i++] = (255 << 24) | (red << 16) | (green << 8) | 128;
            }
        }
        setSize(250, 200);
        setVisible(true);
    }

    public void paint(Graphics gr) {
        if (img == null)
            img = createImage(new MemoryImageSource(w, h, pix, 0, w));
        gr.drawImage(img, 50, 50, this);
    }

    public static void main(String[] args) {

        Frame f = new InMemory(" Зображення в памяті");

        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev) {
                System.exit(0);
            }
        });

    }
} 