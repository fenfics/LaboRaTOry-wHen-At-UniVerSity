import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

public class Marriedhavekids implements ActionListener {
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

        // คนละ 30,000 บาท (บิดามารดาต้องมีอายุเกิน 60 ปี และมีเงินได้ไม่เกิน 30,000
        // บาทต่อปี) 
        JLabel condition1 = new JLabel("คนละ 30,000 บาท (บิดามารดาต้องมีอายุเกิน 60 ปี และมีเงินได้ไม่เกิน 30,000 บาทต่อปี)");
        condition1.setFont(new Font("Tahoma", Font.PLAIN, 11));
        condition1.setBounds(75, 275, 1000, 45);
        layeredPane.add(condition1, JLayeredPane.PALETTE_LAYER);

        // (ได้ทั้งบิดา มารดาของตนเอง และคู่สมรส)
        JLabel condition2 = new JLabel("(ได้ทั้งบิดา มารดาของตนเอง และคู่สมรส)");
        condition2.setFont(new Font("Tahoma", Font.PLAIN, 11));
        condition2.setBounds(75, 290, 1000, 45);
        layeredPane.add(condition2, JLayeredPane.PALETTE_LAYER);

        // บุตรคนที่ 1 (เกิดปีใดก็ตาม)
        JLabel son_1 = new JLabel("บุตรคนที่ 1 (เกิดปีใดก็ตาม)");
        son_1.setFont(new Font("Tahoma", Font.BOLD, 24));
        son_1.setBounds(540, 215, 500, 45);
        layeredPane.add(son_1, JLayeredPane.PALETTE_LAYER);

        // ปุ่มเลือกบุตร1
        JRadioButton have = new JRadioButton(" มี");
        JRadioButton donthave = new JRadioButton(" ไม่มี");
        ButtonGroup bg = new ButtonGroup();
        bg.add(donthave);
        bg.add(have);
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


        // สร้าง JPanel สำหรับเก็บคอมโพเนนต์ที่จะแสดงเมื่อเลือก "มี"
        JPanel havePanel = new JPanel();
        havePanel.setLayout(null);
        havePanel.setBounds(75, 325, 1000, 300);
        havePanel.setOpaque(false);
        havePanel.setVisible(false);
        layeredPane.add(havePanel, JLayeredPane.PALETTE_LAYER);

        // บุตรคนที่ 2 เป็นต้นไป
        JLabel son_2_more = new JLabel("บุตรคนที่ 2 เป็นต้นไป");
        son_2_more.setFont(new Font("Tahoma", Font.BOLD, 24));
        son_2_more.setBounds(0, 0, 500, 45); // เดิม (75, 325) ใน JFrame
        havePanel.add(son_2_more);

        // จำนวนบุตรที่เกิดก่อนปี 2561
        JLabel son_before2561 = new JLabel("จำนวนบุตรที่เกิดก่อนปี 2561");
        son_before2561.setFont(new Font("Tahoma", Font.PLAIN, 20));
        son_before2561.setBounds(0, 30, 500, 45); // เดิม (75, 355) ใน JFrame
        havePanel.add(son_before2561);

        // ลดหย่อน 30,000 บาท
        JLabel discount2_30000 = new JLabel("ลดหย่อน 30,000 บาท ");
        discount2_30000.setFont(new Font("Tahoma", Font.BOLD, 11));
        discount2_30000.setBounds(245, 32, 500, 45); // เดิม (320, 357) ใน JFrame
        havePanel.add(discount2_30000);

        // จำนวนบุตรที่เกิดตั้งแต่ปี 2561 เป็นต้นไป
        JLabel son_after2561 = new JLabel("จำนวนบุตรที่เกิดตั้งแต่ปี 2561 เป็นต้นไป");
        son_after2561.setFont(new Font("Tahoma", Font.PLAIN, 20));
        son_after2561.setBounds(465, 30, 1000, 45); // เดิม (540, 355) ใน JFrame
        havePanel.add(son_after2561);

        // ลดหย่อน 60,000 บาท
        JLabel discount3_60000 = new JLabel("ลดหย่อน 60,000 บาท");
        discount3_60000.setFont(new Font("Tahoma", Font.BOLD, 11));
        discount3_60000.setBounds(800, 32, 500, 45); // เดิม (875, 357) ใน JFrame
        havePanel.add(discount3_60000);

        // textfield ของบุตรก่อน2561
        JTextField text_before = new JTextField(" ");
        text_before.setFont(new Font("Tahoma", Font.PLAIN, 20));
        text_before.setBounds(0, 70, 300, 45); // เดิม (75, 395) ใน JFrame
        havePanel.add(text_before);

