class Solution:
    def minimumRemoval(self, beans: List[int]) -> int:
        beans.sort()
        ans = s = sum(beans)
        n = len(beans)
        for i, v in enumerate(beans):
            ans = min(ans, s - v * (n - i))
        return ans
