func maximumDetonation(bombs [][]int) (ans int) {
	n := len(bombs)
	g := make([][]int, n)
	for i, p1 := range bombs[:n-1] {
		for j := i + 1; j < n; j++ {
			p2 := bombs[j]
			dist := math.Hypot(float64(p1[0]-p2[0]), float64(p1[1]-p2[1]))
			if dist <= float64(p1[2]) {
				g[i] = append(g[i], j)
			}
			if dist <= float64(p2[2]) {
				g[j] = append(g[j], i)
			}
		}
	}
	for k := 0; k < n; k++ {
		q := []int{k}
		vis := make([]bool, n)
		vis[k] = true
		cnt := 0
		for len(q) > 0 {
			i := q[0]
			q = q[1:]
			cnt++
			for _, j := range g[i] {
				if !vis[j] {
					vis[j] = true
					q = append(q, j)
				}
			}
		}
		if cnt == n {
			return n
		}
		ans = max(ans, cnt)
	}
	return
}