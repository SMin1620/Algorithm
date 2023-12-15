import sys
input = sys.stdin.readline

# input
maps = [list(map(int, input().split())) for _ in range(5)]
chk = [[False for _ in range(5)] for _ in range(5)]
dy, dx = (1, 0, -1, 0), (0, 1, 0, -1)

# dfs
def dfs(y, x, s, cnt):
    global answer

    # 종료조건
    if cnt == 6:
        answer.append(s)
        return

    for d in range(4):
        ny, nx = y + dy[d], x + dx[d]

        if 0 <= ny < 5 and 0 <= nx < 5:
            dfs(ny, nx, s + str(maps[ny][nx]), cnt + 1)



# logic
answer = []
for i in range(5):
    for j in range(5):
        num = str(maps[i][j])

        dfs(i, j, num, 1)


print(len(set(answer)))