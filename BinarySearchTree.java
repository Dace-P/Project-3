import java.util.Scanner;

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
	//Finds the next inorder value and returns the node it's located at
	public Node<E> inOrderSuccesor(Node<E> node){
		//Case 1 where there is a right subtree
		if(node.right != null){
			//loops through to find the next value on the leftmost of the right subtree
			Node<E> nextNode = node.right;
			boolean bool = true;
			while(bool){
				if(nextNode.left == null){
					bool = false;
					System.out.println("here");
				}else{
					nextNode = nextNode.left;
					System.out.println("there");
				}
			}
			return find(nextNode.data);
		}
		//Case 2 if there is not a right subtree
		else{
			System.out.println("neither");
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
			command = scr.nextLine();
			if (command.equals("add") || command.equals("delete")){
				option = scr.nextInt();
			}
			bst.root = bst.root(bst.root);
		}
		scr.close();
	}
	

	public static void commands(){

	switch(command){
		case "ADD":
			bst.insert(option);
			break;
		case "DEL":
			bst.deleteNode(option);
			break;
		case "HGT":
			bst.getHeight(bst.root);
			break;
		case "PRE":
			bst.preorder(bst.root);
			break;
		case "POST":
			bst.postorder(bst.root);
			break;
		case "IN":
			bst.inorder(bst.root);
			break;
		case "LVL":

		case "CLEAR":
			bst.clear(bst.root);
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
			node = null;
			
		}
		//Case 2: Node has a left node
		else if(node.left!= null && node.right == null){
			
			node.data = node.left.data;
			node.left = null;
			
		}
		//Case 3: Node has a right node
		else if(node.left == null && node.right != null){
			
			node.data = node.right.data;
			node.right = null;
		}
		//Case 4: Node has a left and right node
		else{
			if(node.equals(node.parent.left)){
				Node<E> newNode = inOrderSuccesor(node);
				System.out.println(newNode.data);
				node.data = newNode.data;
				newNode = null;
				node.right = null;
			}
		}
	}

	//Clears the tree completely
	public void clear(Node<E> root){
		root = null;
	}

	public Node<E> root(Node<E> node){
		if(root != null){
			while(root.parent != null){
			root = root.parent;
			}
		}
		return root; 
	}

	public int getHeight(Node<E> node){
		Node<E> temp = node;
		
		if(root == null){
			return 0;
		}
		else{
			int left = getHeight(temp.left);
			int right = getHeight(temp.right);
			if (left > right)
                return (left + 1);
            else
                return (right + 1);
		}
		
	}
}
