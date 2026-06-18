package com.office.util;
import java.net.*;
import java.util.Enumeration;
/**
* 获取本机局域网IP地址工具类
* 排除回环地址、IPv6、禁用网卡、链路本地地址（169.254.x.x）
*/
public class LocalIPUtils {
    /**
    * 获取第一个有效局域网IPv4地址
    * @return 局域网IP字符串，无有效IP则返回null
    */
    public static String getLocalLanIp() {
        try {
            // 遍历所有网络接口
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface ni = networkInterfaces.nextElement();
                // 过滤：禁用的网卡、回环网卡（lo）
                if (ni.isLoopback() || !ni.isUp()) {
                    continue;
                }
                // 遍历当前网卡的所有IP地址
                Enumeration<InetAddress> addresses = ni.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress addr = addresses.nextElement();
                    // 仅保留IPv4地址
                    if (addr instanceof Inet4Address) {
                        String ip = addr.getHostAddress();
                        // 排除链路本地地址（169.254.x.x，无DHCP分配时的自动IP）
                        if (!ip.startsWith("169.254.") && !ip.equals("127.0.0.1")) {
                            return ip;
                        }
                    }
                }
            }
            } catch (SocketException e) {
                // 捕获网卡遍历异常，打印日志（实际项目中建议用日志框架）
                e.printStackTrace();
            }
            // 无有效局域网IP时返回null
            return null;
        }
        /**
        * 扩展：获取所有有效局域网IPv4地址（多网卡场景）
        * @return 所有有效IP的数组
        */
        public static String[] getAllLocalLanIps() {
            java.util.List<String> ipList = new java.util.ArrayList<>();
            try {
                Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
                while (networkInterfaces.hasMoreElements()) {
                    NetworkInterface ni = networkInterfaces.nextElement();
                    if (ni.isLoopback() || !ni.isUp()) {
                        continue;
                    }
                    Enumeration<InetAddress> addresses = ni.getInetAddresses();
                    while (addresses.hasMoreElements()) {
                        InetAddress addr = addresses.nextElement();
                        if (addr instanceof Inet4Address) {
                            String ip = addr.getHostAddress();
                            if (!ip.startsWith("169.254.") && !ip.equals("127.0.0.1")) {
                                ipList.add(ip);
                            }
                        }
                    }
                }
                } catch (SocketException e) {
                    e.printStackTrace();
                }
                return ipList.toArray(new String[0]);
            }
            // 测试方法
            public static void main(String[] args) {
                // 获取单个局域网IP
                String singleIp = getLocalLanIp();
                System.out.println("本机局域网IP：" + (singleIp == null ? "未找到有效IP" : singleIp));
                // 获取所有局域网IP
                String[] allIps = getAllLocalLanIps();
                System.out.println("所有有效局域网IP：");
                for (String ip : allIps) {
                    System.out.println("- " + ip);
                }
            }
        }
