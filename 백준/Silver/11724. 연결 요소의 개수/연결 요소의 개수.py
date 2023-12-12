import sys
from collections import deque
input = sys.stdin.readline

N, M = map(int, input().split())
maps = [[] for _ in  range(N + 1)]
chk = [False for _ in range(N + 1)]
chk[0] = True

for _ in range(M):
    a, b = map(int, input().split())
    maps[a].append(b)
    maps[b].append(a)

answer = 0
for i in range(1, N + 1):
    if not chk[i]:
        answer += 1

        q = deque()
        q.append(i)
        chk[i] = True
        while q:
            e_node = q.popleft()

            for node in maps[e_node]:
                if not chk[node]:
                    chk[node] = True
                    q.append(node)

print(answer)