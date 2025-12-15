func bestClosingTime(customers string) int {
	ans := 0
	cost := strings.Count(customers, "Y")
	mn := cost
	for j := 1; j <= len(customers); j++ {
		c := customers[j-1]
		if c == 'N' {
			cost++
		} else {
			cost--
		}
		if cost < mn {
			ans = j
			mn = cost
		}
	}
	return ans
}
