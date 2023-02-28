import java.net.Socket;
import java.util.ArrayList;

public class BSTTree {
	
	private Node root;
	
	private ArrayList<String> log = new ArrayList<>();
	private ArrayList<Node> parentList = new ArrayList<>();
	private ArrayList<String> ipAdressesOfParentStrings = new ArrayList<>();
	
	public BSTTree() {
		
	}

	public BSTTree(Node root) {
		this.root = root;
	}
	
	public void addNode(String newIpAddress, Node tempNode) {
		Node addNode = new Node(null, null, newIpAddress);
		if(tempNode.getRight() == null && tempNode.getLeft() == null) {
			if(newIpAddress.compareTo(tempNode.getIPAddress()) >= 1) {
				tempNode.setRight(addNode);
				this.log.add(tempNode.getIPAddress() + ": New node being added with IP:" + newIpAddress);
				return;
			}
			if(newIpAddress.compareTo(tempNode.getIPAddress()) <= -1) {
				tempNode.setLeft(addNode);
				this.log.add(tempNode.getIPAddress() + ": New node being added with IP:" + newIpAddress);
				return;
			}
		}
		if(newIpAddress.compareTo(tempNode.getIPAddress()) >= 1) {
			if(tempNode.getRight() == null) {
				tempNode.setRight(addNode);
			}
			else {
				this.log.add(tempNode.getIPAddress() + ": New node being added with IP:" + newIpAddress);
				addNode(newIpAddress, tempNode.getRight());
				return;
			}
			this.log.add(tempNode.getIPAddress() + ": New node being added with IP:" + newIpAddress);
			return;
		}
		if(newIpAddress.compareTo(tempNode.getIPAddress()) <= -1) {
			if(tempNode.getLeft() == null) {
				tempNode.setLeft(addNode);
			}
			else {
				this.log.add(tempNode.getIPAddress() + ": New node being added with IP:" + newIpAddress);
				addNode(newIpAddress, tempNode.getLeft());
				return;
			}
			this.log.add(tempNode.getIPAddress() + ": New node being added with IP:" + newIpAddress);
			return;
		}
		return;
	}

