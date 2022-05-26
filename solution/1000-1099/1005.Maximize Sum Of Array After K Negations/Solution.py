class Solution:

    def largestSumAfterKNegations(self, A: List[int], K: int) -> int:
        m = float('inf')
        ans = 0
        fu = []
        for num in A:
            if num < 0:
                fu.append(num)
                m = min(m, -num)
            else:
                ans += num
                m = min(m, num)
        if K >= len(fu):
            K -= len(fu)
            ans -= sum(fu)
            if K % 2 == 1:
                ans -= 2 * m
        else:
            fu.sort()
            ans = ans - sum(fu[:K]) + sum(fu[K:])
        return ans
