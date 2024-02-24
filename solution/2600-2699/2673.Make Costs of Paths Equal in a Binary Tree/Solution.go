func minIncrements(n int, cost []int) (ans int) {
	for i := n >> 1; i > 0; i-- {
		l, r := i<<1, i<<1|1
		ans += abs(cost[l-1] - cost[r-1])
		cost[i-1] += max(cost[l-1], cost[r-1])
	}
	return
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}