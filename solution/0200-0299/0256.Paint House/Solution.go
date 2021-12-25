func minCost(costs [][]int) int {
	r, g, b := 0, 0, 0
	for _, cost := range costs {
		_r, _g, _b := r, g, b
		r = min(_g, _b) + cost[0]
		g = min(_r, _b) + cost[1]
		b = min(_r, _g) + cost[2]
	}
	return min(r, min(g, b))
}

func min(x, y int) int {
	if x < y {
		return x
	}
	return y
}
