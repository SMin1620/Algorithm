import sys
input = sys.stdin.readline
from collections import deque

# input
N, M, K = map(int, input().split())
maps = [['.' for _ in range(M)] for _ in range(N)]
chk = [[True for _ in range(M)] for _ in range(N)]
dy, dx = (1, 0, -1, 0), (0, 1, 0, -1)


for _ in range(K):
    r, c = map(int, input().split())
    maps[r - 1][c - 1] = '#'
    chk[r - 1][c - 1] = False

# login
answer = 0
for i in range(N):
    for j in range(M):
        if not chk[i][j] and maps[i][j] == '#':
            q = deque();
            q.append([i, j, 1])
            chk[i][j] = True

            total = 1

            while q:
                ey, ex, cnt = q.popleft()
                answer = max(answer, cnt)

                for d in range(4):
                    ny, nx = ey + dy[d], ex + dx[d]
                    if 0 <= ny < N and 0 <= nx < M and not chk[ny][nx] and maps[ny][nx] == '#':
                        chk[ny][nx] = True
                        q.append([ny, nx, cnt + 1])
                        total += 1

            answer = max(answer, total)

print(answer)