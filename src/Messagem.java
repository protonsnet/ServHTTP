import javax.swing.JOptionPane;

public class Messagem {
	private String ip;

	public Messagem() {
	}
	
	public void ipConected(String ip) {
		JOptionPane.showMessageDialog(null,"O seguinte IP "+ip+" estabeleceu uma conexão");
	}
	
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	
}
