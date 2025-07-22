func minCost(maxTime int, edges [][]int, passingFees []int) int {
	m, n := maxTime, len(passingFees)
	f := make([][]int, m+1)
	const inf int = 1 << 30
	for i := range f {
		f[i] = make([]int, n)
		for j := range f[i] {
			f[i][j] = inf
		}
	}
	f[0][0] = passingFees[0]
	for i := 1; i <= m; i++ {
		for _, e := range edges {
			x, y, t := e[0], e[1], e[2]
			if t <= i {
				f[i][x] = min(f[i][x], f[i-t][y]+passingFees[x])
				f[i][y] = min(f[i][y], f[i-t][x]+passingFees[y])
			}
		}
	}
	ans := inf
	for i := 1; i <= m; i++ {
		ans = min(ans, f[i][n-1])
	}
	if ans == inf {
		return -1
	}
	return ans
}
