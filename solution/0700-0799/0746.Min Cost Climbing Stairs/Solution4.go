func minCostClimbingStairs(cost []int) int {
	n := len(cost)
	f := make([]int, n+2)
	for i := n - 1; i >= 0; i-- {
		f[i] = cost[i] + min(f[i+1], f[i+2])
	}
	return min(f[0], f[1])
}
