package algorithm.indextree;

import java.io.*;
import java.util.StringTokenizer;

public class 최대와최소연습 {

    static int T, N, Q;
    static int leafSize, leafNode;
    static int[] maxTree, minTree;

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("src/algorithm/sample/최대와최소.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());

        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            Q = Integer.parseInt(st.nextToken());

            leafSize = 1;
            while (leafSize < N) {
                leafSize *= 2;
            }
            leafNode = leafSize - 1;
            maxTree = new int[leafSize * 2];
            minTree = new int[leafSize * 2];

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= N; i++) {
                int val = Integer.parseInt(st.nextToken());
                maxTree[i + leafNode] = val;
                minTree[i + leafNode] = val;
            }
            init();
            int maxAns = 0;
            int minAns = 0;

            for (int i = 1; i <= Q; i++) {
                st = new StringTokenizer(br.readLine());
                int q = Integer.parseInt(st.nextToken());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                if (q == 0) {
                    maxAns += maxQuery(a, b);
                    minAns += minQuery(a, b);
                } else {
                    update(a, b);
                }
            }
            System.out.println("#" + tc + " " + maxAns + " " + minAns);
        }

    }
    private static void init() {
        int i = leafNode;
        while (i >= 1) {
            maxTree[i] = Math.max(maxTree[i * 2], maxTree[(i * 2) + 1]);
            minTree[i] = Math.min(minTree[i * 2], minTree[(i * 2) + 1]);
            i--;
        }
    }
    private static void update(int idx, int val) {
        int i = idx + leafNode;
        maxTree[i] = val;
        minTree[i] = val;
        i /= 2;
        while (i >= 1) {
            maxTree[i] = Math.max(maxTree[i * 2], maxTree[(i * 2) + 1]);
            minTree[i] = Math.min(minTree[i * 2], minTree[(i * 2) + 1]);
            i /= 2;
        }
    }

    private static int minQuery(int left, int right) {
        left += leafNode;
        right += leafNode;

        int result = Integer.MAX_VALUE;

        while (left <= right) {
            if (left % 2 == 1) {
                result = Math.min(result, minTree[left]);
            }
            if (right % 2 == 0) {
                result = Math.min(result, minTree[right]);
            }
            left = (left + 1) / 2;
            right = (right - 1) / 2;
        }
        return result;

    }

    private static int maxQuery(int left, int right) {
        left += leafNode;
        right += leafNode;

        int result = Integer.MIN_VALUE;

        while (left <= right) {
            if (left % 2 == 1) {
                result = Math.max(result, maxTree[left]);
            }
            if (right % 2 == 0) {
                result = Math.max(result, maxTree[right]);
            }
            left = (left + 1) / 2;
            right = (right - 1) / 2;
        }
        return result;
    }


}
