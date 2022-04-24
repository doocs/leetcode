func findCheapestPrice(n int, flights [][]int, src int, dst int, k int) int {
	const inf = 0x3f3f3f3f
	dist := make([]int, n)
	backup := make([]int, n)
	for i := range dist {
		dist[i] = inf
	}
	dist[src] = 0
	for i := 0; i < k+1; i++ {
		copy(backup, dist)
		for _, e := range flights {
			f, t, p := e[0], e[1], e[2]
			dist[t] = min(dist[t], backup[f]+p)
		}
	}
	if dist[dst] == inf {
		return -1
	}
	return dist[dst]
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}