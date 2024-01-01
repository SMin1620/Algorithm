import sys
from collections import deque
from copy import deepcopy
input = sys.stdin.readline


def down(new_maps):
    cnt = 0
    # 맨 아래의 적은 성으로 입장하기에 0으로 초기화
    for i in range(M):
        if new_maps[N][i] == 1:
            new_maps[N][i] = 0
            cnt += 1

    for i in range(M):
        idx = N

        while True:
            idx -= 1
            if new_maps[idx][i] == 1:
                new_maps[idx + 1][i] = 1
                new_maps[idx][i] = 0

            if idx == 0: break

    return cnt, new_maps

def bfs(x, new_maps):
    q = deque()
    q.append((N, x))

    chk = [[False] * M for _ in range(N + 1)]
    chk[N][x] = True

    opens = []
    while q:
        ey, ex = q.popleft()

        for d in range(4):
            ny, nx = ey + dy[d], ex + dx[d]
            if abs(N - ny) + abs(x - nx) <= D:
                if 0 <= ny < N and 0 <= nx < M and chk[ny][nx] == False:
                    chk[ny][nx] = True
                    if new_maps[ny][nx] == 1:
                        opens.append((abs(N - ny) + abs(x - nx), ny, nx))
                    else:
                        q.append((ny, nx))

    return sorted(opens, key=lambda x: (x[0], x[2]))


def dfs(depth, idx):
    global answer

    if depth == 3:
        new_maps = deepcopy(maps)

        count = N * M - zero
        attack_cnt = 0

        while True:
            result = []
            # 적 타겟
            for x in archer:
                r = bfs(x, new_maps)
                if r:
                    result += [r.pop(0)]

            # 적 사살
            for dist, y, x in result:
                if new_maps[y][x] == 1:
                    new_maps[y][x] = 0
                    attack_cnt += 1
                    count -= 1

            # 적이 아래로 이동
            cnt, new_maps = down(new_maps)
            count -= cnt

            # 종료 지점
            if count == 0:
                break
        answer = max(answer, attack_cnt)

        return

    for i in range(idx, M):
        archer.append(i)
        dfs(depth + 1, i + 1)
        archer.pop()


N, M, D = map(int, input().split())
maps = [list(map(int, input().split())) for _ in range(N)]
maps.append([-1 for i in range(M)])
chk = [False] * M
dy, dx = (1, 0, -1, 0), (0, 1, 0, -1)

zero = 0
for i in range(N):
    for j in range(M):
        if maps[i][j] == 0:
            zero += 1


answer = 0
archer = []
dfs(0, 0)

print(answer)

