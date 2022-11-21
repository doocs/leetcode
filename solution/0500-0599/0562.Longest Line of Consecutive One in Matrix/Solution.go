func longestLine(mat [][]int) (ans int) {
	m, n := len(mat), len(mat[0])
	f := make([][][4]int, m+2)
	for i := range f {
		f[i] = make([][4]int, n+2)
	}
	for i := 1; i <= m; i++ {
		for j := 1; j <= n; j++ {
			if mat[i-1][j-1] == 1 {
				f[i][j][0] = f[i-1][j][0] + 1
				f[i][j][1] = f[i][j-1][1] + 1
				f[i][j][2] = f[i-1][j-1][2] + 1
				f[i][j][3] = f[i-1][j+1][3] + 1
				for _, v := range f[i][j] {
					if ans < v {
						ans = v
					}
				}
			}
		}
	}
	return
}