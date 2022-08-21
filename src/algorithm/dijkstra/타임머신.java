package algorithm.dijkstra;

/*https://www.acmicpc.net/problem/11657
 * 벨만-포드
 * 특정 출발점에서 각 정점으로의 최단거리, 음의 가중치*/

import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 타임머신 {

    static int T, N, M;
    static ArrayList<int[]> edgeList;
    static long dist[];
    static boolean visited[];

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/algorithm/dijkstra/타임머신.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            edgeList = new ArrayList<int[]>();

            for (int i = 1; i <= M; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());
                edgeList.add(new int[]{from, to, cost});
            }

            dist = new long[N + 1];
            visited = new boolean[N + 1];
            for (int i = 0; i <= N; i++) {
                dist[i] = Long.MAX_VALUE / 10000;
            }

            dist[1] = 0;
            visited[1] = true;

            //N-1번째 라운드
            for (int i = 1; i < N; i++) {
                boolean isChange = false;
                for (int[] edge : edgeList) {
                    int from = edge[0];
                    int to = edge[1];
                    int cost = edge[2];
                    if (visited[from] && dist[to] > dist[from] + cost) {
                        dist[to] = dist[from] + cost;
                        visited[to] = true;
                        isChange = true;
                    }
                }
                if (isChange == false) {
                    break;
                }
            }

            boolean isCycle = false;
            for (int[] edge : edgeList) {
                int from = edge[0];
                int to = edge[1];
                int cost = edge[2];
                if (visited[from] && dist[to] > dist[from] + cost) {
                    isCycle = true;
                    break;
                }
            }

            if (isCycle) {
                bw.write("#" + tc + " -1 \n");
            } else {
                for (int i = 2; i <= N; i++) {
                    if (!visited[i]) {
                        bw.write("#" + tc + " -1 \n");
                    } else {
                        bw.write("#" + tc + " " + dist[i] + "\n");
                    }
                }
            }
        }
        bw.flush();
        bw.close();
    }
}
