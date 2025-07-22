func minCosts(cost []int) []int {
	n := len(cost)
	ans := make([]int, n)
	mi := cost[0]
	for i, c := range cost {
		mi = min(mi, c)
		ans[i] = mi
	}
	return ans
}