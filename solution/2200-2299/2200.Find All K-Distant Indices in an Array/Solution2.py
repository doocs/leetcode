class Solution:
    def findKDistantIndices(self, nums: List[int], key: int, k: int) -> List[int]:
        idx = [i for i, x in enumerate(nums) if x == key]
        ans = []
        for i in range(len(nums)):
            l = bisect_left(idx, i - k)
            r = bisect_right(idx, i + k) - 1
            if l <= r:
                ans.append(i)
        return ans
