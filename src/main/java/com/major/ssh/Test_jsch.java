package com.major.ssh;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class Test_jsch {
	public static void main(String[] args) throws Exception {
		JSch jsch = new JSch(); // 创建JSch对象
		String userName = "";// 用户名
		String password = "";// 密码
		String host = "";// 服务器地址
		int port = 0;// 端口号
		String cmd = "";// 要运行的命令
		Session session = jsch.getSession(userName, host, port); // 根据用户名，主机ip，端口获取一个Session对象
		session.setPassword(password); // 设置密码
		Properties config = new Properties();
		config.put("StrictHostKeyChecking", "no");
		session.setConfig(config); // 为Session对象设置properties
		int timeout = 60000000;
		session.setTimeout(timeout); // 设置timeout时间
		session.connect(); // 通过Session建立链接
		ChannelExec channelExec = (ChannelExec) session.openChannel("exec");
		channelExec.setCommand(cmd);
		channelExec.setInputStream(null);
		channelExec.setErrStream(System.err);
		channelExec.connect();
		InputStream in = channelExec.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(in, Charset.forName("UTF-8")));
		String buf = null;
		StringBuffer sb = new StringBuffer();
		while ((buf = reader.readLine()) != null) {
			sb.append(buf);
			System.out.println(buf);// 打印控制台输出
		}
		reader.close();
		channelExec.disconnect();
		if (null != session) {
			session.disconnect();
		}
	}
}