	private Node findMinimum(Node tempNode) {
		if(tempNode.getRight() == null & tempNode.getLeft() == null) {
			return tempNode;
		}
		else if(tempNode.getLeft() == null) {
			return tempNode;
		}
		else if(tempNode.getRight() == null) {
			return findMinimum(tempNode.getLeft());
		}
		else {
			return findMinimum(tempNode.getLeft());
		}
	}
	public void deleteNode(String newIpAddress, Node tempNode) {
		if(tempNode.getLeft() != null && tempNode.getLeft().getIPAddress().equals(newIpAddress)) {
			if(tempNode.getLeft().getLeft() == null && tempNode.getLeft().getRight() == null) {
				this.log.add(tempNode.getIPAddress() + ": Leaf Node Deleted: " + newIpAddress);
				tempNode.setLeft(null);
				return;
			}
			if(tempNode.getLeft().getRight() == null) {
				this.log.add(tempNode.getIPAddress() + ": Node with single child Deleted: " + newIpAddress);
				tempNode.setLeft(tempNode.getLeft().getLeft());
				return;
			}	
			else {
				if(tempNode.getLeft().getRight().getLeft() == null && tempNode.getLeft().getRight().getRight() == null) {
					if(tempNode.getLeft().getLeft() == null) {
						this.log.add(tempNode.getIPAddress() + ": Node with single child Deleted: " + newIpAddress);
					}
					else {
						this.log.add(tempNode.getIPAddress() + ": Non Leaf Node Deleted; removed: " + newIpAddress + " replaced: " + tempNode.getLeft().getRight().getIPAddress());
					}
					tempNode.getLeft().setIPAddress(tempNode.getLeft().getRight().getIPAddress());
					tempNode.getLeft().setRight(null);
					return;
				}
				if(tempNode.getLeft().getRight().getLeft() == null) {
					if(tempNode.getLeft().getLeft() == null) {
						this.log.add(tempNode.getIPAddress() + ": Node with single child Deleted: " + newIpAddress);
					}
					else {
						this.log.add(tempNode.getIPAddress() + ": Non Leaf Node Deleted; removed: " + newIpAddress + " replaced: " + tempNode.getLeft().getRight().getIPAddress());
					}
					tempNode.getLeft().setIPAddress(tempNode.getLeft().getRight().getIPAddress());
					tempNode.getLeft().setRight(tempNode.getLeft().getRight().getRight());
					return;
				}
				else {
					Node assigNode = findMin(tempNode.getLeft().getRight());
					if(tempNode.getLeft().getLeft() == null) {
						this.log.add(tempNode.getIPAddress() + ": Node with single child Deleted: " + newIpAddress);
						tempNode.setLeft(tempNode.getLeft().getRight());
						return;
					}
					else {
						this.log.add(tempNode.getIPAddress() + ": Non Leaf Node Deleted; removed: " + newIpAddress + " replaced: " + assigNode.getLeft().getIPAddress());
					}	
					tempNode.getLeft().setIPAddress(assigNode.getLeft().getIPAddress());
					if(assigNode.getLeft().getRight() == null) {
						assigNode.setLeft(null);
						return;
					}
					else {
						assigNode.setLeft(assigNode.getLeft().getRight());
						return;
					}
					
				}
			}
		}
		if(tempNode.getRight() != null && tempNode.getRight().getIPAddress().equals(newIpAddress)) {
			if(tempNode.getRight().getLeft() == null && tempNode.getRight().getRight() == null) {
				this.log.add(tempNode.getIPAddress() + ": Leaf Node Deleted: " + newIpAddress);
				tempNode.setRight(null);
				return;
			}
			if(tempNode.getRight().getRight() == null) {
				this.log.add(tempNode.getIPAddress() + ": Node with single child Deleted: " + newIpAddress);
				tempNode.setRight(tempNode.getRight().getLeft());
				return;
			}
			else {
				if(tempNode.getRight().getRight().getLeft() == null && tempNode.getRight().getRight().getRight() == null) {
					if(tempNode.getRight().getLeft() == null) {
						this.log.add(tempNode.getIPAddress() + ": Node with single child Deleted: " + newIpAddress);
					}
					else {
						this.log.add(tempNode.getIPAddress() + ": Non Leaf Node Deleted; removed: " + newIpAddress + " replaced: " + tempNode.getRight().getRight().getIPAddress());
					}
					tempNode.getRight().setIPAddress(tempNode.getRight().getRight().getIPAddress());
					tempNode.getRight().setRight(null);
					return;
				}
				if(tempNode.getRight().getRight().getLeft() == null) {
					if(tempNode.getRight().getLeft() == null) {
						this.log.add(tempNode.getIPAddress() + ": Node with single child Deleted: " + newIpAddress);
					}
					else {
						this.log.add(tempNode.getIPAddress() + ": Non Leaf Node Deleted; removed: " + newIpAddress + " replaced: " + tempNode.getRight().getRight().getIPAddress());
					}
					tempNode.getRight().setIPAddress(tempNode.getRight().getRight().getIPAddress());
					tempNode.getRight().setRight(tempNode.getRight().getRight().getRight());
					return;
				}
				else {
					Node assigNode = findMin(tempNode.getRight().getRight());
					if(tempNode.getRight().getLeft() == null) {
						this.log.add(tempNode.getIPAddress() + ": Node with single child Deleted: " + newIpAddress);
					}
					else {
						this.log.add(tempNode.getIPAddress() + ": Non-Leaf Node Deleted; removed: " + newIpAddress + " replaced: " + assigNode.getLeft().getIPAddress());
					}
					tempNode.getRight().setIPAddress(assigNode.getLeft().getIPAddress());
					if(assigNode.getLeft().getRight() == null) {
						assigNode.setLeft(null);
						return;
					}
					else {
						assigNode.setLeft(assigNode.getLeft().getRight());
						return;
					}
					
				}
			}
		}
		if(newIpAddress.compareTo(tempNode.getIPAddress())  >= 1) {
			if(tempNode.getRight() == null) {
				return;
			}
			else {
				deleteNode(newIpAddress, tempNode.getRight());
				return;
			}
		}
		if(newIpAddress.compareTo(tempNode.getIPAddress())  <= 1) {
			if(tempNode.getLeft() == null) {
				return;
			}
			else {
				deleteNode(newIpAddress, tempNode.getLeft());
				return;
			}
		}
	}
	private Node findMin(Node tempNode){
		if(tempNode.getLeft().getLeft() == null) {
			return tempNode;
		}
		else {
			findMin(tempNode.getLeft());
		}
		return null;
	}
	public void sendMessage(String start, String reach) {
		findTheParentsAndTheNode(start, this.root);
		ArrayList<String> parentsOfStart = new ArrayList<>(ipAdressesOfParentStrings);
		ipAdressesOfParentStrings.clear();
		parentList.clear();
		findTheParentsAndTheNode(reach, this.root);
		ArrayList<String> parentsOfReach = new ArrayList<>(ipAdressesOfParentStrings);
		ipAdressesOfParentStrings.clear();
		parentList.clear();
		
		String commonFirstParent = null;
		int indexInTheStart = 0;
		for(int i = parentsOfStart.size() - 1; i>=0; i--) {
			if(parentsOfReach.contains(parentsOfStart.get(i))) {
				indexInTheStart = i;
				commonFirstParent = parentsOfStart.get(i);
				break;
			}
		}
		
		ArrayList<String> wholePathArrayList = new ArrayList<>();
		
		if(start.equals(this.root.getIPAddress())) {
			for(int b = 1; b < parentsOfReach.size() - 1; b++) {
				wholePathArrayList.add(parentsOfReach.get(b));
			}
		}
		else if(reach.equals(this.root.getIPAddress())) {
			for(int g = parentsOfStart.size() - 2; g>0; g--) {
				wholePathArrayList.add(parentsOfStart.get(g));
			}
		}
		else {
			for(int d = parentsOfStart.size() - 2; d>indexInTheStart; d--) {
				wholePathArrayList.add(parentsOfStart.get(d));
			}
			
			//System.out.println(!(commonFirstParent == start || commonFirstParent == reach));
			if(!(commonFirstParent.equals(start)|| commonFirstParent.equals(reach))){
				//System.out.println(commonFirstParent + " " + start + " " + reach);
				//System.out.println("buradan mıııııııııı");
				wholePathArrayList.add(commonFirstParent);
			}
			
			for(int c = indexInTheStart + 1; c<parentsOfReach.size() - 1; c++) {
				wholePathArrayList.add(parentsOfReach.get(c));
			}
		}
		
		//System.out.println("başlangıç  "+parentsOfStart);
		//System.out.println(parentsOfReach);
		//System.out.println(wholePathArrayList);
		this.log.add(start + ": Sending message to: " + reach);
		
		for(int h = 0; h < wholePathArrayList.size(); h++) {
//			if(wholePathArrayList.get(h) == start || wholePathArrayList.get(h) == reach) {
//				continue;
//			}
			if(h == 0) {
				this.log.add(wholePathArrayList.get(h) + ": Transmission from: " + start + " receiver: " + reach + " sender:" + start);
			}
			else{
				this.log.add(wholePathArrayList.get(h) + ": Transmission from: " + wholePathArrayList.get(h - 1) + " receiver: " + reach + " sender:" + start);
			}
		}
			
			this.log.add(reach + ": Received message from: " + start);
		return;
	}

	
	private void logForSendUpperRight(Node startNode, String start, String reach) {
		for(int i = ipAdressesOfParentStrings.size() - 1; i >= 0; i--) {
			if(reach.compareTo(ipAdressesOfParentStrings.get(i)) == 0) {
				this.log.add(reach + ": Received message from: " + start);
				break;
			}
			else if(i == 0) {
				if(ipAdressesOfParentStrings.size() == 1) {
					this.log.add(ipAdressesOfParentStrings.get(i) + ": Transmission from: " + start + " receiver: " + reach + " sender:" + start);
				}
				else {
					this.log.add(ipAdressesOfParentStrings.get(i) + ": Transmission from: " + ipAdressesOfParentStrings.get(i+1) + " receiver: " + reach + " sender:" + start);
				}
				Node myNode = findTheNode(this.root, ipAdressesOfParentStrings.get(i));
				logForSend(myNode, start, reach);
				break;
			}
			else if(reach.compareTo(ipAdressesOfParentStrings.get(i)) >= 1 && reach.compareTo(ipAdressesOfParentStrings.get(i-1)) <= -1) {
				this.log.add(ipAdressesOfParentStrings.get(i) + ": Transmission from: " + ipAdressesOfParentStrings.get(i+1) + " receiver: " + reach + " sender:" + start);
				Node myNode = findTheNode(this.root, ipAdressesOfParentStrings.get(i));
				logForSend(myNode, start, reach);
				break;
			}
			else if(reach.compareTo(ipAdressesOfParentStrings.get(i)) >= 1) {
				if(i == ipAdressesOfParentStrings.size() - 1) {
					this.log.add(ipAdressesOfParentStrings.get(i) + ": Transmission from: " + start + " receiver: " + reach + " sender:" + start);
				}
				else {
					this.log.add(ipAdressesOfParentStrings.get(i) + ": Transmission from: " + ipAdressesOfParentStrings.get(i+1) + " receiver: " + reach + " sender:" + start);
				}
			}
			
		}
		return;
	}

