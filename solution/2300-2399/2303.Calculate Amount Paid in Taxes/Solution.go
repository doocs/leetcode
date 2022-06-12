func calculateTax(brackets [][]int, income int) float64 {
	var ans float64
	idx, prev := 0, 0
	for income > 0 {
		a, b := brackets[idx][0], brackets[idx][1]
		d := a - prev
		ans += float64(min(d, income)*b) / 100.0
		if income <= d {
			break
		}
		income -= d
		idx++
		prev = a
	}
	return ans
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}