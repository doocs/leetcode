func minimumCost(s string, t string, flipCost int, swapCost int, crossCost int) int64 {
	var diff [2]int64
	n := len(s)
	for i := 0; i < n; i++ {
		if s[i] != t[i] {
			diff[s[i]-'0']++
		}
	}

	ans := (diff[0] + diff[1]) * int64(flipCost)

	mx := max(diff[0], diff[1])
	mn := min(diff[0], diff[1])
	ans = min(ans, mn*int64(swapCost)+(mx-mn)*int64(flipCost))

	avg := (mx + mn) / 2
	ans = min(ans, (avg-mn)*int64(crossCost)+avg*int64(swapCost)+(mx+mn-avg*2)*int64(flipCost))

	return ans
}
