import java.io.*;
import java.util.*;

public class Main {
		static final int MAX_LINES = 100;

		public static int shortest_line_index(int[] lines, int n) {
				int shortest = 0;
				for (int j = 1; j < n; j++) {
						if (lines[j] < lines[shortest]) {
								shortest = j;
						}
				}
				return shortest;
		}

		public static void solve(int[] lines, int n, int m) {
				for (int i = 0; i < m; i++) {
						int shortest = shortest_line_index(lines, n);
						System.out.println(lines[shortest]);
						lines[shortest]++;
				}
		}

		public static void main(String[] args) throws Exception {
				Scanner sc = new Scanner(System.in);
				int[] lines = new int[MAX_LINES];

				int n = sc.nextInt();
				int m = sc.nextInt();

				for (int i = 0; i < n; i++) {
						lines[i] = sc.nextInt();
				}

				solve(lines, n, m);
				sc.close();
		}
}
