import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static final int SIZE = 255;
	public static final int TEST_CASES = 5;

	public static class Node {
		int candy;
		Node left, right;
	}

	public static class Stack {
		static final int SIZE = 255;
		Node[] values;
		int highestUsed;

		public Stack() {
			values = new Node[SIZE];
			highestUsed = -1;
		}

		public void push(Node value) {
			highestUsed++;
			values[highestUsed] = value;
		}

		public Node pop() {
			Node ret = values[highestUsed];
			highestUsed--;
			return ret;
		}

		public boolean isStackEmpty() {
			return highestUsed == -1;
		}
	}

	public static Node newHouse(int candy) {
		Node house = new Node();
		house.candy = candy;
		return house;
	}

	public static Node newNonHouse(Node left, Node right) {
		Node house = new Node();
		house.left = left;
		house.right = right;
		return house;
	}

	// public int treeCandy(Node tree) {
	// int total = 0;
	// Stack stack = new Stack();
	// while (tree != null) {
	// if (tree.left != null && tree.right != null) {
	// stack.push(tree.left);
	// tree = tree.right;
	// } else {
	// total = total + tree.candy;
	// if (stack.isStackEmpty()) {
	// tree = null;
	// } else {
	// tree = stack.pop();
	// }
	// }
	// }
	// return total;
	// }

	public static int treeCandy(Node tree) {
		if (tree.left == null && tree.right == null) {
			return tree.candy;
		}
		return treeCandy(tree.left) + treeCandy(tree.right);
	}

	public static int treeStreets(Node tree) {
		if (tree.left == null && tree.right == null) {
			return 0;
		}
		return treeStreets(tree.left) + treeStreets(tree.right) + 4;
	}

	public static int treeHeight(Node tree) {
		if (tree.left == null && tree.right == null) {
			return 0;
		}
		return 1 + Math.max(treeHeight(tree.left), treeHeight(tree.right));
	}

	static class IntWrapper {
		int value;

		public IntWrapper(int v) {
			value = v;
		}
	}

	public static Node readTree(char[] line) {
		return readTree(line, new IntWrapper(0));
	}

	public static Node readTree(char[] line, IntWrapper pos) {
		if (line[pos.value] == '(') {
			Node tree = new Node();
			pos.value++;
			tree.left = readTree(line, pos);
			pos.value++;
			tree.right = readTree(line, pos);
			pos.value++;
			return tree;
		} else {
			Node tree = new Node();
			tree.candy = line[pos.value] - '0';
			pos.value++;
			if (line[pos.value] != ')' && line[pos.value] != ' ' && pos.value < line.length) {
				tree.candy = tree.candy * 10 + (line[pos.value] - '0');
				pos.value++;
			}
			return tree;
		}
	}

	public static void treeSolve(Node tree) {
		int candy = treeCandy(tree);
		int height = treeHeight(tree);
		int streets = treeStreets(tree);
		int numStreets = streets - height;
		System.out.println(numStreets + " " + candy);
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		char[] line = new char[SIZE + 1];
		Node tree;
		for (int i = 0; i < TEST_CASES; i++) {
			String input = sc.nextLine();
			input.getChars(0, input.length(), line, 0);
			tree = readTree(line);
			treeSolve(tree);
		}
	}

	// public static void main(String[] args) {
	// Node four = newHouse(4);
	// Node nine = newHouse(9);
	// Node B = newNonHouse(four, nine);
	// Node fifteen = newHouse(15);
	// Node C = newNonHouse(B, fifteen);
	// }

	// public static void main(String[] args) {
	// Stack s = new Stack();
	// Node n1 = newHouse(20);
	// Node n2 = newHouse(30);
	// Node n3 = newHouse(10);
	// s.push(n1);
	// s.push(n2);
	// s.push(n3);
	// while (!s.isStackEmpty()) {
	// Node n = s.pop();
	// System.out.println(n.candy);
	// }
	// }
}
