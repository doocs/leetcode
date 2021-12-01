func minCostClimbingStairs(cost []int) int {
	a, b := 0, 0
	for i := 1; i < len(cost); i++ {
		a, b = b, min(a+cost[i-1], b+cost[i])
	}
	return b
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}