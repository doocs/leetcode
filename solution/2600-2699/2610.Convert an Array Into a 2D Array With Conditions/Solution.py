class Solution:
    def findMatrix(self, nums: list[int]) -> list[list[int]]:
        cnt = Counter(nums)
        ans = []
        for x, v in cnt.items():
            for i in range(v):
                if len(ans) <= i:
                    ans.append([])
                ans[i].append(x)
        return ans
