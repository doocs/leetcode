func minimumCost(cost1 int, cost2 int, costBoth int, need1 int, need2 int) int64 {
	a := int64(need1)*int64(cost1) + int64(need2)*int64(cost2)
	b := int64(costBoth) * int64(max(need1, need2))
	mn := min(need1, need2)
	c := int64(costBoth)*int64(mn) +
		int64(need1-mn)*int64(cost1) +
		int64(need2-mn)*int64(cost2)
	return min(a, min(b, c))
}
