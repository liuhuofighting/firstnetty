package websocket;

import java.util.Random;
import java.util.Scanner;

public class CyclicRedundancyCheck {

    public String toBinary(String str){
//        String str = "王雪";
        char[] strChar=str.toCharArray();
        String result="";
        for(int i=0;i<strChar.length;i++){
            result +=Integer.toBinaryString(strChar[i]);
        }
//        System.out.println(result);
        return  result;
    }
    // 生成码
    private int[] generatingCode;


    // 设置生成码
    public void setGeneratingCode(String str) {
        //将生成码字符串转换为int数组
        generatingCode = stringToArray(str);
    }

    // 获取帧检验序列
    public String getFCS(String message) {//传进来的是被除数
        for (int i = 0; i < generatingCode.length - 1; i++) {
            //往后面加几个0（长度是比generatingCode的长度减1）
            message += "0";
        }
        return getRemainder(stringToArray(message));
    }

    // 生成新的数据报
    public String setChu(String chu) {//传进来的是被除数
        return getRemainder(stringToArray(chu));
    }


    // 将01字符串转换为数组
    public int[] stringToArray(String str) {
        char[] chars = str.toCharArray();
        int[] res = new int[chars.length];
        for (int i = 0; i < chars.length; i++) {
            res[i] = chars[i] - '0';
        }
        return res;
    }

    // 求余数
    public String getRemainder(int[] code) {
        int [] generatingCode={1,0,1,1,1};//原来的版本是没有的
        int len = code.length - generatingCode.length + 1;
        for (int i = 0; i < len; i++) {
            if (code[i] != 0) {
                for (int j = 0; j < generatingCode.length; j++) {
                    code[i + j] ^= generatingCode[j];
                }
            }
        }
        StringBuilder res = new StringBuilder();
        for (int i = len; i < code.length; i++) {
            res.append(code[i]);
        }
        return res.toString();
    }

    //设置突变
    public String setMutation(String data){
        int[] res = stringToArray(data);
        Random rand = new Random();
        int i = rand.nextInt(res.length);
        //突变
        if(res[i]==0){
            res[i]=1;
        }else if(res[i]==1){
            res[i]=0;
        }
        //将数组转换为字符串
        StringBuilder a = new StringBuilder();
        for (int j = 0; j < res.length; j++) {
            a.append(res[j]);
        }
        return a.toString();
    }

    //判断余数是否为0
    public void judge(String data){
        int num = Integer.parseInt(data);
        if(num==0){
            System.out.println("接收的数据无差错！");
        }else{
            System.out.println("接收的数据有差错！");
        }
    }

}

class TestCRC {
    public static String toBinary(String str){
//        String str = "王雪";
        char[] strChar=str.toCharArray();
        String result="";
        for(int i=0;i<strChar.length;i++){
            result +=Integer.toBinaryString(strChar[i]);
        }
//        System.out.println(result);
        return  result;
    }

    public static void main(String[] args) {
/*        String input = "0203";
        CRC32 crc = new CRC32();
        crc.update(input.getBytes());
        byte[] s1=input.getBytes();

        System.out.println("CRC32:"+crc.getValue());
        byte[] s2=longToBytes(crc.getValue());
//        byte[] s3=new byte[200];
        byte[] bytes = unitByteArray(s1, s2);
        System.out.println(bytes);
        crc.update(bytes);
        System.out.println(crc.getValue());*/

        CyclicRedundancyCheck crc = new CyclicRedundancyCheck();
        Scanner sc = new Scanner(System.in);

        System.out.print("请输入多项式系数：");
       // String str = sc.next();
        crc.setGeneratingCode("10111");

        System.out.print("请输入二进制数据：");
//        String bei = sc.next();
        String result=toBinary("0230");
        System.out.println("转化的二进制数为："+result);
        String FCS = crc.getFCS(result);
        System.out.print("经过模2除法得到的冗余码为：");
        System.out.println(FCS);

        String data = result+crc.getFCS(result);
        System.out.println("生成新的数据包为：   "+data);
        crc.setChu(data);

       /* 设置突变*/
//        String data2 = crc.setMutation(data);
//        System.out.println("发生突变后的数据包为："+data2);


        System.out.print("经过模2除法得到的余数为：");
        System.out.println(crc.setChu(data));

        crc.judge(crc.setChu(data));

    }
}