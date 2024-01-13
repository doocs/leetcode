func networkDelayTime(times [][]int, n int, k int) int {
	const inf = 0x3f3f
	dist := make([]int, n)
	vis := make([]bool, n)
	g := make([][][]int, n)
	for i := range dist {
		dist[i] = inf
	}
	for _, t := range times {
		u, v, w := t[0]-1, t[1]-1, t[2]
		g[u] = append(g[u], []int{v, w})
	}
	k--
	dist[k] = 0
	q := []int{k}
	vis[k] = true
	for len(q) > 0 {
		u := q[0]
		q = q[1:]
		vis[u] = false
		for _, ne := range g[u] {
			v, w := ne[0], ne[1]
			if dist[v] > dist[u]+w {
				dist[v] = dist[u] + w
				if !vis[v] {
					q = append(q, v)
					vis[v] = true
				}
			}
		}
	}
	ans := slices.Max(dist)
	if ans == inf {
		return -1
	}
	return ans
}