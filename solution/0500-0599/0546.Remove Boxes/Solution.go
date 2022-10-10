func removeBoxes(boxes []int) int {
	n := len(boxes)
	f := make([][][]int, n)
	for i := range f {
		f[i] = make([][]int, n)
		for j := range f[i] {
			f[i][j] = make([]int, n)
		}
	}
	var dfs func(i, j, k int) int
	dfs = func(i, j, k int) int {
		if i > j {
			return 0
		}
		for i < j && boxes[j] == boxes[j-1] {
			j, k = j-1, k+1
		}
		if f[i][j][k] > 0 {
			return f[i][j][k]
		}
		ans := dfs(i, j-1, 0) + (k+1)*(k+1)
		for h := i; h < j; h++ {
			if boxes[h] == boxes[j] {
				ans = max(ans, dfs(h+1, j-1, 0)+dfs(i, h, k+1))
			}
		}
		f[i][j][k] = ans
		return ans
	}
	return dfs(0, n-1, 0)
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}