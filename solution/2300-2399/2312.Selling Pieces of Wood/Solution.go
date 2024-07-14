func sellingWood(m int, n int, prices [][]int) int64 {
	f := make([][]int64, m+1)
	d := make([][]int, m+1)
	for i := range f {
		f[i] = make([]int64, n+1)
		for j := range f[i] {
			f[i][j] = -1
		}
		d[i] = make([]int, n+1)
	}
	for _, p := range prices {
		d[p[0]][p[1]] = p[2]
	}
	var dfs func(int, int) int64
	dfs = func(h, w int) int64 {
		if f[h][w] != -1 {
			return f[h][w]
		}
		ans := int64(d[h][w])
		for i := 1; i < h/2+1; i++ {
			ans = max(ans, dfs(i, w)+dfs(h-i, w))
		}
		for i := 1; i < w/2+1; i++ {
			ans = max(ans, dfs(h, i)+dfs(h, w-i))
		}
		f[h][w] = ans
		return ans
	}
	return dfs(m, n)
}