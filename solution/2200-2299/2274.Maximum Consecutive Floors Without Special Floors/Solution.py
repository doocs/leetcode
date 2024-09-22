class Solution:
    def maxConsecutive(self, bottom: int, top: int, special: List[int]) -> int:
        special.sort()
        ans = max(special[0] - bottom, top - special[-1])
        for x, y in pairwise(special):
            ans = max(ans, y - x - 1)
        return ans
