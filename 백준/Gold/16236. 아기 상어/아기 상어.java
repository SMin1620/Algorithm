import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int N, shark_y, shark_x, size = 2, eat = 0, answer = 0;
    static int[][] maps;
    static int[] dy = {1, 0, -1, 0}, dx = {0, 1, 0, -1};

    static class Fish implements Comparable<Fish> {
        int y, x, time;

        Fish(int y, int x, int time) {
            this.y = y;
            this.x = x;
            this.time = time;
        }

        @Override
        public int compareTo(Fish o) {
            if (this.time != o.time) return Integer.compare(this.time, o.time);
            if (this.y != o.y) return Integer.compare(this.y, o.y);
            return Integer.compare(this.x, o.x);
        }
    }

    static ArrayList<Fish> bfs(int y, int x) {
        Queue<Fish> q = new LinkedList<>();
        q.add(new Fish(y, x, 0));

        boolean[][] chk = new boolean[N][N];
        chk[y][x] = true;

        ArrayList<Fish> dist = new ArrayList<>();
        while (!q.isEmpty()) {
            Fish now = q.poll();

            for (int d = 0; d < 4; d++) {
                int ny = now.y + dy[d];
                int nx = now.x + dx[d];

                if (ny >= 0 && ny < N && nx >= 0 && nx < N && !chk[ny][nx]) {
                    if (maps[ny][nx] == 0 || maps[ny][nx] == size) {
                        chk[ny][nx] = true;
                        q.add(new Fish(ny, nx, now.time + 1));
                    } else if (maps[ny][nx] < size && maps[ny][nx] != 0) {
                        chk[ny][nx] = true;
                        dist.add(new Fish(ny, nx, now.time + 1));
                    }
                }
            }
        }

        Collections.sort(dist);
        return dist;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        maps = new int[N][N];

        for (int i = 0; i < N; i++) {
            String[] line = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                maps[i][j] = Integer.parseInt(line[j]);
                if (maps[i][j] == 9) {
                    shark_y = i;
                    shark_x = j;
                    maps[i][j] = 0;
                }
            }
        }

        while (true) {
            ArrayList<Fish> fish = bfs(shark_y, shark_x);

            if (fish.size() == 0) break;

            Fish now = fish.get(0);
            maps[now.y][now.x] = 0;

            answer += now.time;

            eat += 1;
            if (eat == size) {
                size += 1;
                eat = 0;
            }

            shark_y = now.y;
            shark_x = now.x;
        }

        System.out.println(answer);
    }
}