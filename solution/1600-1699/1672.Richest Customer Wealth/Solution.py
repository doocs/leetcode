class Solution:
    def maximumWealth(self, accounts: list[list[int]]) -> int:
        return max(sum(v) for v in accounts)
