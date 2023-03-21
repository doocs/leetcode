class Solution:
    def numOfSubarrays(self, arr: List[int], k: int, threshold: int) -> int:
        s = sum(arr[:k])
        ans = int(s / k >= threshold)
        for i in range(k, len(arr)):
            s += arr[i]
            s -= arr[i - k]
            ans += int(s / k >= threshold)
        return ans
