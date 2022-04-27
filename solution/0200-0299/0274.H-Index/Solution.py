class Solution:
    def hIndex(self, citations: List[int]) -> int:
        n = len(citations)
        cnt = [0] * (n + 1)
        for c in citations:
            if c <= n:
                cnt[c] += 1
            else:
                cnt[n] += 1
        sum = 0
        for i in range(n, -1, -1):
            sum += cnt[i]
            if sum >= i:
                return i
        return 0
