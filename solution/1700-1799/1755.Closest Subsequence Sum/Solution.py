class Solution:
    def minAbsDifference(self, nums: List[int], goal: int) -> int:
        n = len(nums)
        left = set()
        right = set()

        self.getSubSeqSum(0, 0, nums[: n // 2], left)
        self.getSubSeqSum(0, 0, nums[n // 2 :], right)

        result = inf
        right = sorted(right)
        rl = len(right)

        for l in left:
            remaining = goal - l
            idx = bisect_left(right, remaining)

            if idx < rl:
                result = min(result, abs(remaining - right[idx]))

            if idx > 0:
                result = min(result, abs(remaining - right[idx - 1]))

        return result

    def getSubSeqSum(self, i: int, curr: int, arr: List[int], result: Set[int]):
        if i == len(arr):
            result.add(curr)
            return

        self.getSubSeqSum(i + 1, curr, arr, result)
        self.getSubSeqSum(i + 1, curr + arr[i], arr, result)
