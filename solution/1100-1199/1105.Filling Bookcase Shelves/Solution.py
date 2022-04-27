class Solution:
    def minHeightShelves(self, books: List[List[int]], shelfWidth: int) -> int:
        n = len(books)
        dp = [0] * (n + 1)
        dp[1] = books[0][1]
        for i in range(2, n + 1):
            dp[i] = books[i - 1][1] + dp[i - 1]
            w, h = books[i - 1][0], books[i - 1][1]
            for j in range(i - 1, 0, -1):
                w += books[j - 1][0]
                if w > shelfWidth:
                    break
                h = max(books[j - 1][1], h)
                dp[i] = min(dp[i], dp[j - 1] + h)
        return dp[n]
