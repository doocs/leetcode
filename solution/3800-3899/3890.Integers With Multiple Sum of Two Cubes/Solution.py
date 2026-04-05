LIMIT = 10**9

cnt = defaultdict(int)
cubes = [i**3 for i in range(1001)]

for a in range(1, 1001):
    for b in range(a, 1001):
        x = cubes[a] + cubes[b]
        if x > LIMIT:
            break
        cnt[x] += 1

GOOD = sorted(x for x, v in cnt.items() if v > 1)


class Solution:
    def findGoodIntegers(self, n: int) -> list[int]:
        idx = bisect_right(GOOD, n)
        return GOOD[:idx]
