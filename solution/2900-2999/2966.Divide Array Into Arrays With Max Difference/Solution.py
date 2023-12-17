class Solution:
    def divideArray(self, nums: List[int], k: int) -> List[List[int]]:
        nums.sort()
        ans = []
        n = len(nums)
        for i in range(0, n, 3):
            t = nums[i : i + 3]
            if t[2] - t[0] > k:
                return []
            ans.append(t)
        return ans
