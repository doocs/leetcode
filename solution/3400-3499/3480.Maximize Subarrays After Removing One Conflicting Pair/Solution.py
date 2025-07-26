class Solution:
    def maxSubarrays(self, n: int, conflictingPairs: List[List[int]]) -> int:
        g = [[] for _ in range(n + 1)]
        for a, b in conflictingPairs:
            if a > b:
                a, b = b, a
            g[a].append(b)
        cnt = [0] * (n + 2)
        ans = add = 0
        b1 = b2 = n + 1
        for a in range(n, 0, -1):
            for b in g[a]:
                if b < b1:
                    b2, b1 = b1, b
                elif b < b2:
                    b2 = b
            ans += b1 - a
            cnt[b1] += b2 - b1
            add = max(add, cnt[b1])
        ans += add
        return ans
