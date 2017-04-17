package com.major.ssh;

public class SSHLinux {

	public static void main(String[] args) throws IOException, JSchException {
		// TODO Auto-generated method stub
		String host = "172.19.28.253";
		int port = 22;
		String user = "root";
		String password = "123456";
		String command = "whatweb --output-xml http://216.139.147.75:443/";
		String res = exeCommand(host, port, user, password, command);

		System.out.println(res);

	}

	public static String exeCommand(String host, int port, String user, String password, String command)
			throws JSchException, IOException {

		JSch jsch = new JSch();
		Session session = jsch.getSession(user, host, port);
		session.setConfig("StrictHostKeyChecking", "no");
		// java.util.Properties config = new java.util.Properties();
		// config.put("StrictHostKeyChecking", "no");

		session.setPassword(password);
		session.connect();

		ChannelExec channelExec = (ChannelExec) session.openChannel("exec");
		InputStream in = channelExec.getInputStream();
		channelExec.setCommand(command);
		channelExec.setErrStream(System.err);
		channelExec.connect();
		String out = IOUtils.toString(in, "UTF-8");

		channelExec.disconnect();
		session.disconnect();

		return out;
	}
}
