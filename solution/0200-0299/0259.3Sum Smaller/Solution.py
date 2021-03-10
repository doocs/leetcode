class Solution:
    def threeSumSmaller(self, nums: List[int], target: int) -> int:
        def threeSumSmaller(nums, start, end, target):
            count = 0
            while start < end:
                if nums[start] + nums[end] < target:
                    count += (end - start)
                    start += 1
                else:
                    end -= 1
            return count
            
        nums.sort()
        n, count = len(nums), 0
        for i in range(n - 2):
            count += threeSumSmaller(nums, i + 1, n - 1, target - nums[i])
        return count
