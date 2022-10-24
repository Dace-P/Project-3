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
			Node<E> nextNode = node;
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

	public void delete(Node<E> n){
		n.data = null;
		n.left = null; 
		n.right = null;
		n.parent = null;
	}

//===============================================================
// Simple test program
	public static void main(String[] args) {
		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		Scanner scr = new Scanner(System.in);
		int n = scr.nextInt();
		for(int i=0; i<n; i++) {
			int a = scr.nextInt();
			bst.insert(a);
		}
		
		bst.inorder(bst.root);
		System.out.println();
		bst.preorder(bst.root);
		System.out.println();
		bst.postorder(bst.root);
		System.out.println();
		bst.deleteNode(4);
		bst.inorder(bst.root);
		System.out.println();
		bst.preorder(bst.root);
		System.out.println();
		bst.postorder(bst.root);
		System.out.println();
	}
	public void deleteNode(E n){

		Node<E> node = find(n);
		System.out.println(n);
		System.out.println(node.data);
		System.out.println(node.left);
		if(node.left != null)
			System.out.println(node.right.data);
		if(node.right != null)
			System.out.println(node.parent.data);

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
			delete(node);
			
		}
		else if(node.left!= null && node.right == null){
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
			node = node.left;
			delete(node.right);
		}
		else if(node.left == null && node.right != null){
			node = node.right;
			delete(node.right);
		}
		else{
			if(node.equals(node.parent.left)){
				Node<E> newNode = inOrderSuccesor(node);
				node.data = newNode.data;
				delete(node);
			}
		}
	}
}
