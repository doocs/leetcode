func minimumCost(cost []int) int {
	sort.Ints(cost)
	ans, n := 0, len(cost)
	for i := n - 1; i >= 0; i -= 3 {
		ans += cost[i]
		if i >= 1 {
			ans += cost[i-1]
		}
	}
	return ans
}