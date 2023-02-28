
public class Node {
	private Node right;
	private Node left;
	private String IPAddress;
	private Integer height;
	
	public Node() {
		this.height = 0;
	}
	public Node(Node left, Node right, String IPAddress) {
		this.left = left;
		this.right = right;
		this.IPAddress = IPAddress;
		this.height = 0;
	}
	public Node(Node left, Node right, String IPAddress, Integer height) {
		this.left = left;
		this.right = right;
		this.IPAddress = IPAddress;
		this.height = 0;
	}

	public Node getRight() {
		return right;
	}

	public void setRight(Node right) {
		this.right = right;
	}

	public Node getLeft() {
		return left;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public String getIPAddress() {
		return IPAddress;
	}

	public void setIPAddress(String iPAddress) {
		IPAddress = iPAddress;
	}
	public Integer getHeight() {
		return height;
	}
	public void setHeight(Integer height) {
		this.height = height;
	}
	public void increaseHeight(Node myNode) {
		this.height = myNode.getHeight() + 1;
	}
	@Override
	public String toString() {
		return "Node [right=" + right + ", left=" + left + ", IPAddress=" + IPAddress + "]";
	}
	
}

