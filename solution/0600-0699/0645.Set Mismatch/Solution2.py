class Solution:
    def findErrorNums(self, nums: List[int]) -> List[int]:
        cnt = Counter(nums)
        n = len(nums)
        ans = [0] * 2
        for x in range(1, n + 1):
            if cnt[x] == 2:
                ans[0] = x
            if cnt[x] == 0:
                ans[1] = x
        return ans
