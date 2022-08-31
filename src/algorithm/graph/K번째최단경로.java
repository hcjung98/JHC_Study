package algorithm.graph;
/*dist배열을 PriorityQueue로 정의하고 내림차순 정렬 후 K 개수만큼만 관리
* 최종 계산 후 큐에서 peek하면 그게 K번째 값*/
import java.io.*;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class K번째최단경로 {

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
                graph[i] = new ArrayList<int[]>();
            }

            for (int i = 1; i <= M; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());
                graph[from].add(new int[]{to, cost});
            }

            /*dist[i] => i 도시로 가는 최단경로를 저장하는 큐, 큰 순서순으로 저장, 최대 K까지만...
             * 최종 계산 후 첫번째 값이 K번째 값*/

            PriorityQueue<Integer>[] dist = new PriorityQueue[N + 1];
            for (int i = 1; i <= N; i++) {
                dist[i] = new PriorityQueue<Integer>((o1, o2) -> {
                    return Integer.compare(o2, o1); // 큰값으로 정렬, 내림차순 정렬
                });
            }

            PriorityQueue<long[]> queue = new PriorityQueue<>((o1, o2) -> {
                return Long.compare(o1[1], o2[1]);
            });

            dist[1].add(0); // 1번에서 1번으로 가는 최단경로 큐
            queue.add(new long[]{1, dist[1].peek()}); // 시작점 입력

            while (!queue.isEmpty()) {
                long[] temp = queue.poll();
                int now = (int) temp[0]; // 현재의 정점
                int nowCost = (int) temp[1]; // 현재까지의 최단경로 비용

                for (int i = 0; i < graph[now].size(); i++) { // 현재 정점에서 갈수 있는 모든 간선 조회
                    int next = graph[now].get(i)[0]; // 현재 정점에서 출발하는 연결된 정점
                    int nextCost = graph[now].get(i)[1]; // 연결된 정정까지 이동하는 비용

                    if (dist[next].size() < K) { // 큐사이즈 K보다 작으면 저장, 우선순위 큐이므로 알아서 순위 조정, 제일 큰 값이 위로...내림차순
                        dist[next].add(nowCost + nextCost); // 연결된 정점까지의 비용을 저장 (현재 정점까지의 최단 비용 + 이동비용)
                        queue.add(new long[]{next, nowCost + nextCost}); // 다음 탐색을 위해 큐에 저장
                    } else if (dist[next].peek() > nowCost + nextCost) {
                        // 큐가 K보다 크거나 같을때, peek한 값 (K번째 값)이 계산값보다 크면 큐에서 빼고 새로운 값을 큐에 저장
                        // 계산한 값이 peek한 값보다 크면 K번째 값은 유지되므로 무시...
                        dist[next].poll(); // 큐에서 빼고
                        dist[next].add(nowCost + nextCost); // 저장
                        queue.add(new long[]{next, nowCost + nextCost});
                    }
                }
            }
            for (int i = 1; i <= N; i++) {
                if (dist[i].size() < K) { // K번째 값 없는 경우
                    bw.write("#" + tc + " " + "-1");
                } else {
                    bw.write("#" + tc + " " + dist[i].peek() + "");
                }
                bw.newLine();
            }
        }
        bw.flush();
        bw.close();
    }
}
