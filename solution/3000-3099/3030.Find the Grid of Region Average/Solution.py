class Solution:
    def resultGrid(self, image: List[List[int]], threshold: int) -> List[List[int]]:
        n, m = len(image), len(image[0])
        ans = [[0] * m for _ in range(n)]
        ct = [[0] * m for _ in range(n)]
        for i in range(n - 2):
            for j in range(m - 2):
                region = True
                for k in range(3):
                    for l in range(2):
                        region &= (
                            abs(image[i + k][j + l] - image[i + k][j + l + 1])
                            <= threshold
                        )
                for k in range(2):
                    for l in range(3):
                        region &= (
                            abs(image[i + k][j + l] - image[i + k + 1][j + l])
                            <= threshold
                        )

                if region:
                    tot = 0
                    for k in range(3):
                        for l in range(3):
                            tot += image[i + k][j + l]
                    for k in range(3):
                        for l in range(3):
                            ct[i + k][j + l] += 1
                            ans[i + k][j + l] += tot // 9

        for i in range(n):
            for j in range(m):
                if ct[i][j] == 0:
                    ans[i][j] = image[i][j]
                else:
                    ans[i][j] //= ct[i][j]

        return ans
