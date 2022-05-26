class Solution:
    def sumOddLengthSubarrays(self, arr: List[int]) -> int:
        n = len(arr)
        presum = [0] * (n + 1)
        for i in range(n):
            presum[i + 1] = presum[i] + arr[i]

        res = 0
        for i in range(n):
            for j in range(0, n, 2):
                if i + j < n:
                    res += presum[i + j + 1] - presum[i]
        return res