        // textfield ของบุตรหลัง2561
        JTextField text_after = new JTextField(" ");
        text_after.setFont(new Font("Tahoma", Font.PLAIN, 20));
        text_after.setBounds(465, 70, 300, 45); // เดิม (540, 395) ใน JFrame
        havePanel.add(text_after);

        // ลดหย่อนผู้พิการหรือทุพพลภาพ (ไม่มีเงินได้)
        JLabel disabled_no_money1 = new JLabel("ลดหย่อนผู้พิการหรือทุพพลภาพ (ไม่มีเงินได้)");
        disabled_no_money1.setFont(new Font("Tahoma", Font.BOLD, 24));
        disabled_no_money1.setBounds(0, 115, 1000, 45);
        havePanel.add(disabled_no_money1);

        // ปุ่มกลมพ่อแม่ญาติ
        JRadioButton father2 = new JRadioButton(" บิดา");
        father2.setFont(new Font("Tahoma", Font.PLAIN, 20));
        father2.setBounds(25, 145, 150, 50);
        father2.setOpaque(false);
        havePanel.add(father2);

        JRadioButton mother2 = new JRadioButton(" มารดา");
        mother2.setFont(new Font("Tahoma", Font.PLAIN, 20));
        mother2.setBounds(225, 145, 200, 50);
        mother2.setOpaque(false);
        havePanel.add(mother2);

        JRadioButton cousin1 = new JRadioButton(" ญาติ(พี่ น้อง ฯลฯ)");
        cousin1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        cousin1.setBounds(425, 145, 500, 50);
        cousin1.setOpaque(false);
        havePanel.add(cousin1);

        // ลดหย่อนผู้พิการหรือทุพพลภาพ (คู่สมรสไม่มีเงินได้)
        JLabel disabled_married_no_money1 = new JLabel("ลดหย่อนผู้พิการหรือทุพพลภาพ (คู่สมรสไม่มีเงินได้)");
        disabled_married_no_money1.setFont(new Font("Tahoma", Font.BOLD, 24));
        disabled_married_no_money1.setBounds(0, 180, 1000, 45);
        havePanel.add(disabled_married_no_money1);

        // ปุ่มกลมบิดามารดาคู่สมรส
        JRadioButton father3 = new JRadioButton(" บิดา");
        father3.setFont(new Font("Tahoma", Font.PLAIN, 20));
        father3.setBounds(25, 210, 150, 50);
        father3.setOpaque(false);
        havePanel.add(father3);

        JRadioButton mother3 = new JRadioButton(" มารดา");
        mother3.setFont(new Font("Tahoma", Font.PLAIN, 20));
        mother3.setBounds(225, 210, 200, 50);
        mother3.setOpaque(false);
        havePanel.add(mother3);

        JRadioButton married1 = new JRadioButton(" คู่สมรส");
        married1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        married1.setBounds(425, 210, 500, 50);
        married1.setOpaque(false);
        havePanel.add(married1);

        // กรณีบิดา, มารดา, คู่สมรส, บิดาคู่สมรส , มารดาคู่สมรส และบุตรของตนเอง
        JLabel condition3 = new JLabel("กรณีบิดา, มารดา, คู่สมรส, บิดาคู่สมรส , มารดาคู่สมรส และบุตรของตนเอง");
        condition3.setFont(new Font("Tahoma", Font.PLAIN, 11));
        condition3.setBounds(0, 235, 1000, 45);
        havePanel.add(condition3);

        // หากเป็นผู้อื่นได้เพียง 1 คนเท่านั้น ลดหย่อนได้คนละ 60,000 บาท
        // (ต้องมีบัตรประจำตัวคนพิการ และไม่มีรายได้)
        JLabel condition4 = new JLabel("หากเป็นผู้อื่นได้เพียง 1 คนเท่านั้น ลดหย่อนได้คนละ 60,000 บาท (ต้องมีบัตรประจำตัวคนพิการ และไม่มีรายได้)");
        condition4.setFont(new Font("Tahoma", Font.PLAIN, 11));
        condition4.setBounds(0, 250, 1000, 45);
        havePanel.add(condition4);


        // สร้าง JPanel สำหรับเก็บคอมโพเนนต์ที่จะแสดงเมื่อเลือก "ไม่มี"
        JPanel donthavePanel = new JPanel();
        donthavePanel.setLayout(null);
        donthavePanel.setBounds(75, 325, 1000, 300);
        donthavePanel.setOpaque(false);
        donthavePanel.setVisible(false);
        layeredPane.add(donthavePanel, JLayeredPane.PALETTE_LAYER);

