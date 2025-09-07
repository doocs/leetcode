func maxTotal(value []int, limit []int) (ans int64) {
	g := make(map[int][]int)
	for i := range value {
		g[limit[i]] = append(g[limit[i]], value[i])
	}
	for lim, vs := range g {
		slices.SortFunc(vs, func(a, b int) int { return b - a })
		for i := 0; i < min(lim, len(vs)); i++ {
			ans += int64(vs[i])
		}
	}
	return
}
