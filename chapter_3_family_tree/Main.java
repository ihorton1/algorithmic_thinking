class Main {
	   static class Node {
				int numChildren;
				Node[] children;
				String name;
				int score;

				public Node(String name) {
						this.name = name;
						numChildren = 0;
	   }

	   public static int nameHash(String name) {
	   		int hash = 0;
	   		for (int i = 0; i < name.length(); i++) {
	   				hash = (hash * 31 + name.charAt(i)) % 1000;
	   		}
	   		return hash;
	   }

	   static class ListNode {
			   Node node;
			   Node next;
	   }


	   static class HashMap {
				int SIZE = 1000;
				ListNode[] map = new ListNode[1000];

				public Node findNode(Node node) {
						int nameHash = nameHash(node.name);
						ListNode nodePointer = map[nameHash];
						while (nodePointer != null) {
								if (nodePointer.node.name.equals(name)) {
										return nodePointer.node;
								}
								nodePointer = nodePointer.next;
						}
						return null;
				}

				public void add(Node node) {
						ListNode nodePointer = findNode(node);
						if (nodePointer == null) {
								int nameHash = nameHash(node.name);
								ListNode newNode = new ListNode();
								newNode.node = node;
								newNode.next = map[nameHash];
								map[nameHash] = listNode;
						}
				}
	   }

}
