class Solution:
    def chalkReplacer(self, chalk: list[int], k: int) -> int:
        s = sum(chalk)
        k %= s
        for i, x in enumerate(chalk):
            if k < x:
                return i
            k -= x
