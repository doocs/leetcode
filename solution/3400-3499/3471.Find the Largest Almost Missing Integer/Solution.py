class Solution:
    def largestInteger(self, nums: List[int], k: int) -> int:
        def f(k: int) -> int:
            for i, x in enumerate(nums):
                if i != k and x == nums[k]:
                    return -1
            return nums[k]

        if k == 1:
            cnt = Counter(nums)
            return max((x for x, v in cnt.items() if v == 1), default=-1)
        if k == len(nums):
            return max(nums)
        return max(f(0), f(len(nums) - 1))
