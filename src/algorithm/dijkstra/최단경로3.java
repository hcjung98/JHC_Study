package algorithm.dijkstra;

/*백준 : https://www.acmicpc.net/problem/1753*/
/*ArrayList에 배열말고 객체를 넣어서 모델링 하기*/

import java.io.*;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 최단경로3 {

    static int N, M, K;
    static ArrayList<Edge>[] edgeList;

    static class Edge {

        int to;
        int cost;

        Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/algorithm/dijkstra/최단경로.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        edgeList = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            edgeList[i] = new ArrayList<Edge>();
        }

        st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken()); // 시작점

        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            edgeList[from].add(new Edge(to, cost));
        }

        long dist[] = new long[N + 1];
        for (int i = 1; i <= N; i++) {
            dist[i] = Integer.MAX_VALUE;
        }

        PriorityQueue<Edge> queue = new PriorityQueue<Edge>((o1, o2) -> {
            return Long.compare(o1.cost, o2.cost);
        });
        dist[K] = 0;
        queue.add(new Edge(K, (int) dist[K]));

        while (!queue.isEmpty()){
            Edge curr = queue.poll();
            int now = curr.to;
            if(dist[now] < curr.cost){ // 해당 정점까지의 비용이 이동비용보다 작으면...해당 간선은 사용안함
                continue;
            }

            for (Edge edges : edgeList[now]) {
                int next = edges.to;
                int cost = edges.cost;
                if(dist[next] > dist[now] + cost){
                    dist[next] = dist[now] + cost;
                    queue.add(new Edge(next, (int) dist[next]));
                }
            }
        }

        for (int i = 1; i <= N ; i++) {
            if(dist[i] == Integer.MAX_VALUE){
                bw.write("INF");
                bw.newLine();
            }else{
                bw.write(dist[i] + "");
                bw.newLine();
            }
        }
        bw.flush();
        bw.close();
    }
}
