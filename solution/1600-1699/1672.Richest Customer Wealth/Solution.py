class Solution:
    def maximumWealth(self, accounts: List[List[int]]) -> int:
        res = 0
        for account in accounts:
            res = max(res, sum(account))
        return res
