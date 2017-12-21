package com.ab.frame;

import com.ab.model.User;
import com.ab.utils.ExcelUtil;
import com.ab.utils.ImgUtil;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * Created by guocui on 2017/10/19.
 */
public class PersonFrame extends JFrame{
    private JPanel mainPanel;
    private JButton returnButton;
    private JButton modifyButton;
    private JLabel profileLabel;
    private JLabel nameLabel;
    private JLabel phoneNumberLabel;
    private JTextField phoneNumberField;
    private JTextField infoField;
    private JButton confirmButton;
    private User user;

    public PersonFrame(User user) {
        this.user = user; //接收主界面传来的user对象
        setTitle("个人信息");
        setSize(400,700);
        setUndecorated(true);
        nameLabel.setText(user.getName());
        phoneNumberField.setText(user.getPhoneNumber());
        infoField.setText(user.getInfo());
        String path = System.getProperty("user.dir") +"/src/profile/" +user.getProfile();//取得该用户头像所在路径
        byte[] b = null;
        try{
            b = ImgUtil.getImageByte(path); //得到了该头像对应的字节数组
        }catch (IOException e){
            e.printStackTrace();
        }
        profileLabel.setIcon(new ImageIcon(b));
        add(mainPanel);
        setLocationRelativeTo(null);
        setVisible(true);

        modifyButton.addActionListener(new ActionListener() {
            @Override
            //激活组件
            public void actionPerformed(ActionEvent e) {
                phoneNumberField.setEditable(true);
                infoField.setEditable(true);
                confirmButton.setEnabled(true);
            }
        });
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               // String name =nameLabel.getText();
                //PersonFrame.this.user.setName(name);
                String phoneNumber =phoneNumberField.getText();
                String info =infoField.getText();
                PersonFrame.this.user.setPhoneNumber(phoneNumber); //重新修改电话号码
                PersonFrame.this.user.setInfo(info);//重新修改个人信息
                ExcelUtil excelUtil = new ExcelUtil();
                File file = new File(System.getProperty("user.dir") +"/src/address.xls");
                excelUtil.modify(file,PersonFrame.this.user);
                JOptionPane.showMessageDialog(null,"修改成功");
                PersonFrame.this.dispose();
            }
        });
        returnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PersonFrame.this.dispose();//点击返回就会返回主界面
            }
        });
    }

}