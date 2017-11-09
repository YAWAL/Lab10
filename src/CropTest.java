import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

class CropTest extends Frame {
    private Image img, cropimg, replimg, averimg;

    CropTest(String s) {
        super(s);
// 1. Створюємо зображення — обєкт класу Image 
        img = getToolkit().getImage("Lab10/src/javalogo52x88.gif");
// 2. Створюємо обєкти-фільтри: 
// а) виділяємо лівий верхній кут розміром 30x30 
        CropImageFilter crp = new CropImageFilter(0, 0, 30, 30);
// б) збільшуємо зображення в два рази простим методом 
        ReplicateScaleFilter rsf = new ReplicateScaleFilter(104, 176);
// в) збільшуємо зображення в два рази з усредненням 
        AreaAveragingScaleFilter asf = new AreaAveragingScaleFilter(104, 176);
// 3. Створюємо зміну зображення 
        cropimg = createImage(new FilteredImageSource(img.getSource(), crp));
        replimg = createImage(new FilteredImageSource(img.getSource(), rsf));
        averimg = createImage(new FilteredImageSource(img.getSource(), asf));
        setSize(400, 350);
        setVisible(true);
    }

    public void paint(Graphics g) {
        g.drawImage(img, 10, 40, this);
        g.drawImage(cropimg, 150, 40, 100, 100, this);
        g.drawImage(replimg, 10, 150, this);
        g.drawImage(averimg, 150, 150, this);
    }

    public static void main(String[] args) {
        Frame f = new CropTest(" Маштабування");
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev) {
                System.exit(0);
            }
        });
    }
} 