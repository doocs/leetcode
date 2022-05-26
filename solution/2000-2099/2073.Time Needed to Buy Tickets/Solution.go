func timeRequiredToBuy(tickets []int, k int) int {
	ans := 0
	for i, t := range tickets {
		if i <= k {
			ans += min(tickets[k], t)
		} else {
			ans += min(tickets[k]-1, t)
		}
	}
	return ans
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
