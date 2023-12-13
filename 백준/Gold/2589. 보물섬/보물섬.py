import sys
input = sys.stdin.readline
from collections import deque

# input
L, W = map(int, input().split())
maps = [list(map(str, input().strip())) for _ in range(L)]
dy, dx = (1, 0, -1, 0), (0, 1, 0, -1)

# logic
answer = 0
for i in range(L):
    for j in range(W):
        if maps[i][j] == "L":
            chk = [[False for _ in range(W)] for _ in range(L)]
            # for l in range(L):
            #     for w in range(W):
            #         if maps[l][w] == "W": chk[l][w] = True

            q = deque()
            q.append([i, j, 0])
            chk[i][j] = True

            while q:
                ey, ex, cnt = q.popleft()

                answer = max(answer, cnt)

                for d in range(4):
                    ny, nx = ey + dy[d], ex + dx[d]
                    if 0 <= ny < L and 0 <= nx < W and not chk[ny][nx] and maps[ny][nx] == "L":
                        chk[ny][nx] = True
                        q.append([ny, nx, cnt + 1])

print(answer)