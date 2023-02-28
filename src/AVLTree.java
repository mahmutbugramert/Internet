import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

public class AVLTree {
	private Node root;

	private ArrayList<String> log = new ArrayList<>();

	private ArrayList<String> ipAdressesOfParentStrings = new ArrayList<>();
	private ArrayList<Node> parentList = new ArrayList<>();
	private ArrayList<String> pathForSender = new ArrayList<>();
	private ArrayList<Node> parentListOfMinimum = new ArrayList<>();
	//private ArrayList<String> pathForSender = new ArrayList<>();

	public AVLTree() {

	}

	public AVLTree(Node root) {
		this.root = root;
	}

	public void publicAddNode(String newIpAddress, Node tempNode){
		Node addNode = new Node(null, null, newIpAddress);
		addNode(addNode, tempNode);
		findTheParents(newIpAddress, this.root);
		for(int i = parentList.size() - 1; i >= 0; i--) {
			if(getBalanceFactor(parentList.get(i)) >= 2) {
				if(parentList.get(i).getLeft().getIPAddress().compareTo(newIpAddress) >= 0) {
					lefLeftRotation(parentList.get(i));
					this.log.add("Rebalancing: right rotation");
					continue;
				}
				else if(parentList.get(i).getLeft().getIPAddress().compareTo(newIpAddress) <= 0){
					leftRightRotation(parentList.get(i));
					this.log.add("Rebalancing: left-right rotation");
					continue;
				}
			}
			if(getBalanceFactor(parentList.get(i)) <= -2) {
				if(parentList.get(i).getRight().getIPAddress().compareTo(newIpAddress) <= 0) {
					rightRightRotation(parentList.get(i));
					this.log.add("Rebalancing: left rotation");
					continue;
				}
				else if(parentList.get(i).getRight().getIPAddress().compareTo(newIpAddress) >= 0) {
					rightLeftRotation(parentList.get(i));
					this.log.add("Rebalancing: right-left rotation");
					continue;
				}
			}
		}
		parentList.clear();
		ipAdressesOfParentStrings.clear();
		return;

	}

	private void rightLeftRotation(Node node) {
		Node rightOfLeftOfRightNode = node.getRight().getLeft().getRight();
		Node leftOfLeftOfRightNode = node.getRight().getLeft().getLeft();
		Node leftNode = node.getLeft();
		String ipOfAddNode = node.getRight().getLeft().getIPAddress();
		node.getRight().getLeft().setIPAddress(node.getIPAddress());
		node.setIPAddress(ipOfAddNode);
		node.getRight().getLeft().setLeft(leftNode);
		node.getRight().getLeft().setRight(leftOfLeftOfRightNode);
		node.setLeft(node.getRight().getLeft());
		node.getRight().setLeft(rightOfLeftOfRightNode);
	}

	private void leftRightRotation(Node node) {
		Node leftOfRightOfLeftNode = node.getLeft().getRight().getLeft();
		Node rightOfRightOfLeftNode = node.getLeft().getRight().getRight();
		Node rightNode = node.getRight();
		String ipOfAddNode = node.getLeft().getRight().getIPAddress();
		node.getLeft().getRight().setIPAddress(node.getIPAddress());
		node.setIPAddress(ipOfAddNode);
		node.getLeft().getRight().setRight(rightNode);
		node.getLeft().getRight().setLeft(rightOfRightOfLeftNode);
		node.setRight(node.getLeft().getRight());
		node.getLeft().setRight(leftOfRightOfLeftNode);
	}

	private void rightRightRotation(Node node) {
		Node LeftOfNode = node.getLeft();
		Node leftOfRightOfNode = node.getRight().getLeft();
		Node rightOfRightOfNode = node.getRight().getRight();
		String ipAdressOfRight = node.getRight().getIPAddress();
		node.getRight().setIPAddress(node.getIPAddress());
		node.setIPAddress(ipAdressOfRight);
		node.getRight().setLeft(LeftOfNode);
		node.getRight().setRight(leftOfRightOfNode);
		node.setLeft(node.getRight());
		node.setRight(rightOfRightOfNode);
	}

