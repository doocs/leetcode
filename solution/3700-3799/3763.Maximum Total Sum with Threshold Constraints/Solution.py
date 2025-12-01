class Solution:
    def maxSum(self, nums: List[int], threshold: List[int]) -> int:
        n = len(nums)
        idx = sorted(range(n), key=lambda i: threshold[i])
        sl = SortedList()
        step = 1
        ans = i = 0
        while True:
            while i < n and threshold[idx[i]] <= step:
                sl.add(nums[idx[i]])
                i += 1
            if not sl:
                break
            ans += sl.pop()
            step += 1
        return ans
