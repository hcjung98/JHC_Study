package algorithm.dijkstra;

/*X 지점에서 모이고 돌아감
 * X를 출발점으로 각 지점으로 가는 최단경로 dist1
 * */

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 파티연습 {

    static int T, N, M, X;
    static ArrayList<int[]>[] graph1, graph2;
    static long[] dist1, dist2;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/algorithm/sample/파티.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            X = Integer.parseInt(st.nextToken());

            graph1 = new ArrayList[N + 1];
            graph2 = new ArrayList[N + 1];

            for (int i = 1; i <= N; i++) {
                graph1[i] = new ArrayList<int[]>();
                graph2[i] = new ArrayList<int[]>();
            }
            dist1 = new long[N + 1];
            dist2 = new long[N + 1];

            Arrays.fill(dist1, Integer.MAX_VALUE);
            Arrays.fill(dist2, Integer.MAX_VALUE);

            for (int i = 1; i <= M; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());
                graph1[from].add(new int[]{to, cost});
                graph2[to].add(new int[]{from, cost});
            }

            PriorityQueue<long[]> queue = new PriorityQueue<>((o1, o2) -> Long.compare(o1[1], o2[1]));

            dist1[X] = 0;
            queue.add(new long[]{X, dist1[X]});
            while (!queue.isEmpty()) {
                long[] temp = queue.poll();
                int now = (int) temp[0];
                if (dist1[now] < temp[1]) {
                    continue;
                }

                for (int i = 0; i < graph1[now].size(); i++) {
                    int next = graph1[now].get(i)[0];
                    int cost = graph1[now].get(i)[1];
                    if (dist1[next] > dist1[now] + cost) {
                        dist1[next] = dist1[now] + cost;
                        queue.add(new long[]{next, dist1[next]});
                    }
                }
            }

            queue.clear();
            dist2[X] = 0;
            queue.add(new long[]{X, dist2[X]});
            while (!queue.isEmpty()) {
                long[] temp = queue.poll();
                int now = (int) temp[0];

                for (int i = 0; i < graph2[now].size(); i++) {
                    int next = graph2[now].get(i)[0];
                    int cost = graph2[now].get(i)[1];
                    if (dist2[next] > dist2[now] + cost) {
                        dist2[next] = dist2[now] + cost;
                        queue.add(new long[]{next, dist2[next]});
                    }
                }
            }

            int answer = -1;

            for (int i = 1; i <= N; i++) {
                if (i != X) {
                    answer = (int) Math.max(answer, dist1[i] + dist2[i]);
                }
            }
            bw.write("#" + tc + " " + answer);
            bw.newLine();
        }
        bw.flush();
        bw.close();
    }
}
