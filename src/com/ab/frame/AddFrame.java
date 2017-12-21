package com.ab.frame;

import com.ab.model.User;
import com.ab.utils.ExcelUtil;
import com.ab.utils.ImgUtil;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by guocui on 2017/10/18.
 */
public class AddFrame extends JFrame{
    private JPanel mainPanel;
    private JTextField nameField;
    private JTextField phoneNumberField;
    private JTextField infoField;
    private JButton saveButton;
    private JLabel profileLabel;
    private byte[] b;

    public AddFrame(){
        setTitle("新增联系人");
        setSize(400,700);
        this.getContentPane().add(mainPanel);
        setLocationRelativeTo(null);
        setVisible(true);

        profileLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFileChooser chooser = new JFileChooser();
                 //chooser.setMultiSelectionEnabled(true);
                int result = chooser.showOpenDialog(AddFrame.this);  //内部匿名类
                if (result == JFileChooser.APPROVE_OPTION){ ///正常选择一个文件
                    File file = chooser.getSelectedFile();  //获得用户选择文件（非空）
                    if (file != null){
                        String fileName = file.getAbsolutePath(); // 获得用户选择的图片的完整绝对路径
                        try {
                            b = ImgUtil.getImageByte(fileName);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                        profileLabel.setIcon(new ImageIcon(b)); //更新图片
                    }
                }
            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String phoneNumber = phoneNumberField.getText();
                String info = infoField.getText();
                String profileName = UUID.randomUUID().toString()+".png";
                String path = System.getProperty("user.dir");
                //File file = new File(path +"/src/profile/" +profileName);
                File file = new File(path + "/src/profile/" + profileName);
                try {
                    ImgUtil. saveImg(file,b);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                User user = new User(name,phoneNumber,profileName,info);//封装成User对象
                //JOptionPane.showMessageDialog(null,user);
                //以下代码将user对象写入Excel文件
                ExcelUtil excelUtil = new ExcelUtil();
                File file1 = new File(path + "/src/address.xls");
                excelUtil.writeExcel(file1,user);
                dispose();
            }
        });
    }
}