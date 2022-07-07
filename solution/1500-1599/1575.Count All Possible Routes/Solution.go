func countRoutes(locations []int, start int, finish int, fuel int) int {
	n := len(locations)
	f := make([][]int, n+1)
	for i := range f {
		f[i] = make([]int, fuel+1)
		for j := range f[i] {
			f[i][j] = -1
		}
	}
	mod := int(1e9) + 7
	var dfs func(int, int) int
	dfs = func(i, t int) int {
		if f[i][t] != -1 {
			return f[i][t]
		}
		if abs(locations[i]-locations[finish]) > t {
			return 0
		}
		res := 0
		if i == finish {
			res++
		}
		for j, v := range locations {
			if j != i {
				cost := abs(locations[i] - v)
				if cost <= t {
					res = (res + dfs(j, t-cost)) % mod
				}
			}
		}
		f[i][t] = res
		return res
	}
	return dfs(start, fuel)
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}