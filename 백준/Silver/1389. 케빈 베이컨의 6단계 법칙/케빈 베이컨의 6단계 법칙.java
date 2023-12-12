import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<Integer>[] maps = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) maps[i] = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            maps[a].add(b);
            maps[b].add(a);
        }

        int answer = 0;
        int minKevin = Integer.MAX_VALUE;

        for (int i = 1; i <= N; i++) {
            int totalKevin = bfs(i, N, maps);

            if (totalKevin < minKevin) {
                minKevin = totalKevin;
                answer = i;
            }
        }

        System.out.println(answer);
    }

    private static int bfs(int start, int N, List<Integer>[] maps) {
        Queue<int[]> q = new ArrayDeque<>();
        boolean[] visited = new boolean[N + 1];

        q.offer(new int[]{start, 0});
        visited[start] = true;

        int totalKevin = 0;

        while (!q.isEmpty()) {
            int[] data = q.poll();
            int current = data[0];
            int cnt = data[1];

            totalKevin += cnt;

            for (int next : maps[current]) {
                if (!visited[next]) {
                    q.offer(new int[]{next, cnt + 1});
                    visited[next] = true;
                }
            }
        }

        return totalKevin;
    }
}