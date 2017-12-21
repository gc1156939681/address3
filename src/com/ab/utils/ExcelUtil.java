package com.ab.utils;

/**
 * Created by guocui on 2017/10/18.
 */
import com.ab.model.User;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ExcelUtil {
    private POIFSFileSystem fs;//文件系统
    private HSSFWorkbook wb;//工作簿
    private HSSFSheet sheet;//工作表
    private HSSFRow row;//行

    /**
     * 读取excel表头内容
     *
     * @param is
     * @return
     */
    /*
    public String[] readExcelTitle(InputStream is) {
        try {
            fs = new POIFSFileSystem(is); //文件系统
            wb = new HSSFWorkbook(fs);//工作簿
        } catch (IOException e) {
            e.printStackTrace();//在命令行打印异常信息在程序中出错的位置及原因.
        }
        sheet = wb.getSheetAt(0);//getSheetAt 的参数应该是索引， 引用第几个sheet；
        // getSheet的参数应该是sheet的名称，  获取具体名称的sheet。
        row = sheet.getRow(0);
        int colNum = row.getPhysicalNumberOfCells();//getPhysicalNumberOfCells 是获取不为空的列个数。
        System.out.println("colNum:" + colNum);


        //读取表头内容
        String[] title = new String[colNum];
        for (int i = 0; i < colNum; i++) {
            title[i] = row.getCell((short) i).getRichStringCellValue().getString().trim();
        }
        return title;
    }
    */


    /**
     * 读取Excel数据内容
     *
     * @param is
     * @return Map 包含单元格数据内容的Map对象
     */

    public Map<Integer, User> readExcelContent(InputStream is) {
        Map<Integer, User> content = new HashMap<>();
        //创建一个叫做map的Map对象，把HashMap赋给Map，
        // 因为HashMap是Map的实现类。
        //hashmap的key值类型必须是String，hashmap的value值类型必须是String
        // <>内的内容表示对这个map对象进行泛型约束，只有满足泛型约束才可以放入这个map对象中。
        try {
            fs = new POIFSFileSystem(is); //文件系统
            wb = new HSSFWorkbook(fs);   //工作簿
        } catch (IOException e) {
            e.printStackTrace();
        }
        sheet = wb.getSheetAt(0);
        int rowNum = sheet.getLastRowNum();
        row = sheet.getRow(0);
        for (int i = 1; i <= rowNum; i++) {
            row = sheet.getRow(i);
            String name = row.getCell(0).getRichStringCellValue().getString().trim();
            String phoneNumber = row.getCell(1).getRichStringCellValue().getString().trim();
            String profile = row.getCell(2).getRichStringCellValue().getString().trim();
            String info = row.getCell(3).getRichStringCellValue().getString().trim();
            User user = new User(name, phoneNumber, profile, info);
            content.put(i, user);
        }
        return content;
    }

    public void writeExcel(File file, User user) {
        try {
            fs = new POIFSFileSystem(file);//工作簿
            wb = new HSSFWorkbook(fs);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sheet = wb.getSheetAt(0);  //工作表 = 工作簿
        int rowNum = sheet.getLastRowNum();
        Row row = sheet.createRow(rowNum + 1);
        Cell cell = row.createCell(0);
        cell.setCellValue(user.getName());
        Cell cell1 = row.createCell(1);
        cell1.setCellValue(user.getPhoneNumber());
        Cell cell2 = row.createCell(2);
        cell2.setCellValue(user.getProfile());
        Cell cell3 = row.createCell(3);
        cell3.setCellValue(user.getInfo());
        OutputStream out = null;
        try {
            out = new FileOutputStream(file);
            wb.write(out);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 先导出工作表，得到总共的行数
     *
     * @param file
     * @param user
     */

    public void modify(File file, User user) {
        try {
            fs = new POIFSFileSystem(file);
            wb = new HSSFWorkbook(fs);
        } catch (IOException e) {
            e.printStackTrace();
        }
        sheet = wb.getSheetAt(0);
        int rowNum = sheet.getLastRowNum();
        for (int i = 1; i <= rowNum; i++) {
            Row row = sheet.getRow(i);//得到每一行
            if (row.getCell(0).getRichStringCellValue().getString().trim().equals(user.getName().trim())) {
                //找到了与姓名匹配的单元格内容

                //但是下面两个修改只是在单元格的内存当中进行修改，只是一个临时性的调试语句
                row.getCell(1).setCellValue(user.getPhoneNumber());//修改电话号码
                row.getCell(3).setCellValue(user.getInfo());//修改个人备注
                break;
            }
        }

        //通过输出流重新把Excel表格写一下
        OutputStream out = null;
        try {
            out = new FileOutputStream(file);
            wb.write(out);
            //out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
        
        /*
        try{
            out = new FileOutputStream(file);
            wb.write(out);
            out.close();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        */


/*
    public static void main(String[] args) throws Exception {
        String path = System.getProperty("user.dir");
        File file = new File(path + "/src/address.xls");
        InputStream is = new FileInputStream(file);
        ExcelUtil excelUtil = new ExcelUtil();

        //User user=new User("test","121212","11.png","erererr");
        // excelUtil.writeExcel(file,user);

        Map<Integer, User> map = excelUtil.readExcelContent(is);
        System.out.println("\n获得Excel表格的内容");
        for (int i = 1; i <= map.size(); i++) {
            System.out.println(map.get(i));
        }
    }
    */



