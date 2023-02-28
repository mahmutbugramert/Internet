import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.channels.NonReadableChannelException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		String data = null;
		ArrayList<String> listOfInputs = new ArrayList<String>(); 
		try {// reads input file
			
			File myObj = new File(args[0]);
			Scanner myReader = new Scanner(myObj);
			while (myReader.hasNextLine()) {
				data = myReader.nextLine();
				listOfInputs.add(data);
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		//System.out.println(listOfInputs);
		
		FileWriter myWriter = null;
		try {
			//myWriter = new FileWriter("C:\\Bugra\\outputs\\output42_AVL.txt");
			myWriter = new FileWriter(args[1] + "_AVL.txt");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		FileWriter myWriterBst = null;
		try {
			//myWriterBst = new FileWriter("C:\\Bugra\\outputs\\output42_BST.txt");
			myWriterBst = new FileWriter(args[1] + "_BST.txt");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		BSTTree myBstTree = new BSTTree();
		AVLTree myAvlTree = new AVLTree();
		Node rootNode = new Node(null, null, listOfInputs.get(0));
		Node rootNode2 = new Node(null, null, listOfInputs.get(0));
		myBstTree.setRoot(rootNode2);
		myAvlTree.setRoot(rootNode);
		for(int i = 1; i < listOfInputs.size(); i++) {
			rootNode2 = myBstTree.getRoot();
			rootNode = myAvlTree.getRoot();
			String[] splittedLines = listOfInputs.get(i).split(" ");
			if(splittedLines[0].equals("ADDNODE")) {
				myBstTree.addNode(splittedLines[1], rootNode2);
				ArrayList<String> listOfLogStrings = new ArrayList<>();
				listOfLogStrings = myBstTree.getLog();
				for(int j = 0; j < listOfLogStrings.size(); j++) {
					try {
						myWriterBst.write(listOfLogStrings.get(j)+"\n");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			
				}
				myBstTree.clearLog();
				
				myAvlTree.publicAddNode(splittedLines[1], rootNode);
				ArrayList<String> listOfLog2 = new ArrayList<>();
				listOfLog2 = myAvlTree.getLog();
				for(int k = 0; k < listOfLog2.size(); k++) {
					try {
						myWriter.write(listOfLog2.get(k)+"\n");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		
				}
				myAvlTree.clearLog();
			}
			if(splittedLines[0].equals("DELETE")) {
				myBstTree.deleteNode(splittedLines[1], rootNode2);
				ArrayList<String> listOfLogStrings = new ArrayList<>();
				listOfLogStrings = myBstTree.getLog();
				for(int j = 0; j < listOfLogStrings.size(); j++) {
					try {
						myWriterBst.write(listOfLogStrings.get(j)+"\n");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		
				}
				myBstTree.clearLog();
				
				myAvlTree.publicDeleteNode(splittedLines[1], rootNode);
				ArrayList<String> listOfLog2 = new ArrayList<>();
				listOfLog2 = myAvlTree.getLog();
				for(int k = 0; k < listOfLog2.size(); k++) {
					try {
						myWriter.write(listOfLog2.get(k)+"\n");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
				}
				myAvlTree.clearLog();
			}
			if(splittedLines[0].equals("SEND")) {
				myBstTree.sendMessage(splittedLines[1], splittedLines[2]);
				ArrayList<String> listOfLogStrings = new ArrayList<>();
				listOfLogStrings = myBstTree.getLog();
				for(int j = 0; j < listOfLogStrings.size(); j++) {
					try {
						myWriterBst.write(listOfLogStrings.get(j)+"\n");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
				}
				myBstTree.clearLog();
				
				myAvlTree.sendMessage(splittedLines[1], splittedLines[2]);
				ArrayList<String> listOfLog2 = new ArrayList<>();
				listOfLog2 = myAvlTree.getLog();
				for(int k = 0; k < listOfLog2.size(); k++) {
					try {
						myWriter.write(listOfLog2.get(k)+"\n");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
				}
				myAvlTree.clearLog();
			}
		}
		try {
			myWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			myWriterBst.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//System.out.println(myAvlTree.toString());
		//System.out.println(myBstTree.toString());
	}	
}
