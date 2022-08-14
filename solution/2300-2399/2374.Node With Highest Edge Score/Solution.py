class Solution:
    def edgeScore(self, edges: List[int]) -> int:
        cnt = Counter()
        for i, v in enumerate(edges):
            cnt[v] += i
        ans = 0
        for i in range(len(edges)):
            if cnt[ans] < cnt[i]:
                ans = i
        return ans
