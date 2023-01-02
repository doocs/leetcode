func minimumLines(points [][]int) int {
	check := func(i, j, k int) bool {
		x1, y1 := points[i][0], points[i][1]
		x2, y2 := points[j][0], points[j][1]
		x3, y3 := points[k][0], points[k][1]
		return (x2-x1)*(y3-y1) == (x3-x1)*(y2-y1)
	}
	n := len(points)
	f := make([]int, 1<<n)
	var dfs func(int) int
	dfs = func(state int) int {
		if state == (1<<n)-1 {
			return 0
		}
		if f[state] > 0 {
			return f[state]
		}
		ans := 1 << 30
		for i := 0; i < n; i++ {
			if (state >> i & 1) == 0 {
				for j := i + 1; j < n; j++ {
					nxt := state | 1<<i | 1<<j
					for k := j + 1; k < n; k++ {
						if (nxt>>k&1) == 0 && check(i, j, k) {
							nxt |= 1 << k
						}
					}
					ans = min(ans, dfs(nxt)+1)
				}
				if i == n-1 {
					ans = min(ans, dfs(state|1<<i)+1)
				}
			}
		}
		f[state] = ans
		return ans
	}
	return dfs(0)
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}