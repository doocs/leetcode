class Solution:
    def floodFill(
        self, image: List[List[int]], sr: int, sc: int, newColor: int
    ) -> List[List[int]]:
        def dfs(i, j, oc, nc):
            if (
                i < 0
                or i >= len(image)
                or j < 0
                or j >= len(image[0])
                or image[i][j] != oc
                or image[i][j] == nc
            ):
                return
            image[i][j] = nc
            for x, y in [[0, 1], [0, -1], [1, 0], [-1, 0]]:
                dfs(i + x, j + y, oc, nc)

        dfs(sr, sc, image[sr][sc], newColor)
        return image
