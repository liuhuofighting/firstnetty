package websocket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Date;
import java.util.zip.CRC32;

public class Server {
    public static void main(String[] args) throws IOException {
        /*
         * 接收客户端发送的数据
         */

        while (true) {
            // 1.创建服务器端DatagramSocket，指定端口
            DatagramSocket socket = new DatagramSocket(8800);
            // 2.创建数据报，用于接收客户端发送的数据
            byte[] data = new byte[1024];// 创建字节数组，指定接收的数据包的大小
            DatagramPacket packet = new DatagramPacket(data, data.length);
            // 3.接收客户端发送的数据
            System.out.println("****服务器端已经启动，等待客户端发送数据");
            socket.receive(packet);// 此方法在接收到数据报之前会一直阻塞
            // 4.读取数据
            String info = new String(data, 0, packet.getLength());
            System.out.println("我是服务器，客户端说：" + info);
            String reply = "";
            switch (info.substring(6, 8))
            {
                case "60":
                    if(info.charAt(8)=='0')   //0代表读取成功
                    {
                        String crc="";
                        reply="QN14"+info.substring(4, 6)+info.substring(6, 8)+info.substring(6, 8)+"00";
                        //"CRC"+"E";
                        CyclicRedundancyCheck cyclicRedundancyCheck=new CyclicRedundancyCheck();
                        String rcontent=cyclicRedundancyCheck.toBinary(reply);   //将数据内容用2进制表示
                        rcontent=rcontent+"0000";                                    //加上校验码
//                        System.out.println(rcontent);
                        int [] num=cyclicRedundancyCheck.stringToArray(rcontent);   //转为整形数组
                        crc=cyclicRedundancyCheck.getRemainder(num);      //再次进行校验为0即为成功
                        reply=reply+crc+"E";
                        String tem=info.substring(13,17);
                        System.out.println(tem);
                        int num1 = Integer.parseInt(tem,16);
                        String tem2=info.substring(17,21);
                        int num2 = Integer.parseInt(tem2,16);
                        String tem3=info.substring(21,25);
                        int num3 = Integer.parseInt(tem3,16);
                        reply=reply+"|"+"总报警个数!"+num1+"!总故障个数!"+num2+"!总探测器个数!"+num3;
                    }
                    else
                    {

                        reply="QN14"+info.substring(4, 6)+info.substring(6, 8)+info.substring(6, 8)+"00";//"CRC"+"E";
                        String crc="";
                        reply="QN14"+info.substring(4, 6)+info.substring(6, 8)+info.substring(6, 8)+"00";
                        //"CRC"+"E";
                        CyclicRedundancyCheck cyclicRedundancyCheck=new CyclicRedundancyCheck();
                        String rcontent=cyclicRedundancyCheck.toBinary(reply);   //将数据内容用2进制表示
                        rcontent=rcontent+"0000";                                    //加上校验码
//                        System.out.println(rcontent);
                        int [] num=cyclicRedundancyCheck.stringToArray(rcontent);   //转为整形数组
                        crc=cyclicRedundancyCheck.getRemainder(num);      //再次进行校验为0即为成功
                        reply=reply+crc+"E";
                        reply=reply+"|"+"无";
                    }
                    break;
                case "70":
                    if(info.charAt(8)=='0')
                    {
                      reply="QN14"+info.substring(4, 6)+info.substring(6, 8)+info.substring(6, 8)+"00";//+"CRC"+"E";
                        String crc="";
                        reply="QN14"+info.substring(4, 6)+info.substring(6, 8)+info.substring(6, 8)+"00";
                        //"CRC"+"E";
                        CyclicRedundancyCheck cyclicRedundancyCheck=new CyclicRedundancyCheck();
                        String rcontent=cyclicRedundancyCheck.toBinary(reply);   //将数据内容用2进制表示
                        rcontent=rcontent+"0000";                                    //加上校验码
//                        System.out.println(rcontent);
                        int [] num=cyclicRedundancyCheck.stringToArray(rcontent);   //转为整形数组
                        crc=cyclicRedundancyCheck.getRemainder(num);      //再次进行校验为0即为成功
                        reply=reply+crc+"E";
                        String tem1=info.substring(13,15);  //防护区1
                        String tem2=info.substring(15,17);  //设备类型1
                        String tem3=info.substring(17,19);  //设备编号1
                        String tem4=info.substring(19,21);  //故障码1
                        String tem5=info.substring(21,23);  //防护区2
                        String tem6=info.substring(23,25);  //设备类型2
                        String tem7=info.substring(25,27);  //设备编号2
                        String tem8=info.substring(27,29);  //故障码2
                        System.out.println(tem2);
//                        int num1 = Integer.parseInt(tem,16);
//                        String tem2=info.substring(17,21);
//                        int num2 = Integer.parseInt(tem2,16);
//                        String tem3=info.substring(21,25);
//                        int num3 = Integer.parseInt(tem3,16);
                        reply=reply+"|"+"防护区1!"+tem1+"!设备类型1!"+tem2+"!设备编号1!"+tem3+"!故障码1!"+tem4+"!";
                        reply=reply+"防护区2!"+tem5+"!设备类型2!"+tem6+"!设备编号2!"+tem7+"!故障码2!"+tem8;
                    }
                    else
                    {
                        reply="QN14"+info.substring(4, 6)+info.substring(6, 8)+info.substring(6, 8)+"00";//"CRC"+"E"
                        String crc="";
                        reply="QN14"+info.substring(4, 6)+info.substring(6, 8)+info.substring(6, 8)+"00";
                        //"CRC"+"E";
                        CyclicRedundancyCheck cyclicRedundancyCheck=new CyclicRedundancyCheck();
                        String rcontent=cyclicRedundancyCheck.toBinary(reply);   //将数据内容用2进制表示
                        rcontent=rcontent+"0000";                                    //加上校验码
//                        System.out.println(rcontent);
                        int [] num=cyclicRedundancyCheck.stringToArray(rcontent);   //转为整形数组
                        crc=cyclicRedundancyCheck.getRemainder(num);      //再次进行校验为0即为成功
                        reply=reply+crc+"E";
                        reply=reply+"|"+"无";
                    }
                    break;
                case "80":
                    if (info.charAt(8) == '0') {
                        reply = "QN14" + info.substring(4, 6) + info.substring(6, 8) + info.substring(6, 8) + "00";// + "CRC" + "E";
                        String crc="";
                        reply="QN14"+info.substring(4, 6)+info.substring(6, 8)+info.substring(6, 8)+"00";
                        //"CRC"+"E";
                        CyclicRedundancyCheck cyclicRedundancyCheck=new CyclicRedundancyCheck();
                        String rcontent=cyclicRedundancyCheck.toBinary(reply);   //将数据内容用2进制表示
                        rcontent=rcontent+"0000";                                    //加上校验码
//                        System.out.println(rcontent);
                        int [] num=cyclicRedundancyCheck.stringToArray(rcontent);   //转为整形数组
                        crc=cyclicRedundancyCheck.getRemainder(num);      //再次进行校验为0即为成功
                        reply=reply+crc+"E";
                        String tem1 = info.substring(13, 15);  //防护区
                        String tem2 = info.substring(15, 17);  //报警级别
                        String tem3 = info.substring(17, 19);  //故障
                        String tem4 = info.substring(19, 21);  //手动模式
                        String tem5 = info.substring(21, 23);  //自动模式
                        String tem6 = info.substring(23, 25);  //手动启动
                        String tem7 = info.substring(25, 27);  //手动急停
                        String tem8 = info.substring(27, 29);  //启动控制
                        String tem9 = info.substring(29, 31);  //延时
                        String tem10 = info.substring(31, 33);  //延时时间
                        String tem11 = info.substring(33, 35); //启动喷洒
                        String tem12 = info.substring(35, 37); //喷洒

                        reply = reply + "|" + "防护区!" + tem1 + "!报警级别!" + tem2 + "!故障!" + tem3 + "!手动模式!" + tem4 + "!自动模式!" + tem5 + "!手动启动!" + tem6 + "!手动急停!" + tem7 + "!启动控制!" + tem8 + "!延时!" + tem9 + "!延时时间!" + tem10 + "!启动喷洒!" + tem11+"!喷洒!" + tem12;
                    } else {
                        reply = "QN14" + info.substring(4, 6) + info.substring(6, 8) + info.substring(6, 8) + "00" ;// "CRC" + "E";
                        String crc="";
                        reply="QN14"+info.substring(4, 6)+info.substring(6, 8)+info.substring(6, 8)+"00";
                        //"CRC"+"E";
                        CyclicRedundancyCheck cyclicRedundancyCheck=new CyclicRedundancyCheck();
                        String rcontent=cyclicRedundancyCheck.toBinary(reply);   //将数据内容用2进制表示
                        rcontent=rcontent+"0000";                                    //加上校验码
//                        System.out.println(rcontent);
                        int [] num=cyclicRedundancyCheck.stringToArray(rcontent);   //转为整形数组
                        crc=cyclicRedundancyCheck.getRemainder(num);      //再次进行校验为0即为成功
                        reply=reply+crc+"E";
                        reply = reply + "|" + "无";
                    }
                    break;
                //探测器信息
                case "90":
                    if (info.charAt(8) == '0') {
                        reply = "QN14" + info.substring(4, 6) + info.substring(6, 8) + info.substring(6, 8) + "00" ;// "CRC" + "E";
                        String crc="";
                        reply="QN14"+info.substring(4, 6)+info.substring(6, 8)+info.substring(6, 8)+"00";
                        //"CRC"+"E";
                        CyclicRedundancyCheck cyclicRedundancyCheck=new CyclicRedundancyCheck();
                        String rcontent=cyclicRedundancyCheck.toBinary(reply);   //将数据内容用2进制表示
                        rcontent=rcontent+"0000";                                    //加上校验码
//                        System.out.println(rcontent);
                        int [] num=cyclicRedundancyCheck.stringToArray(rcontent);   //转为整形数组
                        crc=cyclicRedundancyCheck.getRemainder(num);      //再次进行校验为0即为成功
                        reply=reply+crc+"E";
                        String tem1 = info.substring(13, 15);  //防护区
                        String tem2 = info.substring(15, 17);  //类型
                        String tem3 = info.substring(17, 19);  //ID
                        String tem4 = info.substring(19, 21);  //报警等级
                        String tem5 = info.substring(21, 23);  //温度
                        String tem6 = info.substring(23, 25);  //CO
                        String tem7 = info.substring(25, 27);  //VOC
                        String tem8 = info.substring(27, 29);  //烟雾


                        reply = reply + "|" + "防护区!" + tem1 + "!类型!" + tem2 + "!ID!" + tem3 + "!报警等级!" + tem4 + "!温度!" + tem5 + "!CO!" + tem6 + "!VOC!" + tem7 + "!烟雾!" + tem8 ;
                    } else {
                        reply = "QN14" + info.substring(4, 6) + info.substring(6, 8) + info.substring(6, 8) + "00" ;// "CRC" + "E";
                        String crc="";
                        reply="QN14"+info.substring(4, 6)+info.substring(6, 8)+info.substring(6, 8)+"00";
                        //"CRC"+"E";
                        CyclicRedundancyCheck cyclicRedundancyCheck=new CyclicRedundancyCheck();
                        String rcontent=cyclicRedundancyCheck.toBinary(reply);   //将数据内容用2进制表示
                        rcontent=rcontent+"0000";                                    //加上校验码
//                        System.out.println(rcontent);
                        int [] num=cyclicRedundancyCheck.stringToArray(rcontent);   //转为整形数组
                        crc=cyclicRedundancyCheck.getRemainder(num);      //再次进行校验为0即为成功
                        reply=reply+crc+"E";
                        reply = reply + "|" + "无";
                    }
                    break;
                default:
                    if(info.length()>18)
                    {
                        CyclicRedundancyCheck cyclicRedundancyCheck=new CyclicRedundancyCheck();  //crc校验的类
                        String rym=info.substring(23,27);  //取出4位校验码
                        String content=info.substring(11,23);   //取出数据内容
                        System.out.println(rym);
                        System.out.println(content);
                        String rcontent=cyclicRedundancyCheck.toBinary(content);   //将数据内容用2进制表示
                        rcontent=rcontent+rym;                                    //加上校验码
                        System.out.println(rcontent);
                        int [] num=cyclicRedundancyCheck.stringToArray(rcontent);   //转为整形数组
                        String result=cyclicRedundancyCheck.getRemainder(num);      //再次进行校验为0即为成功
                        System.out.println(result);
                    }
//                    reply = "QN14" + info.substring(4, 6) + info.substring(6, 8) + "7F" + "11" ;//+ "CRC";// + "E";
                    String crc="";
                    reply = "QN14" + info.substring(4, 6) + info.substring(6, 8) + "7F" + "11" ;//+ "CRC";// + "E";
                    //"CRC"+"E";
                    CyclicRedundancyCheck cyclicRedundancyCheck=new CyclicRedundancyCheck();
                    String rcontent=cyclicRedundancyCheck.toBinary(reply);   //将数据内容用2进制表示
                    rcontent=rcontent+"0000";                                    //加上校验码
//                        System.out.println(rcontent);
                    int [] num=cyclicRedundancyCheck.stringToArray(rcontent);   //转为整形数组
                    crc=cyclicRedundancyCheck.getRemainder(num);      //再次进行校验为0即为成功
                    reply=reply+crc+"E";
                    reply = reply + "|" + "无";
                    break;
            }




//            System.out.println(s);

//             *向客户端响应数据

            // 1.定义客户端的地址、端口号、数据
            InetAddress address = packet.getAddress();
            int port = packet.getPort();
            byte[] data2 = reply.getBytes();
            // 2.创建数据报，包含响应的数据信息
            DatagramPacket packet2 = new DatagramPacket(data2, data2.length, address, port);
            // 3.响应客户端
            socket.send(packet2);
            // 4.关闭资源
            socket.close();
        }

    }
}

