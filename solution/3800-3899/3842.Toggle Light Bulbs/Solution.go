func toggleLightBulbs(bulbs []int) []int {
	st := make([]int, 101)
	for _, x := range bulbs {
		st[x] ^= 1
	}
	ans := make([]int, 0)
	for i := 0; i < 101; i++ {
		if st[i] == 1 {
			ans = append(ans, i)
		}
	}
	return ans
}
