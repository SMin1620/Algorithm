import sys
input = sys.stdin.readline
sys.setrecursionlimit(10**6)

# input
N, M, R = map(int, input().split())
maps = [[] for _ in range(N + 1)]
for _ in range(M):
    u, v = map(int, input().split())
    maps[u].append(v)
    maps[v].append(u)

for number in maps:
    number.sort()

idx = 1
answer = [0 for _ in range(N + 1)]

# logic
def dfs(start):
    global idx

    answer[start] = idx

    for num in maps[start]:
        if not answer[num]:
            idx += 1
            dfs(num)

dfs(R)
for n in answer[1:]:
    print(n)