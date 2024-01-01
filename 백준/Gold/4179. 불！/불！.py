import sys
from collections import deque

input = sys.stdin.readline


R, C = map(int, input().split())
maps = [list(map(str, input().strip())) for _ in range(R)]

chk = [[False] * C for _ in range(R)]
dy, dx = (1, -1, 0, 0), (0, 0, 1, -1)

fire_list = []
for i in range(R):
    for j in range(C):
        if maps[i][j] == 'J':
            jihoon_y, jihoon_x = i, j
        elif maps[i][j] == 'F':
            fire_list.append((i, j))
            chk[i][j] = True



def bfs():
    q = deque()
    fire = deque()
    q.append((jihoon_y, jihoon_x, 0))

    chk[jihoon_y][jihoon_x] = True
    for i in range(len(fire_list)):
        y, x = fire_list[i]
        fire.append((y, x, 0))

    idx = 0

    # 지훈, 불 순서대로 탐색, 지훈 큐가 없다면 모두 종료
    while q:
        idx += 1

        if fire:
            while fire:
                if fire[0][2] < idx:
                    ey, ex, cnt = fire.popleft()
                    for d in range(4):
                        ny, nx = ey + dy[d], ex + dx[d]
                        if 0 <= ny < R and 0 <= nx < C and chk[ny][nx] == False:
                            if maps[ny][nx] != '#':
                                chk[ny][nx] = True
                                maps[ny][nx] = 'F'
                                fire.append((ny, nx, cnt + 1))
                else: break

        while q:
            if q[0][2] < idx:
                ey, ex, cnt = q.popleft()
                if ey == 0 or ey == R - 1 or ex == 0 or ex == C - 1:
                    return cnt + 1

                for d in range(4):
                    ny, nx = ey + dy[d], ex + dx[d]
                    if 0 <= ny < R and 0 <= nx < C and chk[ny][nx] == False:
                        if maps[ny][nx] == '.':
                            chk[ny][nx] = True
                            maps[ny][nx] = 'J'
                            q.append((ny, nx, cnt + 1))
            else: break

    return 'IMPOSSIBLE'

print(bfs())