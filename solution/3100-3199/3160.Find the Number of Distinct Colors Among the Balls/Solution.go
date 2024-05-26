func queryResults(limit int, queries [][]int) (ans []int) {
	g := map[int]int{}
	cnt := map[int]int{}
	for _, q := range queries {
		x, y := q[0], q[1]
		cnt[y]++
		if v, ok := g[x]; ok {
			cnt[v]--
			if cnt[v] == 0 {
				delete(cnt, v)
			}
		}
		g[x] = y
		ans = append(ans, len(cnt))
	}
	return
}