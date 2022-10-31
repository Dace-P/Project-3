import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.*;


public class BinarySearchTree<E extends Comparable<E>> {
	// ---------------- nested Node class ----------------
	private static class Node<E> {
		private E data; // reference to the data stored at this node
		private Node<E> left; // reference to the left child
		private Node<E> right; // reference to the right child
		private Node<E> parent; // reference to the parent

		public Node(E e) {
			data = e;
			left = right = parent = null;
		}

	}

	private Node<E> root = null; // root of the tree (or null if empty)

	public BinarySearchTree() {
	}

	// insert a new value into a binary search tree
	// if the value already exists, the tree is not changed
	public E insert(E data) {
		Node<E> p = new Node<E>(data);
		if (root == null) // if the tree is empty, put this value in the root
			root = p;
		else {
			Node<E> q = find(data);
			if (data.compareTo(q.data) < 0) { // it should be in the left subtree
				p.parent = q;
				q.left = p;
			} else if (data.compareTo(q.data) > 0) { // it should be in the right subtree
				p.parent = q;
				q.right = p;
			} else
				; // duplicate data -- do nothing
		}
		return data;
	}

	// find a value in a binary search tree
	// if it doesn't exist, return the node
	// that should be its parent
	public Node<E> find(E data) {
		if (root == null)
			return null;
		Node<E> p = root;
		while (p != null) {
			if (data.compareTo(p.data) < 0) // it should be in the left subtree
				if (p.left == null)
					return p;
				else
					p = p.left;
			else if (data.compareTo(p.data) > 0) // it should be in the right subtree
				if (p.right == null)
					return p;
				else
					p = p.right;
			else
				return p; // found it
		}
		return p; // it should be in a subtree of this node
	}

	// perform an inorder traversal printing the values
	public void inorder(Node<E> p) {
		if (p == null)
			return;
		inorder(p.left);
		System.out.print(p.data + " ");
		inorder(p.right);
	}
	
	// perform an preorder traversal printing the values
	public void preorder(Node<E> p) {
		if (p == null)
			return;
		System.out.print(p.data + " ");
		preorder(p.left);
		preorder(p.right);
	}
	
	// perform an postorder traversal printing the values
	public void postorder(Node<E> p) {
		if (p == null)
			return;
		postorder(p.left);
		postorder(p.right);
		System.out.print(p.data + " ");
	}

	//Performs a level order traversal printing the values
	public void levelorder(){
		Queue<Node<E>> queue = new LinkedList<Node<E>>();
		queue.add(root);
		if (root == null){
			return;
		}
		while(!queue.isEmpty()){
			Node<E> node = queue.poll();
			System.out.print(node.data + " ");

			if(node.left!=null){
				queue.add(node.left);
			}
			if (node.right!=null){
				queue.add(node.right);
			}
			
		}
	}


	//Finds the next inorder value and returns the node it's located at
	public Node<E> inOrderSuccessor(Node<E> node){
		//Case 1 where there is a right subtree
		if(node.right != null){
			//loops through to find the next value on the leftmost of the right subtree
			Node<E> nextNode = node.right;
			boolean bool = true;
			while(bool){
				if(nextNode.left == null){
					bool = false;
				}else{
					nextNode = nextNode.left;
				}
			}
			return find(nextNode.data);
		}
		//Case 2 if there is not a right subtree
		else{
			return find(node.parent.data);
		}
		
	}
	//Finds the previous inorder value and returns the node it's located at
	public Node<E> inOrderPrecessor(Node<E> node){
		//Case 1 where there is a right subtree
		if(node.left != null){
			//loops through to find the next value on the leftmost of the right subtree
			Node<E> nextNode = node.left;
			boolean bool = true;
			while(bool){
				if(nextNode.right == null){
					bool = false;
				}else{
					nextNode = nextNode.right;
				}
			}
			return find(nextNode.data);
		}
		//Case 2 if there is not a right subtree
		else{
			return find(node.parent.data);
		}
		
	}

//===============================================================
// Simple test program
	static String command;
	static int option;
	static BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
	static Node<Integer> rootNode;
	static boolean on = true;
	public static void main(String[] args) {
		
		Scanner scr = new Scanner(System.in);
		while(on) {
			StringTokenizer tokens = new StringTokenizer(scr.nextLine(), " ");
			command = tokens.nextToken();
			if (command.equals("ADD") || command.equals("DEL")){
				option = Integer.parseInt(tokens.nextToken());
			}
			bst.commands();
		}
		scr.close();
	}

	//Takes in commmands to run the needed methods
	public void commands(){
	//System.out.println(option);

	switch(command){
		case "ADD":
			bst.insert(option);
			break;
		case "DEL":
			bst.deleteNode(option);
			break;
		case "HGT":
			System.out.println(bst.getHeight(bst.root));
			break;
		case "PRE":
			bst.preorder(bst.root);
			System.out.println();
			break;
		case "POST":
			bst.postorder(bst.root);
			System.out.println();
			break;
		case "IN":
			bst.inorder(bst.root);
			System.out.println();
			break;
		case "LVL":
			bst.levelorder();
			System.out.println();
		case "CLEAR":
			bst.clear();
			break;
		case "END":
			on = false;
			break;
		}
	}

	//Deletes a node
	public void deleteNode(E n){
		//Find the location of the node
		Node<E> node = find(n);
		//Case 1: Node is a leaf
		if(node.left == null && node.right == null){
			if(node.parent == null){
				root = null;
			}
			else{
				if(node.parent.left != null){
					if(node.parent.left.data == node.data){
						node.parent.left = null;
				}
			}
			if (node.parent.right != null){
				if(node.parent.right.data == node.data){
					node.parent.right = null;
					}	
				}
			}
			
			node = null;
			
		}
		//Case 2: Node has a left node
		else if(node.left!= null && node.right == null){
			
			Node<E> newNode = node.left;
			
			if(node.data.compareTo(node.parent.data)<0){
				node.parent.left = newNode;
				newNode.parent = node.parent;

			}
			else{
				node.parent.right = newNode;
				newNode.parent = node.parent;
			}
			
			node = null;
			
		}
		//Case 3: Node has a right node
		else if(node.left == null && node.right != null){
			//Assigns newNode to the child of node
			Node<E> newNode = node.right;
			

			if(node.data.compareTo(node.parent.data)<0){
				node.parent.left = newNode;
				newNode.parent = node.parent;

			}
			else{
				node.parent.right = newNode;
				newNode.parent = node.parent;
			}
			
			node = null;
		}
		//Case 4: Node has a left and right node
		else{
			
			Node<E> newNode = inOrderSuccessor(node);
			E e = newNode.data;
			if(newNode.right != null){
				deleteNode(newNode.data);
				node.data = e;
			}else{
				deleteNode(newNode.data);
				node.data = e;
			}
			
		}
	
	}
	

	//Clears the tree completely
	public void clear(){
		root = null;
	}

	//Retrieves the maximum height of the tree 
	public int getHeight(Node<E> node){
		Node<E> temp = node;
		
		if(node == null){
			return -1;	
		}
		else{
			int left = getHeight(temp.left);
			int right = getHeight(temp.right);
			return(Math.max(left, right)) + 1;
		}
		
	}		
	
}
