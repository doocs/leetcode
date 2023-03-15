func minCost(houses []int, cost [][]int, m int, n int, target int) int {
	f := make([][][]int, m)
	const inf = 1 << 30
	for i := range f {
		f[i] = make([][]int, n+1)
		for j := range f[i] {
			f[i][j] = make([]int, target+1)
			for k := range f[i][j] {
				f[i][j][k] = inf
			}
		}
	}
	if houses[0] == 0 {
		for j := 1; j <= n; j++ {
			f[0][j][1] = cost[0][j-1]
		}
	} else {
		f[0][houses[0]][1] = 0
	}
	for i := 1; i < m; i++ {
		if houses[i] == 0 {
			for j := 1; j <= n; j++ {
				for k := 1; k <= target && k <= i+1; k++ {
					for j0 := 1; j0 <= n; j0++ {
						if j == j0 {
							f[i][j][k] = min(f[i][j][k], f[i-1][j][k]+cost[i][j-1])
						} else {
							f[i][j][k] = min(f[i][j][k], f[i-1][j0][k-1]+cost[i][j-1])
						}
					}
				}
			}
		} else {
			j := houses[i]
			for k := 1; k <= target && k <= i+1; k++ {
				for j0 := 1; j0 <= n; j0++ {
					if j == j0 {
						f[i][j][k] = min(f[i][j][k], f[i-1][j][k])
					} else {
						f[i][j][k] = min(f[i][j][k], f[i-1][j0][k-1])
					}
				}
			}
		}
	}
	ans := inf
	for j := 1; j <= n; j++ {
		ans = min(ans, f[m-1][j][target])
	}
	if ans == inf {
		return -1
	}
	return ans
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}