        // ลดหย่อนผู้พิการหรือทุพพลภาพ (ไม่มีเงินได้)
        JLabel disabled_no_money2 = new JLabel("ลดหย่อนผู้พิการหรือทุพพลภาพ (ไม่มีเงินได้)");
        disabled_no_money2.setFont(new Font("Tahoma", Font.BOLD, 24));
        disabled_no_money2.setBounds(0, 0, 1000, 45); // เดิม (75, 325) ใน JFrame
        donthavePanel.add(disabled_no_money2);

        // ปุ่มกลมพ่อแม่ญาติ
        JRadioButton father4 = new JRadioButton(" บิดา");
        father4.setFont(new Font("Tahoma", Font.PLAIN, 20));
        father4.setBounds(25, 35, 150, 50); // เดิม (100, 360) ใน JFrame
        father4.setOpaque(false);
        donthavePanel.add(father4);

        JRadioButton mother4 = new JRadioButton(" มารดา");
        mother4.setFont(new Font("Tahoma", Font.PLAIN, 20));
        mother4.setBounds(225, 35, 200, 50); // เดิม (300, 360) ใน JFrame
        mother4.setOpaque(false);
        donthavePanel.add(mother4);

        JRadioButton cousin2 = new JRadioButton(" ญาติ(พี่ น้อง ฯลฯ)");
        cousin2.setFont(new Font("Tahoma", Font.PLAIN, 20));
        cousin2.setBounds(425, 35, 500, 50); // เดิม (500, 360) ใน JFrame
        cousin2.setOpaque(false);
        donthavePanel.add(cousin2);

        // ลดหย่อนผู้พิการหรือทุพพลภาพ (คู่สมรสไม่มีเงินได้)
        JLabel disabled_married_no_money2 = new JLabel("ลดหย่อนผู้พิการหรือทุพพลภาพ (คู่สมรสไม่มีเงินได้)");
        disabled_married_no_money2.setFont(new Font("Tahoma", Font.BOLD, 24));
        disabled_married_no_money2.setBounds(0, 75, 1000, 45); // เดิม (75, 400) ใน JFrame
        donthavePanel.add(disabled_married_no_money2);

        // ปุ่มกลมบิดามารดาคู่สมรส
        JRadioButton father5 = new JRadioButton(" บิดา");
        father5.setFont(new Font("Tahoma", Font.PLAIN, 20));
        father5.setBounds(25, 110, 150, 50); // เดิม (100, 430) ใน JFrame
        father5.setOpaque(false);
        donthavePanel.add(father5);

        JRadioButton mother5 = new JRadioButton(" มารดา");
        mother5.setFont(new Font("Tahoma", Font.PLAIN, 20));
        mother5.setBounds(225, 110, 200, 50); // เดิม (300, 430) ใน JFrame
        mother5.setOpaque(false);
        donthavePanel.add(mother5);

        JRadioButton married2 = new JRadioButton(" คู่สมรส");
        married2.setFont(new Font("Tahoma", Font.PLAIN, 20));
        married2.setBounds(425, 110, 500, 50); // เดิม (500, 430) ใน JFrame
        married2.setOpaque(false);
        donthavePanel.add(married2);

        // กรณีบิดา, มารดา, คู่สมรส, บิดาคู่สมรส , มารดาคู่สมรส และบุตรของตนเอง
        JLabel condition5 = new JLabel("กรณีบิดา, มารดา, คู่สมรส, บิดาคู่สมรส , มารดาคู่สมรส และบุตรของตนเอง");
        condition5.setFont(new Font("Tahoma", Font.PLAIN, 11));
        condition5.setBounds(0, 140, 1000, 45);
        donthavePanel.add(condition5);

        // หากเป็นผู้อื่นได้เพียง 1 คนเท่านั้น ลดหย่อนได้คนละ 60,000 บาท
        // (ต้องมีบัตรประจำตัวคนพิการ และไม่มีรายได้)
        JLabel condition6 = new JLabel("หากเป็นผู้อื่นได้เพียง 1 คนเท่านั้น ลดหย่อนได้คนละ 60,000 บาท (ต้องมีบัตรประจำตัวคนพิการ และไม่มีรายได้)");
        condition6.setFont(new Font("Tahoma", Font.PLAIN, 11));
        condition6.setBounds(0, 155, 1000, 45);
        donthavePanel.add(condition6);

        have.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                havePanel.setVisible(true);
                donthavePanel.setVisible(false);

            }
        });

        donthave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                havePanel.setVisible(false);
                donthavePanel.setVisible(true);
            }
        });

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

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
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
