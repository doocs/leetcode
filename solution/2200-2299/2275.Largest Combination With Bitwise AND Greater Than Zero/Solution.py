class Solution:
    def largestCombination(self, candidates: List[int]) -> int:
        ans = 0
        for i in range(max(candidates).bit_length()):
            ans = max(ans, sum(x >> i & 1 for x in candidates))
        return ans
