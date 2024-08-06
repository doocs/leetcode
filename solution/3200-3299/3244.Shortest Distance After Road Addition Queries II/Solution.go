func shortestDistanceAfterQueries(n int, queries [][]int) (ans []int) {
	nxt := make([]int, n-1)
	for i := range nxt {
		nxt[i] = i + 1
	}
	cnt := n - 1
	for _, q := range queries {
		u, v := q[0], q[1]
		if nxt[u] > 0 && nxt[u] < v {
			i := nxt[u]
			for i < v {
				cnt--
				nxt[i], i = 0, nxt[i]
			}
			nxt[u] = v
		}
		ans = append(ans, cnt)
	}
	return
}