	private void lefLeftRotation(Node node) {

		Node rightOfNode = node.getRight();
		Node leftOfLeftOfNode = node.getLeft().getLeft();
		Node rightOfLeftOfNode = node.getLeft().getRight();
		String ipAddressOfLeftString = node.getLeft().getIPAddress();
		node.getLeft().setIPAddress(node.getIPAddress());
		node.setIPAddress(ipAddressOfLeftString);
		node.getLeft().setRight(rightOfNode);
		node.getLeft().setLeft(rightOfLeftOfNode);
		node.setRight(node.getLeft());
		node.setLeft(leftOfLeftOfNode);
	}

	private int getBalanceFactor(Node myNode) {
		int leftHeightOfmyNode = findNewHeight(myNode.getLeft()) + 1;
		int rightHeightOfmyNode = findNewHeight(myNode.getRight()) + 1;
		//System.out.println("here"+leftHeightOfmyNode + rightHeightOfmyNode);
		//		System.out.print(leftHeightOfmyNode + " ");
		//		System.out.println(rightHeightOfmyNode);
		return leftHeightOfmyNode - rightHeightOfmyNode;
	}
	private void addNode(Node addNode, Node tempNode) {
		//Node addNode = new Node(null, null, newIpAddress);
		if(tempNode.getRight() == null && tempNode.getLeft() == null) {
			if(addNode.getIPAddress().compareTo(tempNode.getIPAddress()) >= 1) {
				tempNode.setRight(addNode);
				//tempNode.increaseHeight(tempNode);
				//myWriter.write(tempNode.getIPAddress() + ": New node being added with IP:" + addNode.getIPAddress());
				this.log.add(tempNode.getIPAddress() + ": New node being added with IP:" + addNode.getIPAddress());
				return;
			}
			if(addNode.getIPAddress().compareTo(tempNode.getIPAddress()) <= -1) {
				tempNode.setLeft(addNode);
				//tempNode.increaseHeight(tempNode);
				//myWriter.write(tempNode.getIPAddress() + ": New node being added with IP:" + addNode.getIPAddress());
				this.log.add(tempNode.getIPAddress() + ": New node being added with IP:" + addNode.getIPAddress());
				return;
			}
		}
		if(addNode.getIPAddress().compareTo(tempNode.getIPAddress()) >= 1) {
			if(tempNode.getRight() == null) {
				tempNode.setRight(addNode);
				//tempNode.increaseHeight(tempNode);
			}
			else {
				//myWriter.write(tempNode.getIPAddress() + ": New node being added with IP:" + addNode.getIPAddress());
				this.log.add(tempNode.getIPAddress() + ": New node being added with IP:" + addNode.getIPAddress());
				//tempNode.increaseHeight(tempNode);
				addNode(addNode, tempNode.getRight());
				return;
			}
			//myWriter.write(tempNode.getIPAddress() + ": New node being added with IP:" + addNode.getIPAddress());
			this.log.add(tempNode.getIPAddress() + ": New node being added with IP:" + addNode.getIPAddress());
			return;
		}
		if(addNode.getIPAddress().compareTo(tempNode.getIPAddress()) <= -1) {
			if(tempNode.getLeft() == null) {
				tempNode.setLeft(addNode);
				//tempNode.increaseHeight(tempNode);
			}
			else {
				this.log.add(tempNode.getIPAddress() + ": New node being added with IP:" + addNode.getIPAddress());
				//	tempNode.increaseHeight(tempNode);
				addNode(addNode, tempNode.getLeft());
				return;
			}
			this.log.add(tempNode.getIPAddress() + ": New node being added with IP:" + addNode.getIPAddress());
			return;
		}
		return;
	}
	//	private int height(Node myNode) {
	//		if(myNode == null) {
	//			return - 1;
	//		}
	//		else {
	//			return findNewHeight(myNode) + 1;
	//		}
	//	}
	private int findNewHeight(Node myNode) {
		if(myNode == null) {
			return 0;
		}
		else {
			int heightOfLeftSubtree = findNewHeight(myNode.getLeft());
			int heightOfRightSubtree = findNewHeight(myNode.getRight());
			//myNode.setHeight(Math.max(heightOfLeftSubtree, heightOfRightSubtree));
			return Math.max(heightOfLeftSubtree , heightOfRightSubtree) + 1;
		}
	}
	public void publicDeleteNode(String newIpAddress, Node tempNode) {
		findTheParentsAndTheNode(newIpAddress, this.root);
		deleteNode(newIpAddress, tempNode);
		//System.out.println("burası"+ipAdressesOfParentStrings);
		//System.out.println("parentlar  "+parentList.get(parentList.size() -1));
		for(int i = parentList.size() - 1; i >= 0; i--) {
		//	System.out.println(getBalanceFactor(parentList.get(i)));
			if(getBalanceFactor(parentList.get(i)) >= 2) {
				if(getBalanceFactor(parentList.get(i).getLeft()) >= 0 ) {
					lefLeftRotation(parentList.get(i));
					this.log.add("Rebalancing: right rotation");
					continue;
				}
				else if(getBalanceFactor(parentList.get(i).getLeft()) < 0 ){
					leftRightRotation(parentList.get(i));
					this.log.add("Rebalancing: left-right rotation");
					continue;
				}
			}
			if(getBalanceFactor(parentList.get(i)) <= -2) {
				if(getBalanceFactor(parentList.get(i).getRight()) <= 0 ) {
					rightRightRotation(parentList.get(i));
					this.log.add("Rebalancing: left rotation");
					continue;
				}
				else if(getBalanceFactor(parentList.get(i).getRight()) > 0 ) {
					rightLeftRotation(parentList.get(i));
					this.log.add("Rebalancing: right-left rotation");
					continue;
				}
			}
		}
		parentList.clear();
		ipAdressesOfParentStrings.clear();
		return;

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
						this.log.add(tempNode.getIPAddress() + ": Non Leaf Node Deleted; removed: " + newIpAddress + " replaced: " + assigNode.getLeft().getIPAddress());
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
			
		//	System.out.println(!(commonFirstParent == start || commonFirstParent == reach));
			if(!(commonFirstParent.equals(start)|| commonFirstParent.equals(reach))){
			//	System.out.println(commonFirstParent + " " + start + " " + reach);
				//System.out.println("buradan mıııııııııı");
				wholePathArrayList.add(commonFirstParent);
			}
			
			for(int c = indexInTheStart + 1; c<parentsOfReach.size() - 1; c++) {
				wholePathArrayList.add(parentsOfReach.get(c));
			}
		}
		
