class Solution:
    def chalkReplacer(self, chalk: List[int], k: int) -> int:
        pre_sum = list(itertools.accumulate(chalk))
        k %= pre_sum[-1]
        left, right = 0, len(chalk) - 1
        while left < right:
            mid = (left + right) >> 1
            if pre_sum[mid] > k:
                right = mid
            else:
                left = mid + 1
        return left
