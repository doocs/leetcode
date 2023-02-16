class Solution:
    def getLastMoment(self, n: int, left: List[int], right: List[int]) -> int:
        ans = 0
        for x in left:
            ans = max(ans, x)
        for x in right:
            ans = max(ans, n - x)
        return ans
