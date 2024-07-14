class Solution:
    def minSwaps(self, data: List[int]) -> int:
        k = data.count(1)
        mx = t = sum(data[:k])
        for i in range(k, len(data)):
            t += data[i]
            t -= data[i - k]
            mx = max(mx, t)
        return k - mx
