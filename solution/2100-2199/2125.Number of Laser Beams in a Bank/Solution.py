class Solution:
    def numberOfBeams(self, bank: list[str]) -> int:
        ans = pre = 0
        for row in bank:
            if (cur := row.count("1")) > 0:
                ans += pre * cur
                pre = cur
        return ans
