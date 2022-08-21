package algorithm.dijkstra;

/*백준 : https://www.acmicpc.net/problem/1753*/
/*다익스트라 구현
* Queue를 이용하여 방문하지 않은 모든 정점을 탐색
* */
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 최단경로1 {

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
        boolean[] visited = new boolean[N + 1];
        for (int i = 1; i <= N; i++) {
            dist[i] = Integer.MAX_VALUE;
        }

        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(K);
        dist[K] = 0;

        while (!queue.isEmpty()) {
            int now = queue.poll();
            visited[now] = true;

            for (int i = 0; i < graph[now].size(); i++) {
                int next = graph[now].get(i)[0];
                int cost = graph[now].get(i)[1];
                if(dist[next] > dist[now] + cost){
                    dist[next] = dist[now] + cost;
                }
            }
            // 다음정점 이동을 위한 로직, 방문하지 않은 정점 중에서 dist 값이 작은 값을 찾아서 큐에 삽입
            // 다음에 방문할 정점
            int max = Integer.MAX_VALUE;
            int maxIndex = -1;
            for (int i = 1; i <= N ; i++) {
                if(visited[i] == false && max > dist[i]){
                    max = (int)dist[i];
                    maxIndex = i;
                }
            }
            if(maxIndex != -1){
                queue.add(maxIndex);
            }
        }

        for (int i = 1; i <= N; i++) {
            if(dist[i] == Integer.MAX_VALUE){
                bw.write("INF \n");
            }else{
                bw.write(dist[i] + "\n");
            }
        }

        bw.flush();
        bw.close();
    }

}
