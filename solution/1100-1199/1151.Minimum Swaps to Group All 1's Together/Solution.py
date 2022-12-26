class Solution:
    def minSwaps(self, data: List[int]) -> int:
        k = data.count(1)
        t = sum(data[:k])
        mx = t
        for i in range(k, len(data)):
            t += data[i]
            t -= data[i - k]
            mx = max(mx, t)
        return k - mx
