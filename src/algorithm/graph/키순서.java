package algorithm.graph;

import java.io.*;
import java.util.StringTokenizer;

/*
 *  N <= 500 이므로 O(N^3)
 *  그래프 문제, 모든 쌍
 * => 플로이드 워셜
 * */
public class 키순서 {

    static int T, N, M;
    static boolean[][] graph;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/algorithm/sample/키순서.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            graph = new boolean[N + 1][N + 1];

            for (int j = 1; j <= M; j++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());

                graph[from][to] = true;
            }

            for (int k = 1; k <= N; k++) {
                for (int i = 1; i <= N; i++) {
                    for (int j = 1; j <= N; j++) {
                        if (graph[i][k] && graph[k][j]) {
                            graph[i][j] = graph[i][k] & graph[k][j];
                        }
                    }
                }
            }

            int cnt = 0;
            for (int i = 1; i <= N; i++) {
                boolean possible = true;
                for (int j = 1; j <= N; j++) {
                    if (i == j) {
                        continue;
                    }
                    if (graph[i][j] == false && graph[j][i] == false) {
                        possible = false;
                    }
                }
                if (possible == true) {
                    cnt++;
                }
            }
            bw.write("#" + tc + " " + cnt);
            bw.newLine();
        }
        bw.flush();
        bw.close();

    }
}
