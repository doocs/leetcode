func floodFill(image [][]int, sr int, sc int, newColor int) [][]int {
	oc := image[sr][sc]
	m, n := len(image), len(image[0])
	dirs := []int{-1, 0, 1, 0, -1}
	var dfs func(i, j int)
	dfs = func(i, j int) {
		if i < 0 || i >= m || j < 0 || j >= n || image[i][j] != oc || image[i][j] == newColor {
			return
		}
		image[i][j] = newColor
		for k := 0; k < 4; k++ {
			dfs(i+dirs[k], j+dirs[k+1])
		}
	}
	dfs(sr, sc)
	return image
}