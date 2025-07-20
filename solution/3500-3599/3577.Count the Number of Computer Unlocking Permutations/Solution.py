class Solution:
    def countPermutations(self, complexity: List[int]) -> int:
        mod = 10**9 + 7
        ans = 1
        for i in range(1, len(complexity)):
            if complexity[i] <= complexity[0]:
                return 0
            ans = ans * i % mod
        return ans
