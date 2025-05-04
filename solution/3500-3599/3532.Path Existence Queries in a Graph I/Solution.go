func pathExistenceQueries(n int, nums []int, maxDiff int, queries [][]int) (ans []bool) {
	g := make([]int, n)
	cnt := 0
	for i := 1; i < n; i++ {
		if nums[i]-nums[i-1] > maxDiff {
			cnt++
		}
		g[i] = cnt
	}

	for _, q := range queries {
		u, v := q[0], q[1]
		ans = append(ans, g[u] == g[v])
	}
	return
}
