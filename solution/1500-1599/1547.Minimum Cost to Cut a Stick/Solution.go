func minCost(n int, cuts []int) int {
	cuts = append(cuts, []int{0, n}...)
	sort.Ints(cuts)
	m := len(cuts)
	f := make([][]int, m)
	for i := range f {
		f[i] = make([]int, m)
	}
	for l := 2; l < m; l++ {
		for i := 0; i+l < m; i++ {
			j := i + l
			f[i][j] = 1 << 30
			for k := i + 1; k < j; k++ {
				f[i][j] = min(f[i][j], f[i][k]+f[k][j]+cuts[j]-cuts[i])
			}
		}
	}
	return f[0][m-1]
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}