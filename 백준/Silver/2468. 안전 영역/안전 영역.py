import sys
from collections import deque
input = sys.stdin.readline


N = int(input())
maps = [list(map(int, input().split())) for _ in range(N)]
dy, dx = (1, 0, -1, 0), (0, 1, 0, -1)
answer = -1e9

max_v = 0
for i in range(N):
    for j in range(N):
        max_v = max(max_v, maps[i][j])


idx = 0
while True:
    chk = [[False for _ in range(N)] for _ in range(N)]
    for i in range(N):
        for j in range(N):
            if maps[i][j] <= idx:
                chk[i][j] = True

    cnt = 0
    for i in range(N):
        for j in range(N):
            if not chk[i][j]:
                cnt += 1
                chk[i][j] = True

                q = deque()
                q.append([i, j])

                while q:
                    ey, ex = q.popleft()

                    for d in range(4):
                        ny, nx = ey + dy[d], ex + dx[d]
                        if 0 <= ny < N and 0 <= nx < N and not chk[ny][nx]:
                            q.append([ny, nx])
                            chk[ny][nx] = True

    if idx == max_v: break
    answer = max(answer, cnt)
    idx += 1

print(answer)