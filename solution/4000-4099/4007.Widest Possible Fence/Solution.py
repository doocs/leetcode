class Solution:
    def maximumWidth(self, planks: list[int]) -> int:
        cnt = Counter(planks)
        ans = 0
        t = defaultdict(int)
        for x, v1 in cnt.items():
            t[x] += v1
            t[x * 2] += v1 // 2
            for y, v2 in cnt.items():
                if y > x:
                    t[x + y] += min(v1, v2)
        return max(t.values())
