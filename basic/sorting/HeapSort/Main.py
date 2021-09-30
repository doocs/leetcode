n, m = list(map(int, input().split(" ")))
h = [0] + list(map(int, input().split(" ")))

size = n


def down(u):
    t = u
    if u * 2 <= size and h[u * 2] < h[t]:
        t = u * 2
    if u * 2 + 1 <= size and h[u * 2 + 1] < h[t]:
        t = u * 2 + 1
    if t != u:
        h[t], h[u] = h[u], h[t]
        down(t)


def up(u):
    while u // 2 > 0 and h[u // 2] > h[u]:
        h[u // 2], h[u] = h[u], h[u // 2]
        u //= 2


for i in range(n // 2, 0, -1):
    down(i)

res = []
for i in range(m):
    res.append(h[1])
    h[1] = h[size]
    size -= 1
    down(1)

print(' '.join(list(map(str, res))))
