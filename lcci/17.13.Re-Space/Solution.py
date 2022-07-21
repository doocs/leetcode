class Solution:
    def respace(self, dictionary: List[str], sentence: str) -> int:
        s = set(dictionary)
        n = len(sentence)
        dp = [0] * (n + 1)
        for i in range(1, n + 1):
            dp[i] = dp[i - 1] + 1
            for j in range(i):
                if sentence[j:i] in s:
                    dp[i] = min(dp[i], dp[j])
        return dp[-1]
