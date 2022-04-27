class Solution:
    def generate(self, numRows: int) -> List[List[int]]:
        ans = []
        for i in range(numRows):
            t = [
                1 if j == 0 or j == i else ans[-1][j] + ans[-1][j - 1]
                for j in range(i + 1)
            ]
            ans.append(t)
        return ans
