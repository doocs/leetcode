class Solution:
    def numTeams(self, rating: List[int]) -> int:
        n, ans = len(rating), 0
        for j in range(1, n - 1):
            ia = ib = ka = kb = 0
            for i in range(j):
                if rating[i] < rating[j]:
                    ia += 1
                elif rating[i] > rating[j]:
                    ib += 1
            for k in range(j + 1, n):
                if rating[j] < rating[k]:
                    ka += 1
                elif rating[j] > rating[k]:
                    kb += 1
            ans += ia * ka + ib * kb
        return ans
