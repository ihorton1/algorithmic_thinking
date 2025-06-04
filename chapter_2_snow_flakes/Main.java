import java.util.Scanner;

public class Main {
		static int SIZE = 100000;

		public static void identifyIdentical(SnowflakeNode[] snowflakes) {
				for (int i = 0; i < SIZE; i++) {
						SnowflakeNode node1 = snowflakes[i];
						while (node1 != null) {
								SnowflakeNode node2 = node1.next;
								while (node2 != null) {
										if (areIdentical(node1.snowflake, node2.snowflake)) {
												System.out.println("Twin ingtegers found.");
												return;
										}
										node2 = node2.next;
								}
								node1 = node1.next;
						}
				}
				System.out.println("No two integers are alike.");
		}

		public static boolean identicalRight(int[] snow1, int[] snow2, int start) {
				for (int offset = 0; offset < 6; offset++) {
						if (snow1[offset] != snow2[(start + offset) % 6]) {
								return false;
						}
				}
				return true;
		}

		public static boolean identicalLeft(int[] snow1, int[] snow2, int start) {
				for (int offset = 0; offset < 6; offset++) {
						int snow2Index = start - offset;
						if (snow2Index <= -1) {
								snow2Index = snow2Index + 6;
						}
						if (snow1[offset] != snow2[snow2Index]) {
								return false;
						}
				}
				return true;
		}

		public static boolean areIdentical(int[] snow1, int[] snow2) {
				for (int start = 0; start < 6; start++) {
						if (identicalRight(snow1, snow2, start)) {
								return true;
						}
						if (identicalLeft(snow1, snow2, start)) {
								return true;
						}
				}
				return false;
		}

		public static int code(int[] snowflake) {
				return (snowflake[0] + snowflake[1] + snowflake[2] + snowflake[3]
								+ snowflake[4] + snowflake[5]) % SIZE;
		}

		public static class SnowflakeNode {
				int[] snowflake;
				SnowflakeNode next;

				public SnowflakeNode(int[] snowflake) {
						this.snowflake = snowflake;
						this.next = null;
				}
		}

		public static void main(String[] args) {
				SnowflakeNode[] snowflakes = new SnowflakeNode[SIZE];
				Scanner sc = new Scanner(System.in);
				int n = sc.nextInt();
				for (int i = 0; i < n; i++) {
						int[] snowflake = new int[6];
						for (int j = 0; j < 6; j++) {
								snowflake[j] = sc.nextInt();
						}
						int snowflakeCode = code(snowflake);
						SnowflakeNode newNode = new SnowflakeNode(snowflake);
						newNode.next = snowflakes[snowflakeCode];
						snowflakes[snowflakeCode] = newNode;
				}
				identifyIdentical(snowflakes);
		}
}
