class Solution:
    def rowAndMaximumOnes(self, mat: list[list[int]]) -> list[int]:
        ans = [0, 0]
        for i, row in enumerate(mat):
            cnt = sum(row)
            if ans[1] < cnt:
                ans = [i, cnt]
        return ans
