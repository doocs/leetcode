class Solution:
    def findLongestChain(self, pairs: List[List[int]]) -> int:
        ans, cur = 0, -inf
        for a, b in sorted(pairs, key=lambda x: x[1]):
            if cur < a:
                cur = b
                ans += 1
        return ans
