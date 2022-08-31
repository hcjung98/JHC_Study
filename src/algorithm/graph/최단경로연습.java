package algorithm.graph;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 최단경로연습 {

    static int T, N, M, K;
    static ArrayList<int[]>[] graph;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/algorithm/dijkstra/최단경로연습.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); // 정점
            M = Integer.parseInt(st.nextToken()); // 간선

            graph = new ArrayList[N + 1];
            for (int i = 1; i <= N; i++) {
                graph[i] = new ArrayList<int[]>();
            }

            st = new StringTokenizer(br.readLine());
            K = Integer.parseInt(st.nextToken()); // 시작점

            for (int i = 1; i <= M; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());

                graph[from].add(new int[]{to, cost});
            }

            long[] dist = new long[N + 1];
            Arrays.fill(dist, Integer.MAX_VALUE);

            /*PriorityQueue<long[]> queue = new PriorityQueue<long[]>((o1, o2) -> {
                return Long.compare(o1[1], o2[1]);
            });*/

            PriorityQueue<long[]> queue = new PriorityQueue<long[]>((o1, o2) -> Long.compare(o1[1], o2[1]));

            dist[K] = 0;
            queue.add(new long[]{K, dist[K]});

            while (!queue.isEmpty()) {
                long[] temp = queue.poll();
                int now = (int) temp[0];
                if (dist[now] < temp[1]) { // 현재점까지의 최단경로 계산값이 현재점까지의 이동비용보다 싸면 탐색안함
                    continue;
                }

                for (int i = 0; i < graph[now].size(); i++) {
                    int next = graph[now].get(i)[0];
                    int cost = graph[now].get(i)[1];

                    if (dist[next] > dist[now] + cost) {
                        dist[next] = dist[now] + cost;
                        queue.add(new long[]{next, dist[next]});
                    }
                }
            }
            for (int i = 1; i <= N; i++) {
                if (dist[i] == Integer.MAX_VALUE) {
                    bw.write("INF");
                } else {
                    bw.write(dist[i] + "");
                }
                bw.newLine();
            }
        }
        bw.flush();
        bw.close();
    }
}
