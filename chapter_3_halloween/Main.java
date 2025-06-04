public class Main {

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

//		public int treeCandy(Node tree) {
//				int total = 0;
//				Stack stack = new Stack();
//				while (tree != null) {
//						if (tree.left != null && tree.right != null) {
//								stack.push(tree.left);
//								tree = tree.right;
//						} else {
//								total = total + tree.candy;
//								if (stack.isStackEmpty()) {
//										tree = null;
//								} else {
//										tree = stack.pop();
//								}
//						}
//				}
//				return total;
//		}

		public int treeCandy(Node tree) {
				if (tree.left == null && tree.right == null) {
						return tree.candy;
				}
				return treeCandy(tree.left) + treeCandy(tree.right);
		}

		public int treeStreets(Node tree) {
				if (tree.left == null && and tree.right == null) {
						return 0;
				}
				return treeStreets(tree.left) + treeStreets(tree.right);
		}

		public int treeHeight(Node tree) {
				if (tree.left == null && tree.right == null) {
						return 0;
				}
				return 1 + Math.max(treeHeight(tree.left), treeHeigh(tree.right));
		}


		public Node readTree(Scanner sc) {
				if (!sc.hasNext()){
						return null;
				}

				String token = sc.next()
				if (token.equals("(")) {
						Node tree = new Node();
						tree.left = readTree(sc);
						tree.right = readTree(sc);
						return tree;
				} else {
						Node tree = new Node();
						tree.candy = Integer.parseInt(token);
						return tree;

		}

		public static void main(String args) {
				int candy = treeCandy(tree);
				int height = treeHeight(tree);
				int numStreets = treeStreets(tree) - height;
		}
}
