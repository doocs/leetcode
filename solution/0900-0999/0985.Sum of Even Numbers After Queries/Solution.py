class Solution:
    def sumEvenAfterQueries(
        self, nums: List[int], queries: List[List[int]]
    ) -> List[int]:
        ans = []
        s = sum(num for num in nums if num % 2 == 0)
        for v, i in queries:
            old = nums[i]
            nums[i] += v
            if nums[i] % 2 == 0 and old % 2 == 0:
                s += v
            elif nums[i] % 2 == 0 and old % 2 == 1:
                s += nums[i]
            elif old % 2 == 0:
                s -= old
            ans.append(s)
        return ans
