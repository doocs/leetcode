class Solution:
    def sumZero(self, n: int) -> List[int]:
        presum = 0
        res = []
        for i in range(1, n):
            res.append(i)
            presum += i
        res.append(-presum)
        return res
