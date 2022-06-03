class Solution:
    def findKDistantIndices(self, nums: List[int], key: int, k: int) -> List[int]:
        ans = []
        n = len(nums)
        for i in range(n):
            for j in range(n):
                if abs(i - j) <= k and nums[j] == key:
                    ans.append(i)
                    break
        return ans
