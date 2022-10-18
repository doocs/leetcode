func averageWaitingTime(customers [][]int) float64 {
	tot, t := 0, 0
	for _, e := range customers {
		a, b := e[0], e[1]
		t = max(t, a) + b
		tot += t - a
	}
	return float64(tot) / float64(len(customers))
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}