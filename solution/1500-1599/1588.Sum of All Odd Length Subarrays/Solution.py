class Solution:
    def sumOddLengthSubarrays(self, arr: List[int]) -> int:
        ans, n = 0, len(arr)
        for i in range(n):
            s = 0
            for j in range(i, n):
                s += arr[j]
                if (j - i + 1) & 1:
                    ans += s
        return ans
