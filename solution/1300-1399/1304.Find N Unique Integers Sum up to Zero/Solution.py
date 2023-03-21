class Solution:
    def sumZero(self, n: int) -> List[int]:
        ans = list(range(1, n))
        ans.append(-sum(ans))
        return ans
