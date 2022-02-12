class Solution:
    def numberOfBeams(self, bank: List[str]) -> int:
        last = ans = 0
        for b in bank:
            if (t := b.count('1')) > 0:
                ans += last * t
                last = t
        return ans
