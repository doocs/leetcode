class Solution:
    def findSmallestInteger(self, nums: List[int], value: int) -> int:
        cnt = Counter(x % value for x in nums)
        for i in range(len(nums) + 1):
            if cnt[i % value] == 0:
                return i
            cnt[i % value] -= 1
