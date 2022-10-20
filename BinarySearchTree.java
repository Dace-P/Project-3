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
	}
}