	private void logForSendUpper(Node startNode, String start, String reach) {
		for(int i = ipAdressesOfParentStrings.size() - 1; i >= 0; i--) {
			if(reach.compareTo(ipAdressesOfParentStrings.get(i)) == 0) {
				this.log.add(reach + ": Received message from: " + start);
				break;
			}
			else if(i == 0) {
				if(ipAdressesOfParentStrings.size() == 1) {
					this.log.add(ipAdressesOfParentStrings.get(i) + ": Transmission from: " + start + " receiver: " + reach + " sender:" + start);
				}
				else {
					this.log.add(ipAdressesOfParentStrings.get(i) + ": Transmission from: " + ipAdressesOfParentStrings.get(i+1) + " receiver: " + reach + " sender:" + start);
				}
				Node myNode = findTheNode(this.root, ipAdressesOfParentStrings.get(i));
				logForSend(myNode, start, reach);
				break;
			}
			else if(reach.compareTo(ipAdressesOfParentStrings.get(i)) <= -1 && reach.compareTo(ipAdressesOfParentStrings.get(i-1)) >= 1) {
				this.log.add(ipAdressesOfParentStrings.get(i) + ": Transmission from: " + ipAdressesOfParentStrings.get(i+1) + " receiver: " + reach + " sender:" + start);
				Node myNode = findTheNode(this.root, ipAdressesOfParentStrings.get(i));
				logForSend(myNode, start, reach);
				break;
			}
			else if(reach.compareTo(ipAdressesOfParentStrings.get(i)) <= -1) {
				if(i == ipAdressesOfParentStrings.size() - 1) {
					this.log.add(ipAdressesOfParentStrings.get(i) + ": Transmission from: " + start + " receiver: " + reach + " sender:" + start);
				}
				else {
					this.log.add(ipAdressesOfParentStrings.get(i) + ": Transmission from: " + ipAdressesOfParentStrings.get(i+1) + " receiver: " + reach + " sender:" + start);
				}
			}
			
		}
		return;
	}

