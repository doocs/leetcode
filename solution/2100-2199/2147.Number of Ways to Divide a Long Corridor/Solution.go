func numberOfWays(corridor string) int {
	n := len(corridor)
	var mod int = 1e9 + 7
	f := make([][]int, n)
	for i := range f {
		f[i] = make([]int, 3)
		for j := range f[i] {
			f[i][j] = -1
		}
	}
	var dfs func(i, cnt int) int
	dfs = func(i, cnt int) int {
		if i == n {
			if cnt == 2 {
				return 1
			}
			return 0
		}
		if corridor[i] == 'S' {
			cnt++
		}
		if cnt > 2 {
			return 0
		}
		if f[i][cnt] != -1 {
			return f[i][cnt]
		}
		ans := dfs(i+1, cnt)
		if cnt == 2 {
			ans += dfs(i+1, 0)
			ans %= mod
		}
		f[i][cnt] = ans
		return ans
	}
	return dfs(0, 0)
}