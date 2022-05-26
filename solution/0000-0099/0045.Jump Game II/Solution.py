class Solution:
    def jump(self, nums: List[int]) -> int:
        end = mx = steps = 0
        for i, num in enumerate(nums[:-1]):
            mx = max(mx, i + num)
            if i == end:
                end = mx
                steps += 1
        return steps
