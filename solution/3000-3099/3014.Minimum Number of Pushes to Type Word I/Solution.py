class Solution:
    def minimumPushes(self, word: str) -> int:
        n = len(word)
        ans, k = 0, 1
        for _ in range(n // 8):
            ans += k * 8
            k += 1
        ans += k * (n % 8)
        return ans
