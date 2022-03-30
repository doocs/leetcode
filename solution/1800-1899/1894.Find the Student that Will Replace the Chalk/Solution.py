class Solution:
    def chalkReplacer(self, chalk: List[int], k: int) -> int:
        s = list(accumulate(chalk))
        k %= s[-1]
        return bisect_right(s, k)
