class Solution:
    def __init__(self, w: List[int]):
        n = len(w)
        self.presum = [0] * (n + 1)
        for i in range(n):
            self.presum[i + 1] = self.presum[i] + w[i]

    def pickIndex(self) -> int:
        n = len(self.presum)
        x = random.randint(1, self.presum[-1])
        left, right = 0, n - 2
        while left < right:
            mid = (left + right) >> 1
            if self.presum[mid + 1] >= x:
                right = mid
            else:
                left = mid + 1
        return left


# Your Solution object will be instantiated and called as such:
# obj = Solution(w)
# param_1 = obj.pickIndex()
