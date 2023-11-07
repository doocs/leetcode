func minimumCosts(regular []int, express []int, expressCost int) []int64 {
	f, g := 0, 1<<30
	cost := make([]int64, len(regular))
	for i, a := range regular {
		b := express[i]
		ff := min(f+a, g+a)
		gg := min(f+expressCost+b, g+b)
		f, g = ff, gg
		cost[i] = int64(min(f, g))
	}
	return cost
}