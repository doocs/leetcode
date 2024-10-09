class Solution:
    def edgeScore(self, edges: List[int]) -> int:
        ans = 0
        cnt = [0] * len(edges)
        for i, j in enumerate(edges):
            cnt[j] += i
            if cnt[ans] < cnt[j] or (cnt[ans] == cnt[j] and j < ans):
                ans = j
        return ans
