class Solution:
    def findDifferentBinaryString(self, nums: List[str]) -> str:
        ans = [None] * len(nums)
        for i, s in enumerate(nums):
            ans[i] = "1" if s[i] == "0" else "0"
        return "".join(ans)