	private void logForSend(Node startNode, String start, String reach) {
		if(startNode.getIPAddress().compareTo(reach) >= 1) {
			if(startNode.getLeft().getIPAddress().equals(reach)) {
				this.log.add(reach + ": Received message from: " + start);
				return;
			}
			else {
				this.log.add(startNode.getLeft().getIPAddress() + ": Transmission from: "+ startNode.getIPAddress() + " receiver: " + reach + " sender:" + start);
				logForSend(startNode.getLeft(), start, reach);
				return;
			}	
		}
		if(startNode.getIPAddress().compareTo(reach) <= -1) {
			if(startNode.getRight().getIPAddress().equals(reach)) {
				this.log.add(reach + ": Received message from: " + start);
				return;
			}
			else {
				this.log.add(startNode.getRight().getIPAddress() + ": Transmission from: "+ startNode.getIPAddress() + " receiver: " + reach + " sender:" + start);
				logForSend(startNode.getRight(), start, reach);
				return;
			}	
		}
	}

	private boolean searchReach(Node startNode, String reach) {
		if(startNode.getIPAddress().equals(reach)) {
			return true;
		}
		else if(startNode.getIPAddress().compareTo(reach) >= 1 && startNode.getLeft() != null) {
			return searchReach(startNode.getLeft(), reach);
			
		}
		else if(startNode.getIPAddress().compareTo(reach) <= -1 && startNode.getRight() != null) {
			return searchReach(startNode.getRight(), reach);
			
		}
		return false;
	}
	
