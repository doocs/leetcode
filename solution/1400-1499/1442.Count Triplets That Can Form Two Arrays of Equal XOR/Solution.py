class Solution:
    def countTriplets(self, arr: List[int]) -> int:
        ans, n = 0, len(arr)
        for i, x in enumerate(arr):
            s = x
            for k in range(i + 1, n):
                s ^= arr[k]
                if s == 0:
                    ans += k - i
        return ans
