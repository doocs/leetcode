func networkDelayTime(times [][]int, n int, k int) int {
	const inf = 0x3f3f
	dist := make([]int, n)
	backup := make([]int, n)
	for i := range dist {
		dist[i] = inf
	}
	dist[k-1] = 0
	for i := 0; i < n; i++ {
		copy(backup, dist)
		for _, e := range times {
			u, v, w := e[0]-1, e[1]-1, e[2]
			dist[v] = min(dist[v], backup[u]+w)
		}
	}
	ans := slices.Max(dist)
	if ans == inf {
		return -1
	}
	return ans
}