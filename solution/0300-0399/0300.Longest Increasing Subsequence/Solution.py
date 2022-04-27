class Solution:
    def lengthOfLIS(self, nums: List[int]) -> int:
        d = [nums[0]]
        for x in nums[1:]:
            if x > d[-1]:
                d.append(x)
            else:
                idx = bisect_left(d, x)
                if idx == len(d):
                    idx = 0
                d[idx] = x
        return len(d)
