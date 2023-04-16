class Solution:
    def rowAndMaximumOnes(self, mat: List[List[int]]) -> List[int]:
        ans = [0, 0]
        for i, row in enumerate(mat):
            cnt = row.count(1)
            if ans[1] < cnt:
                ans = [i, cnt]
        return ans
