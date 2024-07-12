class Solution:
    def minimumKeypresses(self, s: str) -> int:
        cnt = Counter(s)
        ans, k = 0, 1
        for i, x in enumerate(sorted(cnt.values(), reverse=True), 1):
            ans += k * x
            if i % 9 == 0:
                k += 1
        return ans
