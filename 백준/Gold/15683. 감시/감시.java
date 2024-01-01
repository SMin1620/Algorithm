import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] maps;
    static int[][] copy;
    static List<int[]> cctv = new ArrayList<>();
    static List<E> cctvList = new ArrayList<>();
    static List<int[]> index = new ArrayList<>();
    static int[][] cctv1 = {{0}, {1}, {2}, {3}};
    static int[][] cctv2 = {{0, 2}, {1, 3}};
    static int[][] cctv3 = {{0, 1}, {1, 2}, {2, 3}, {3, 0}};
    static int[][] cctv4 = {{0, 1, 2}, {1, 2, 3}, {2, 3, 0}, {3, 0, 1}};
    static int[][] cctv5 = {{0, 1, 2, 3}};
    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, -1, 0, 1};
    static int[][] direct;
    static int answer = Integer.MAX_VALUE;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        maps = new int[N][M];
        copy = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++){
                maps[i][j] = Integer.parseInt(st.nextToken());
                if (maps[i][j] == 1 || maps[i][j] == 2 || maps[i][j] == 3 || maps[i][j] == 4 || maps[i][j] == 5) {
                    cctv.add(new int[] {i, j});
                }
            }
        }

        dfs(0);
        System.out.println(answer);

    }
    static void deepCopy() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                copy[i][j] = maps[i][j];
            }
        }
    }
    static void check(E data) {
        int ey = data.y;
        int ex = data.x;
        int[] ed = data.dir;

        for (int d : ed) {
            int ny = ey;
            int nx = ex;
            while (true) {
                ny = ny + dy[d];
                nx = nx + dx[d];

                if (ny >= 0 && ny < N && nx >= 0 && nx < M) {
                    if (copy[ny][nx] == 0 || copy[ny][nx] == -1) {
                        copy[ny][nx] = -1;
                        continue;
                    }
                    else if (copy[ny][nx] == 6) break;
                }
                else break;
            }
        }
    }
    static int sum() {
        int total = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (copy[i][j] == 0) {
                    total++;
                }
            }
        }

        return total;
    }
    static void dfs(int idx) {
        if (idx == cctv.size()) {
            deepCopy();
            for (int k = 0; k < cctvList.size(); k++) {
                check(cctvList.get(k));
            }

            int total = sum();
            answer = Math.min(answer, total);
            return;
        }

        int y = cctv.get(idx)[0];
        int x = cctv.get(idx)[1];

        if (maps[y][x] == 1) {
            direct = cctv1;
        }
        else if (maps[y][x] == 2) {
            direct = cctv2;
        }
        else if (maps[y][x] == 3) {
            direct = cctv3;
        }
        else if (maps[y][x] == 4) {
            direct = cctv4;
        }
        else direct = cctv5;

        for (int[] i : direct) {
            E e = new E(y, x, i);
            cctvList.add(e);
            dfs(idx + 1);
            cctvList.remove(e);
        }
    }
}
class E {
    int y;
    int x;
    int[] dir;

    public E(int y, int x, int[] dir) {
        this.y = y;
        this.x = x;
        this.dir = dir;
    }
}
