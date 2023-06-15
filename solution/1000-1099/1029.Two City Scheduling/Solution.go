func twoCitySchedCost(costs [][]int) (ans int) {
	sort.Slice(costs, func(i, j int) bool {
		return costs[i][0]-costs[i][1] < costs[j][0]-costs[j][1]
	})
	n := len(costs) >> 1
	for i, a := range costs[:n] {
		ans += a[0] + costs[i+n][1]
	}
	return
}