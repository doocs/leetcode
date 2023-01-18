func calculateTax(brackets [][]int, income int) float64 {
	var ans, prev int
	for _, e := range brackets {
		upper, percent := e[0], e[1]
		ans += max(0, min(income, upper)-prev) * percent
		prev = upper
	}
	return float64(ans) / 100.0
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}