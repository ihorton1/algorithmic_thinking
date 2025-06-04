import java.util.Scanner;

public class Main {
		public static final int MAX_PASSWORD = 10;
		public static final int NUM_BITS = 20;
		public static final PasswordNode[] hashTable = new PasswordNode[1 << NUM_BITS];

		public static class PasswordNode {
				public char[] password = new char[MAX_PASSWORD];
				public int total;
				PasswordNode next;

				public PasswordNode(char[] password, int total) {
						this.password = password;
						this.total = total;
				}
		}

		public static PasswordNode inHashTable(PasswordNode[] hashTable, char[] find) {
				int passwordCode = oaat(find, find.length, NUM_BITS);
				PasswordNode passwordPtr = hashTable[passwordCode];
				while (passwordPtr != null) {
						if (compareCharArrays(passwordPtr.password, find)) {
								return passwordPtr;
						}
						passwordPtr = passwordPtr.next;
				}
				return null;
		}

		public static void addToHashTable(PasswordNode[] hashTable, char[] find) {
				PasswordNode passwordPtr = inHashTable(hashTable, find);
				if (passwordPtr == null) {
						int passwordCode = oaat(find, find.length, NUM_BITS); 
						passwordPtr = new PasswordNode(find, 0);
						passwordPtr.next = hashTable[passwordCode];
						hashTable[passwordCode] = passwordPtr;
				}
				passwordPtr.total++;
		}

		public static int oaat(char[] key, int length, int numBits) {
				int hash = 0;
				for (int i = 0; i < length; i++) {
						hash += key[i];
						hash += (hash << 10);
						hash ^= (hash >> 6);
				}
				hash += (hash << 3);
				hash ^= (hash << 11);
				hash ^= (hash >> 15);
				return hash & ((1 << numBits) -1);
		}

		public static boolean compareCharArrays(char[] array1, char[] array2) {
				if (array1.length != array2.length) {
						return false;
				}
				for (int i = 0; i < array1.length; i++) {
						if (array1[i] != array2[i]) {
								return false;
						}
				}
				return true;
		}

		public static boolean alreadyAdded(String[] allSubstrings, String find) {
				for (int i = 0; i < allSubstrings.length; i++) {
						if (allSubstrings[i].equals(find)) {
								return true;
						}
				}
				return false;
		}

		public static void main(String[] args) {
				Scanner sc = new Scanner(System.in);
				int totalSubstrings;
				String[] allSubstrings = new String[100];
				int numOps = sc.nextInt();
				sc.nextLine();

				for (int op = 0; op < numOps; op++) {
						int opType = sc.nextInt();
						String password = sc.next();
						sc.nextLine();

						if (opType == 1) {
								totalSubstrings = 0;
								for (int i = 0; i < password.length(); i++) {
										for (int j = 1; j < password.length(); j++) {
												String substring = password.substring(i, j+1);
												if (!alreadyAdded(allSubstrings, substring)) {
														addToHashTable(hashTable, substring.toCharArray());
														allSubstrings[totalSubstrings] = substring;
														totalSubstrings++;
												}
										}
								}
						} else {
								PasswordNode passwordPtr = inHashTable(hashTable, password.toCharArray());
								if (passwordPtr == null) {
										System.out.println("0");
								} else {
										System.out.println(passwordPtr.total);
								}
						}
				}
				sc.close();
		}

}

