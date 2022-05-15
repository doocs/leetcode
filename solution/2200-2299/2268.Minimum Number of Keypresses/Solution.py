class Solution:
    def minimumKeypresses(self, s: str) -> int:
        cnt = Counter(s)
        ans = 0
        i, j = 0, 1
        for v in sorted(cnt.values(), reverse=True):
            i += 1
            ans += j * v
            if i % 9 == 0:
                j += 1
        return ans
