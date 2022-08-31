package algorithm.graph;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 최단경로NEW {

    static int T, N, M;
    static ArrayList<int[]>[] graph;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/algorithm/sample/최단경로NEW.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            graph = new ArrayList[N + 1];
            for (int i = 1; i <= N; i++) {
                graph[i] = new ArrayList<int[]>();
            }

            for (int i = 1; i <= M; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());
                graph[from].add(new int[]{to, cost});
                graph[to].add(new int[]{from, cost});
            }

            long[] dist = new long[N + 1];
            Arrays.fill(dist, Integer.MAX_VALUE);

            PriorityQueue<long[]> queue = new PriorityQueue<>((o1, o2) -> Long.compare(o1[1], o2[1]));

            dist[1] = 0;
            queue.add(new long[]{1, dist[1]});

            while (!queue.isEmpty()) {
                long[] temp = queue.poll();
                int now = (int) temp[0];
                if (dist[now] < temp[1]) {
                    continue;
                }

                if(now == N){
                    break;
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
            if (dist[N] == Integer.MAX_VALUE) {
                bw.write("#" + tc + " " + "-1");
            } else {
                bw.write("#" + tc + " " + dist[N]);
            }
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }
}
