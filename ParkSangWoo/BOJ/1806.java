import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer stn;
	static int N, M, left, right, ans;
	static int[] arr;

	public static void main(String[] args) throws IOException {
		stn = new StringTokenizer(br.readLine());

		N = Integer.parseInt(stn.nextToken());
		M = Integer.parseInt(stn.nextToken());

		arr = new int[N + 1];

		ans = Integer.MAX_VALUE;
		stn = new StringTokenizer(br.readLine());
		loop: for (int i = 1; i <= N; i++) {
			arr[i] = arr[i - 1] + Integer.parseInt(stn.nextToken());

			right = i;
			if (arr[right] - arr[left] < M) {
				continue;
			}
			while (left++ < right) {
				if (arr[right] - arr[left] < M) {
					ans = Math.min(ans, right - left + 1);
					if (ans == 1) {
						break loop;
					}
					break;
				}
			}
		}
		if (ans == Integer.MAX_VALUE) {
			ans = 0;
		}
		bw.write(Integer.toString(ans));
		bw.flush();
		bw.close();
		br.close();
	}
}
