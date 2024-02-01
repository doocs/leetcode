class MedianFinder:
    def __init__(self):
        self.q1 = []
        self.q2 = []
        self.s1 = 0
        self.s2 = 0

    def addNum(self, num: int) -> None:
        heappush(self.q1, num)
        self.s1 += num
        num = heappop(self.q1)
        heappush(self.q2, -num)
        self.s1 -= num
        self.s2 += num
        if len(self.q2) - len(self.q1) > 1:
            num = -heappop(self.q2)
            heappush(self.q1, num)
            self.s1 += num
            self.s2 -= num

    def findMedian(self) -> int:
        if len(self.q2) > len(self.q1):
            return -self.q2[0]
        return (self.q1[0] - self.q2[0]) // 2

    def cal(self) -> int:
        x = self.findMedian()
        return (self.s1 - x * len(self.q1) + x * len(self.q2) - self.s2) % (10**9 + 7)


class Solution:
    def numsGame(self, nums: List[int]) -> List[int]:
        n = len(nums)
        ans = [0] * n
        finder = MedianFinder()
        for i, x in enumerate(nums):
            finder.addNum(x - i)
            ans[i] = finder.cal()
        return ans
