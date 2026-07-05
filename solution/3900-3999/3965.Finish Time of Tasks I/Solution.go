func finishTime(n int, edges [][]int, baseTime []int) int64 {
	g := make([][]int, n)

	for _, e := range edges {
		g[e[0]] = append(g[e[0]], e[1])
	}

	var dfs func(int) int64

	dfs = func(i int) int64 {
		if len(g[i]) == 0 {
			return int64(baseTime[i])
		}

		var INF int64 = 1 << 62
		var earliest int64 = INF
		var latest int64 = -INF

		for _, j := range g[i] {
			a := dfs(j)
            earliest = min(earliest, a)
            latest = max(latest, a)
		}

		ownDuration := (latest - earliest) + int64(baseTime[i])
		return latest + ownDuration
	}

	return dfs(0)
}