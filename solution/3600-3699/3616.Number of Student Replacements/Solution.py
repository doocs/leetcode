class Solution:
    def totalReplacements(self, ranks: List[int]) -> int:
        ans, cur = 0, ranks[0]
        for x in ranks:
            if x < cur:
                cur = x
                ans += 1
        return ans