	private Node findTheNode(Node tempNode, String start) {
		if(start.compareTo(tempNode.getIPAddress()) >= 1) {
			return findTheNode(tempNode.getRight(), start);
		}
		else if(start.compareTo(tempNode.getIPAddress()) <= -1) {
			return findTheNode(tempNode.getLeft(), start);
		}
		return tempNode;
	}

	private void findTheParents(String start, Node root) {
		if(start.compareTo(root.getIPAddress()) >= 1) {
			ipAdressesOfParentStrings.add(root.getIPAddress());
			findTheParents(start, root.getRight());
			return;
		}
		else if(start.compareTo(root.getIPAddress()) <= -1) {
			ipAdressesOfParentStrings.add(root.getIPAddress());
			findTheParents(start, root.getLeft());
			return;
		}
		else if(start.compareTo(root.getIPAddress()) == 0) {
			return;
		}
	}
	private void findTheParents2(String start, Node root) {
		//System.out.println(root.getIPAddress());
		if(start.compareTo(root.getIPAddress()) >= 1) {
			parentList.add(root);
			ipAdressesOfParentStrings.add(root.getIPAddress());
			findTheParents2(start, root.getRight());
			return;
		}
		else if(start.compareTo(root.getIPAddress()) <= -1) {
			parentList.add(root);
			ipAdressesOfParentStrings.add(root.getIPAddress());
			findTheParents2(start, root.getLeft());
			return;
		}
		else if(start.compareTo(root.getIPAddress()) == 0) {
			//System.out.println("here");
			//parentList.add(root);
			//ipAdressesOfParentStrings.add(root.getIPAddress());
			return;
		}
	}

	public Node getRoot() {
		return root;
	}

	public void setRoot(Node root) {
		this.root = root;
	}
	
	public ArrayList<String> getLog() {
		return log;
	}

	public void setLog(ArrayList<String> log) {
		this.log = log;
	}
	public void clearLog() {
		log.clear();
	}

	@Override
	public String toString() {
		return "BSTTree [root=" + root.toString() + "]";
	}
	private void findTheParentsAndTheNode(String start, Node root) {
		if(start.compareTo(root.getIPAddress()) >= 1) {
			//System.out.println("büyük  ");
			parentList.add(root);
			ipAdressesOfParentStrings.add(root.getIPAddress());
			findTheParentsAndTheNode(start, root.getRight());
			return;
		}
		else if(start.compareTo(root.getIPAddress()) <= -1) {
			//System.out.println("küçük  ");
			parentList.add(root);
			ipAdressesOfParentStrings.add(root.getIPAddress());
			findTheParentsAndTheNode(start, root.getLeft());
			return;
		}
		else if(start.compareTo(root.getIPAddress()) == 0) {
			//System.out.println("evet  ");
			parentList.add(root);
			ipAdressesOfParentStrings.add(root.getIPAddress());
			return;
		}
	}
	
	
}	

