import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Marrieddonthavemoney {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(1133, 744);
        frame.setTitle("โปรแกรมคำนวณภาษีหนึ่งเดียวที่คุณไว้ใจ");
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage("project.jpg"));
        frame.getContentPane().setBackground(Color.decode("#D8F5FF"));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, 1133, 744);

        DisplayGraphics m = new DisplayGraphics();
        m.setBounds(0, 0, 1133, 744);
        layeredPane.add(m, JLayeredPane.DEFAULT_LAYER);

        // รายการลดหย่อนภาษี : ครอบครัว
        JLabel family = new JLabel("รายการลดหย่อนภาษี : ครอบครัว");
        family.setFont(new Font("Tahoma", Font.BOLD, 32));
        family.setBounds(160, 15, 1000, 50);
        layeredPane.add(family, JLayeredPane.PALETTE_LAYER);

        // สถานะสมรส
        JLabel status = new JLabel("สถานะสมรส");
        status.setFont(new Font("Tahoma", Font.BOLD, 24));
        status.setBounds(75, 100, 1000, 50);
        layeredPane.add(status, JLayeredPane.PALETTE_LAYER);

        // ลดหย่อนส่วนบุคคล
        JLabel own = new JLabel("ลดหย่อนส่วนบุคคล");
        own.setFont(new Font("Tahoma", Font.BOLD, 24));
        own.setBounds(540, 100, 1000, 50);
        layeredPane.add(own, JLayeredPane.PALETTE_LAYER);

        // ล็อก ลดหย่อน 60000
        JLabel discount = new JLabel("  60,000");
        discount.setFont(new Font("Tahoma", Font.PLAIN, 24));
        discount.setBounds(540, 155, 300, 45);
        discount.setBackground(Color.LIGHT_GRAY);
        Border border = BorderFactory.createLineBorder(Color.GRAY, 3);
        discount.setBorder(border); // ตั้งกรอบให้ JLabel
        discount.setOpaque(true);
        layeredPane.add(discount, JLayeredPane.PALETTE_LAYER);

        // เลือกสถานะ
        String s1[] = { "  โสด", "  หย่า", "  คู่สมรสมีเงินได้", "  คู่สมรสไม่มีเงินได้" };
        JComboBox chosestatus = new JComboBox(s1);
        chosestatus.setFont(new Font("Tahoma", Font.PLAIN, 25));
        chosestatus.setBounds(75, 155, 300, 45);
        layeredPane.add(chosestatus, JLayeredPane.PALETTE_LAYER);

        // ลดหย่อนบิดา-มารดา (ตนเอง)
        JLabel fathmoth = new JLabel("ลดหย่อนบิดา-มารดา (ตนเอง)");
        fathmoth.setFont(new Font("Tahoma", Font.BOLD, 24));
        fathmoth.setBounds(75, 215, 500, 45);
        layeredPane.add(fathmoth, JLayeredPane.PALETTE_LAYER);

        // ปุ่มกลมพ่อแม่
        JRadioButton father1 = new JRadioButton(" บิดา");
        father1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        father1.setBounds(100, 245, 150, 50);
        father1.setOpaque(false); // ตั้งค่าให้ปุ่มโปร่งใส
        layeredPane.add(father1, JLayeredPane.PALETTE_LAYER);

        JRadioButton mother1 = new JRadioButton(" มารดา");
        mother1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        mother1.setBounds(300, 245, 200, 50);
        mother1.setOpaque(false); // ตั้งค่าให้ปุ่มโปร่งใส
        layeredPane.add(mother1, JLayeredPane.PALETTE_LAYER);

        //คนละ 30,000 บาท (บิดามารดาต้องมีอายุเกิน 60 ปี และมีเงินได้ไม่เกิน 30,000 บาทต่อปี) 
        JLabel condition1 = new JLabel("คนละ 30,000 บาท (บิดามารดาต้องมีอายุเกิน 60 ปี และมีเงินได้ไม่เกิน 30,000 บาทต่อปี)");
        condition1.setFont(new Font("Tahoma", Font.PLAIN, 11));
        condition1.setBounds(75, 275, 1000, 45);
        layeredPane.add(condition1, JLayeredPane.PALETTE_LAYER);

        //(ได้ทั้งบิดา มารดาของตนเอง และคู่สมรส)
        JLabel condition2 = new JLabel("(ได้ทั้งบิดา มารดาของตนเอง และคู่สมรส)");
        condition2.setFont(new Font("Tahoma", Font.PLAIN, 11));
        condition2.setBounds(75, 290, 1000, 45);
        layeredPane.add(condition2, JLayeredPane.PALETTE_LAYER);

        //บุตรคนที่ 1 (เกิดปีใดก็ตาม)
        JLabel son_1 = new JLabel("บุตรคนที่ 1 (เกิดปีใดก็ตาม)");
        son_1.setFont(new Font("Tahoma", Font.BOLD, 24));
        son_1.setBounds(540, 215, 500, 45);
        layeredPane.add(son_1, JLayeredPane.PALETTE_LAYER);

        //ปุ่มเลือกบุตร1
        JRadioButton have = new JRadioButton(" มี");
        JRadioButton donthave = new JRadioButton(" ไม่มี");
        ButtonGroup bg=new ButtonGroup();
        bg.add(donthave);bg.add(have);
        have.setFont(new Font("Tahoma", Font.PLAIN, 20));
        have.setBounds(565, 245, 150, 50);
        have.setOpaque(false); // ตั้งค่าให้ปุ่มโปร่งใส
        layeredPane.add(have, JLayeredPane.PALETTE_LAYER); 
        donthave.setFont(new Font("Tahoma", Font.PLAIN, 20));   
        donthave.setBounds(765, 245, 150, 50);
        donthave.setOpaque(false); // ตั้งค่าให้ปุ่มโปร่งใส
        layeredPane.add(donthave, JLayeredPane.PALETTE_LAYER);
        

        //ลดหย่อน 30,000 บาท
        JLabel discount_30000 = new JLabel("ลดหย่อน 30,000 บาท");
        discount_30000.setFont(new Font("Tahoma", Font.BOLD, 11));
        discount_30000.setBounds(855, 220, 500, 45);
        layeredPane.add(discount_30000, JLayeredPane.PALETTE_LAYER);
        

        //บุตรคนที่ 2 เป็นต้นไป
        JLabel son_2_more= new JLabel("บุตรคนที่ 2 เป็นต้นไป");
        son_2_more.setFont(new Font("Tahoma", Font.BOLD, 24));
        son_2_more.setBounds(75, 325, 500, 45);
        layeredPane.add(son_2_more, JLayeredPane.PALETTE_LAYER);

        //จำนวนบุตรที่เกิดก่อนปี 2561
        JLabel son_before2561= new JLabel("จำนวนบุตรที่เกิดก่อนปี 2561");
        son_before2561.setFont(new Font("Tahoma", Font.PLAIN, 20));
        son_before2561.setBounds(75, 355, 500, 45);
        layeredPane.add(son_before2561, JLayeredPane.PALETTE_LAYER);

        //ลดหย่อน 30,000 บาท
        JLabel discount2_30000 = new JLabel("ลดหย่อน 30,000 บาท ");
        discount2_30000.setFont(new Font("Tahoma", Font.BOLD, 11));
        discount2_30000.setBounds(320, 357, 500, 45);
        layeredPane.add(discount2_30000, JLayeredPane.PALETTE_LAYER);

        //จำนวนบุตรที่เกิดตั้งแต่ปี 2561 เป็นต้นไป
        JLabel son_after2561 = new JLabel("จำนวนบุตรที่เกิดตั้งแต่ปี 2561 เป็นต้นไป");
        son_after2561.setFont(new Font("Tahoma", Font.PLAIN, 20));
        son_after2561.setBounds(540, 355, 1000, 45);
        layeredPane.add(son_after2561, JLayeredPane.PALETTE_LAYER);

        //ลดหย่อน 30,000 บาท
        JLabel discount3_30000 = new JLabel("ลดหย่อน 60,000 บาท");
        discount3_30000.setFont(new Font("Tahoma", Font.BOLD, 11));
        discount3_30000.setBounds(875, 357, 500, 45);
        layeredPane.add(discount3_30000, JLayeredPane.PALETTE_LAYER);

        //textfield ของบุตรก่อน2561
        JTextField text_before = new JTextField(" ");
        text_before.setFont(new Font("Tahoma", Font.PLAIN, 20));
        text_before.setBounds(75, 395, 300, 45);
        layeredPane.add(text_before, JLayeredPane.PALETTE_LAYER);

        //textfield ของบุตรหลัง2561
        JTextField text_after = new JTextField(" ");
        text_after.setFont(new Font("Tahoma", Font.PLAIN, 20));
        text_after.setBounds(540, 395, 300, 45);
        layeredPane.add(text_after, JLayeredPane.PALETTE_LAYER);

        // ลดหย่อนผู้พิการหรือทุพพลภาพ (ไม่มีเงินได้)
        JLabel disabled_no_money = new JLabel("ลดหย่อนผู้พิการหรือทุพพลภาพ (ไม่มีเงินได้)");
        disabled_no_money.setFont(new Font("Tahoma", Font.BOLD, 24));
        disabled_no_money.setBounds(75, 440, 1000, 45);
        layeredPane.add(disabled_no_money, JLayeredPane.PALETTE_LAYER);

        // ปุ่มกลมพ่อแม่ญาติ
        JRadioButton father2 = new JRadioButton(" บิดา");
        father2.setFont(new Font("Tahoma", Font.PLAIN, 20));
        father2.setBounds(100, 470, 150, 50);
        father2.setOpaque(false); // ตั้งค่าให้ปุ่มโปร่งใส
        layeredPane.add(father2, JLayeredPane.PALETTE_LAYER);

        JRadioButton mother2 = new JRadioButton(" มารดา");
        mother2.setFont(new Font("Tahoma", Font.PLAIN, 20));
        mother2.setBounds(300, 470, 200, 50);
        mother2.setOpaque(false); // ตั้งค่าให้ปุ่มโปร่งใส
        layeredPane.add(mother2, JLayeredPane.PALETTE_LAYER);

        JRadioButton cousin1 = new JRadioButton(" ญาติ(พี่ น้อง ฯลฯ)");
        cousin1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        cousin1.setBounds(500, 470, 500, 50);
        cousin1.setOpaque(false); // ตั้งค่าให้ปุ่มโปร่งใส
        layeredPane.add(cousin1, JLayeredPane.PALETTE_LAYER);

        // ลดหย่อนผู้พิการหรือทุพพลภาพ (คู่สมรสไม่มีเงินได้)
        JLabel disabled_married_no_money = new JLabel("ลดหย่อนผู้พิการหรือทุพพลภาพ (คู่สมรสไม่มีเงินได้)");
        disabled_married_no_money.setFont(new Font("Tahoma", Font.BOLD, 24));
        disabled_married_no_money.setBounds(75, 505, 1000, 45);
        layeredPane.add(disabled_married_no_money, JLayeredPane.PALETTE_LAYER);

        // ปุ่มกลมบิดามารดาคู่สมรส
        JRadioButton father3 = new JRadioButton(" บิดา");
        father3.setFont(new Font("Tahoma", Font.PLAIN, 20));
        father3.setBounds(100, 530, 150, 50);
        father3.setOpaque(false); // ตั้งค่าให้ปุ่มโปร่งใส
        layeredPane.add(father3, JLayeredPane.PALETTE_LAYER);

        JRadioButton mother3 = new JRadioButton(" มารดา");
        mother3.setFont(new Font("Tahoma", Font.PLAIN, 20));
        mother3.setBounds(300, 530, 200, 50);
        mother3.setOpaque(false); // ตั้งค่าให้ปุ่มโปร่งใส
        layeredPane.add(mother3, JLayeredPane.PALETTE_LAYER);

        JRadioButton married1 = new JRadioButton(" คู่สมรส");
        married1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        married1.setBounds(500, 530, 500, 50);
        married1.setOpaque(false); // ตั้งค่าให้ปุ่มโปร่งใส
        layeredPane.add(married1, JLayeredPane.PALETTE_LAYER);


        //กรณีบิดา, มารดา, คู่สมรส, บิดาคู่สมรส , มารดาคู่สมรส และบุตรของตนเอง
        JLabel condition3 = new JLabel("กรณีบิดา, มารดา, คู่สมรส, บิดาคู่สมรส , มารดาคู่สมรส และบุตรของตนเอง");
        condition3.setFont(new Font("Tahoma", Font.PLAIN, 11));
        condition3.setBounds(75, 560, 1000, 45);
        layeredPane.add(condition3, JLayeredPane.PALETTE_LAYER);

        //หากเป็นผู้อื่นได้เพียง 1 คนเท่านั้น ลดหย่อนได้คนละ 60,000 บาท (ต้องมีบัตรประจำตัวคนพิการ และไม่มีรายได้)
        JLabel condition4 = new JLabel("หากเป็นผู้อื่นได้เพียง 1 คนเท่านั้น ลดหย่อนได้คนละ 60,000 บาท (ต้องมีบัตรประจำตัวคนพิการ และไม่มีรายได้)");
        condition4.setFont(new Font("Tahoma", Font.PLAIN, 11));
        condition4.setBounds(75, 575, 1000, 45);
        layeredPane.add(condition4, JLayeredPane.PALETTE_LAYER);

        















        // ปุ่ม Home
        JButton button = new JButton("HOME");
        button.setBackground(Color.decode("#80C8CD"));
        button.setFont(new Font("Tahoma", Font.BOLD, 20));
        button.setForeground(Color.WHITE);
        button.setBounds(20, 20, 120, 50);
        Border border1 = BorderFactory.createLineBorder(Color.BLACK, 1);
        button.setBorder(border1);
        button.addActionListener(e -> System.out.println("HOME button clicked"));
        layeredPane.add(button, JLayeredPane.PALETTE_LAYER);

        // ปุ่ม Back
        JButton button1 = new JButton("BACK");
        button1.setBackground(Color.decode("#E5B6B3"));
        button1.setFont(new Font("Tahoma", Font.BOLD, 20));
        button1.setForeground(Color.WHITE);
        button1.setBounds(427, 622, 120, 50);
        button1.setBorder(border1);
        button1.addActionListener(e -> System.out.println("BACK button clicked"));
        layeredPane.add(button1, JLayeredPane.PALETTE_LAYER);

        // ปุ่ม NEXT
        JButton button2 = new JButton("NEXT");
        button2.setBackground(Color.decode("#A5CBB0"));
        button2.setFont(new Font("Tahoma", Font.BOLD, 20));
        button2.setForeground(Color.WHITE);
        button2.setBounds(587, 622, 120, 50);
        button2.setBorder(border1);
        button2.addActionListener(e -> System.out.println("NEXT button clicked"));
        layeredPane.add(button2, JLayeredPane.PALETTE_LAYER);

        frame.add(layeredPane);
        frame.setVisible(true);
    }
}

class DisplayGraphics extends JPanel {
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setOpaque(false);

        Graphics2D g2d = (Graphics2D) g;
        // วาดเส้น
        g2d.setColor(Color.decode("#6390BA"));
        g2d.setStroke(new BasicStroke(7));
        g.drawLine(0, 90, 1133, 90);
        }

}


