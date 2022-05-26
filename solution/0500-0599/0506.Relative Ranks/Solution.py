class Solution:
    def findRelativeRanks(self, score: List[int]) -> List[str]:
        n = len(score)
        idx = list(range(n))
        idx.sort(key=lambda x: -score[x])
        top3 = ['Gold Medal', 'Silver Medal', 'Bronze Medal']
        ans = [None] * n
        for i in range(n):
            ans[idx[i]] = top3[i] if i < 3 else str(i + 1)
        return ans
