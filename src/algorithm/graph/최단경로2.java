package algorithm.graph;

/*백준 : https://www.acmicpc.net/problem/1753*/

import java.io.*;
import java.util.*;

public class 최단경로2 {

    static int T, N, M, K;
    static ArrayList<int[]>[] graph;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/algorithm/dijkstra/최단경로.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

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

        long[] dist = new long[N + 1]; // 각 정점까지의 최단경로를 저장하는 배열
        for (int i = 1; i <= N; i++) {
            dist[i] = Integer.MAX_VALUE;
        }

        /*PriorityQueue<long[]> queue = new PriorityQueue<long[]>(new Comparator<long[]>() {
            @Override
            public int compare(long[] o1, long[] o2) {
                return Long.compare(o1[1], o2[1]);
            }
        });*/

        // 정점과 정점까지의 죄단경로 비용을 저장하는 Queue
        /*PriorityQueue<long[]> queue = new PriorityQueue<long[]>((o1, o2) ->{
            return Long.compare(o1[1], o2[1]); // 비용이 적은 정정을 먼저
        });*/

        PriorityQueue<long[]> queue = new PriorityQueue<>((o1, o2) -> Long.compare(o1[1], o2[1]));

        dist[K] = 0;
        queue.add(new long[]{K, dist[K]});

        while (!queue.isEmpty()) {
            long[] temp = queue.poll();
            int now = (int) temp[0]; // 정점
            if (dist[now] < temp[1]) { //
                continue;
            }

            for (int i = 0; i < graph[now].size(); i++) {
                int next = graph[now].get(i)[0];
                int cost = graph[now].get(i)[1];
                ;
                if (dist[next] > dist[now] + cost) {
                    dist[next] = dist[now] + cost;
                    queue.add(new long[]{next, dist[next]});
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                bw.write("INF \n");
            } else {
                bw.write(dist[i] + "\n");
            }
        }

        bw.flush();
        bw.close();
    }

}
