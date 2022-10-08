class Solution:
    def maximumWealth(self, accounts: List[List[int]]) -> int:
        return max(sum(v) for v in accounts)
