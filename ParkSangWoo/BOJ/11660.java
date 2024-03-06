import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringTokenizer stn;
	static StringBuilder sb = new StringBuilder();
	static int N, M, temp, rowFrom, colFrom, rowTo, colTo, sum;
	static int[][] board;

	public static void main(String[] args) throws IOException {
		stn = new StringTokenizer(br.readLine());
		N = Integer.parseInt(stn.nextToken());
		M = Integer.parseInt(stn.nextToken());

		board = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			stn = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				temp = Integer.parseInt(stn.nextToken());
				board[i][j] = board[i][j - 1] + temp;
			}
		}
		for (int j = 1; j <= N; j++) {
			for (int i = 1; i <= N; i++) {
				board[i][j] += board[i - 1][j];
			}
		}

		for (int i = 0; i < M; i++) {
			stn = new StringTokenizer(br.readLine());

			rowFrom = Integer.parseInt(stn.nextToken());
			colFrom = Integer.parseInt(stn.nextToken());
			rowTo = Integer.parseInt(stn.nextToken());
			colTo = Integer.parseInt(stn.nextToken());

			sum = board[rowTo][colTo] + board[rowFrom - 1][colFrom - 1] - board[rowFrom - 1][colTo]
					- board[rowTo][colFrom - 1];

			sb.append(sum).append("\n");
		}

		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}

}
