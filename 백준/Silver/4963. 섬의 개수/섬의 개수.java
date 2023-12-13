import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        while (true) {

            // input
            st = new StringTokenizer(br.readLine());

            int w, h;
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            if (w == 0 && h == 0) break;

            int[][] maps = new int[h][w];
            boolean[][] chk = new boolean[h][w];
            for (int i = 0; i < h; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < w; j++) {
                    maps[i][j] = Integer.parseInt(st.nextToken());
                    if (maps[i][j] == 0) chk[i][j] = true;
                }
            }

            int[] dy = new int[] {1, 0, -1, 0, 1, 1, -1, -1};
            int[] dx = new int[] {0, 1, 0, -1, 1, -1, 1, -1};

            // logic
            int answer = 0;
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if (! chk[i][j]) {
                        // queue
                        Queue<int[]> q = new ArrayDeque<>();
                        q.offer(new int[] {i, j});
                        chk[i][j] = true;
                        answer++;

                        while (! q.isEmpty()) {
                            int[] data = q.poll();
                            int ey = data[0];
                            int ex = data[1];

                            for (int d = 0; d < 8; d++) {
                                int ny = ey + dy[d];
                                int nx = ex + dx[d];

                                if (ny >= h || nx >= w || ny < 0 || nx < 0) continue;

                                if (! chk[ny][nx]) {
                                    chk[ny][nx] = true;
                                    q.offer(new int[] {ny, nx});
                                }
                            }
                        }
                    }
                }
            }

            System.out.println(answer);
        }
    }
}