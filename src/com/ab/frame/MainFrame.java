package com.ab.frame;

import com.ab.model.User;
import com.ab.utils.ExcelUtil;
import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.border.StandardBorderPainter;
import org.jvnet.substance.button.ClassicButtonShaper;
import org.jvnet.substance.painter.SimplisticGradientPainter;
import org.jvnet.substance.skin.BusinessSkin;
import org.jvnet.substance.theme.SubstanceTerracottaTheme;
import org.jvnet.substance.watermark.SubstanceBubblesWatermark;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;

/**
 * Created by guocui on 2017/10/18.
 */
public class MainFrame {
    private JPanel mainPanel;
    private JLabel addressLabel;
    private JLabel addLabel;
    private JTextField searchField;
    private JLabel profileLabel;
    private JLabel nameFiled;
    private JLabel phoneNumberField;
    private JList addressList;
    private JPanel meauPanel;
    private JLabel iconLabel1;
    private JLabel iconLabel2;
    private JLabel iconLabel3;
    private JLabel refreshLabel;
    private Map<Integer, User> map;

    public MainFrame() {
       // DefaultListModel<String> model = new DefaultListModel<>();
        //model.addElement("计算机学院");
     //   model.addElement("机械工程学院");
        //model.addElement("电气工程学院");
       // model.addElement("艺术设计学院");
        //以下代码将从Excel中读取数据，并取出姓名单元格的内容，赋值给model
        //addressList.setModel(model); //对list设置数据模型
        dataInit();

        addLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new AddFrame();
            }
        });
        refreshLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
               MainFrame.this.dataInit();//调用数据初始化方法，刷新数据
            }
        });
    }

    public void dataInit() {
        File file = new File(System.getProperty("user.dir") +"/src/address.xls");
        InputStream is = null;
        try {
            is = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            //e.printStackTrace();
            JOptionPane.showMessageDialog(null,"未找到文件");//弹出的窗口
        }
        ExcelUtil excelReader = new ExcelUtil();
        //map = excelReader.readExcelContent(is);//通过输入流，得到map数据
        map = excelReader.readExcelContent(is);
        DefaultListModel<String> model = new DefaultListModel<>();
        for (int i = 1; i <= map.size(); i++) {
            model.addElement(map.get(i).getName());
        }
        addressList.setModel(model);
        addressList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting() == true){
                    int i = addressList.getSelectedIndex();
                    User user = map.get(i+1);
                    new PersonFrame(user);
                }
            }
        });

    }

    public static void main(String[] args) throws Exception{
        UIManager.setLookAndFeel(new SubstanceLookAndFeel());
        JFrame.setDefaultLookAndFeelDecorated(true);
        //SubstanceLookAndFeel.setCurrentTheme(new SubstanceTerracottaTheme());
        //SubstanceLookAndFeel.setSkin(new BusinessSkin());
        //SubstanceLookAndFeel.setCurrentButtonShaper(new ClassicButtonShaper());
        SubstanceLookAndFeel.setCurrentWatermark(new SubstanceBubblesWatermark());
        SubstanceLookAndFeel.setCurrentBorderPainter(new StandardBorderPainter());
        SubstanceLookAndFeel.setCurrentGradientPainter(new SimplisticGradientPainter());

        JFrame frame = new JFrame("MainFrame");
        frame.setContentPane(new MainFrame().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}