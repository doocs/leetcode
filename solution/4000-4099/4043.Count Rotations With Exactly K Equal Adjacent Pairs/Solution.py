class Solution:
    def countRotations(self, s: str, k: int) -> int:
        n = len(s)
        score = sum(a == b for a, b in pairwise(s))
        ans = int(score == k)

        for i in range(n, n * 2 - 1):
            score += int(s[i % n] == s[(i - 1) % n])
            score -= int(s[i % n] == s[(i + 1) % n])
            ans += int(score == k)

        return ans
