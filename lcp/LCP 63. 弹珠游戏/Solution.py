class Solution:
    def ballGame(self, num: int, plate: List[str]) -> List[List[int]]:
        def check(i, j, d):
            k = num
            while plate[i][j] != 'O':
                if k == 0:
                    return False
                if plate[i][j] == 'W':
                    d = (d + 3) % 4
                elif plate[i][j] == 'E':
                    d = (d + 1) % 4
                i, j = i + dirs[d], j + dirs[d + 1]
                if not (0 <= i < m and 0 <= j < n):
                    return False
                k -= 1
            return True

        dirs = (0, 1, 0, -1, 0)
        m, n = len(plate), len(plate[0])
        ans = []
        for i in range(1, m - 1):
            if plate[i][0] == '.' and check(i, 0, 0):
                ans.append([i, 0])
            if plate[i][n - 1] == '.' and check(i, n - 1, 2):
                ans.append([i, n - 1])
        for j in range(1, n - 1):
            if plate[0][j] == '.' and check(0, j, 1):
                ans.append([0, j])
            if plate[m - 1][j] == '.' and check(m - 1, j, 3):
                ans.append([m - 1, j])
        return ans
