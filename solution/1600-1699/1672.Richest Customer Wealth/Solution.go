func maximumWealth(accounts [][]int) int {
	res := 0
	for _, account := range accounts {
		t := 0
		for _, money := range account {
			t += money
		}
		if t > res {
			res = t
		}
	}
	return res
}