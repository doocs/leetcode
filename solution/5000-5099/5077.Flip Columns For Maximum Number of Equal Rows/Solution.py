'''
若将第i行变成所有的值相等，则可能会有j行也会变成所有的值相等，则共有j+1个行所有的值相等。找出其中最大的就行了。
'''


class Solution:
    def maxEqualRowsAfterFlips(self, matrix: List[List[int]]) -> int:
        hang = len(matrix)
        lie = len(matrix[0])
        visit = [0 for _ in range(hang)]
        ans = 0
        for i in range(hang):
            if visit[i] == 1:
                continue
            visit[i] = 1
            cnt = 1
            for j in range(i + 1, hang):
                if visit[j] == 1:
                    continue
                if matrix[i][0] == matrix[j][0]:
                    if matrix[i] == matrix[j]:
                        cnt += 1
                        visit[j] = 1
                else:
                    if matrix[i] == [(matrix[j][k] + 1) % 2 for k in range(lie)]:
                        cnt += 1
                        visit[j] = 1
            if cnt > ans:
                ans = cnt
        return ans
