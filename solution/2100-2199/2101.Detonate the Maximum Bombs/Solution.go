func maximumDetonation(bombs [][]int) int {
	check := func(i, j int) bool {
		if i == j {
			return false
		}
		x, y := bombs[i][0]-bombs[j][0], bombs[i][1]-bombs[j][1]
		r := bombs[i][2]
		return r*r >= x*x+y*y
	}
	n := len(bombs)
	g := make([][]bool, n)
	for i := range g {
		g[i] = make([]bool, n)
		for j := range g[i] {
			g[i][j] = check(i, j)
		}
	}

	ans := 0
	for k := 0; k < n; k++ {
		q := []int{k}
		vis := make([]bool, n)
		vis[k] = true
		cnt := 0
		for len(q) > 0 {
			i := q[0]
			q = q[1:]
			cnt++
			for j := 0; j < n; j++ {
				if g[i][j] && !vis[j] {
					vis[j] = true
					q = append(q, j)
				}
			}
		}
		ans = max(ans, cnt)
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}