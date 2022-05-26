class Solution:
    def findDifferentBinaryString(self, nums: List[str]) -> str:
        s = set(num.count("1") for num in nums)
        n = len(nums)
        for i in range(n + 1):
            if i not in s:
                return "1" * i + "0" * (n - i)
        return ""
