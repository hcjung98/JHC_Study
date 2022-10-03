package algorithm.graph;

import java.io.*;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class K번째최단경로연습 {

    static int T, N, M, K;
    static ArrayList<int[]>[] graph;


    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/algorithm/sample/K번째최단경로.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            graph = new ArrayList[N + 1];
            for (int i = 1; i <= N; i++) {
                graph[i] = new ArrayList<>();
            }

            for (int i = 1; i <= M; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());

                graph[from].add(new int[]{to, cost});
            }

            //dist 정의
            PriorityQueue<Integer>[] dist = new PriorityQueue[N + 1];
            for (int i = 1; i <= N; i++) {
                dist[i] = new PriorityQueue<>((o1, o2) -> Integer.compare(o2, o1));
            }

            PriorityQueue<long[]> queue = new PriorityQueue<>((o1, o2) -> Long.compare(o1[1], o2[1]));

            dist[1].add(0); // 시작점
            queue.add(new long[]{1, dist[1].peek()});

            while (!queue.isEmpty()) {
                long[] temp = queue.poll();
                int now = (int) temp[0];
                int nowCost = (int) temp[1];

                for (int i = 0; i < graph[now].size(); i++) {
                    int next = graph[now].get(i)[0];
                    int nextCost = graph[now].get(i)[1];
                    /*최단 경로를 찾는게 아니기 때문에 모든 경로의 값을 계산해서 큐에 저장
                     * 다만, K번째 값을 찾기위해 큐 사이즈는 내림차순 기준으로 K개 만큼만 유지*/
                    if (dist[next].size() < K) {
                        dist[next].add(nowCost + nextCost);
                        queue.add(new long[]{next, nowCost + nextCost});
                    } else if (dist[next].peek() > nowCost + nextCost) {
                        dist[next].poll();
                        dist[next].add(nowCost + nextCost);
                        queue.add(new long[]{next, nowCost + nextCost});
                    }
                }
            }

            /*for (int i = 1; i <= N; i++) {
                if (dist[i].size() < K) {
                    bw.write("#" + tc + " " + "-1");
                } else {
                    bw.write("#" + tc + " " + dist[i].peek());
                }
                bw.newLine();
            }*/
            if(dist[N].size() < K){
                bw.write("#" + tc + " " + "-1");
            }else{
                bw.write("#" + tc + " " + dist[N].peek());
            }
        }
        bw.flush();
        bw.close();
    }
}