		//System.out.println("başlangıç  "+parentsOfStart);
	//	System.out.println(parentsOfReach);
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
			parentList.add(root);
			ipAdressesOfParentStrings.add(root.getIPAddress());
			findTheParents(start, root.getRight());
			return;
		}
		else if(start.compareTo(root.getIPAddress()) <= -1) {
			parentList.add(root);
			ipAdressesOfParentStrings.add(root.getIPAddress());
			findTheParents(start, root.getLeft());
			return;
		}
		else if(start.compareTo(root.getIPAddress()) == 0) {
						//parentList.add(root);
						//ipAdressesOfParentStrings.add(root.getIPAddress());
			return;
		}
	}
	private void findTheParentsOfMinimum(String start, Node root) {
		if(start.compareTo(root.getIPAddress()) >= 1) {
			parentListOfMinimum.add(root);
			//ipAdressesOfParentStrings.add(root.getIPAddress());
			findTheParents(start, root.getRight());
			return;
		}
		else if(start.compareTo(root.getIPAddress()) <= -1) {
			parentListOfMinimum.add(root);
			//ipAdressesOfParentStrings.add(root.getIPAddress());
			findTheParents(start, root.getLeft());
			return;
		}
		else if(start.compareTo(root.getIPAddress()) == 0) {
						//parentList.add(root);
						//ipAdressesOfParentStrings.add(root.getIPAddress());
			return;
		}
	}
	
	private void findTheParents2(String start, Node root) {
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
			//parentList.add(root);
			//ipAdressesOfParentStrings.add(root.getIPAddress());
			return;
		}
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


}
