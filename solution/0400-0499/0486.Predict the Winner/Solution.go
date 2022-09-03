func PredictTheWinner(nums []int) bool {
	n := len(nums)
	s := make([]int, n+1)
	f := make([][]int, n+1)
	for i, v := range nums {
		s[i+1] = s[i] + v
	}
	for i := range f {
		f[i] = make([]int, n+1)
		for j := range f[i] {
			f[i][j] = -1
		}
	}
	var dfs func(i, j int) int
	dfs = func(i, j int) int {
		if i > j {
			return 0
		}
		if f[i][j] != -1 {
			return f[i][j]
		}
		a := min(dfs(i+1, j), dfs(i, j-1))
		f[i][j] = s[j+1] - s[i] - a
		return f[i][j]
	}
	return dfs(0, n-1)*2 >= s[n]
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}