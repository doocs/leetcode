func maximumWealth(accounts [][]int) int {
	ans := 0
	for _, e := range accounts {
		s := 0
		for _, v := range e {
			s += v
		}
		if ans < s {
			ans = s
		}
	}
	return ans
}