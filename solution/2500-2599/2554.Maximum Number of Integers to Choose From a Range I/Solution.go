func maxCount(banned []int, n int, maxSum int) (ans int) {
	ban := map[int]bool{}
	for _, x := range banned {
		ban[x] = true
	}
	s := 0
	for i := 1; i <= n && s+i <= maxSum; i++ {
		if !ban[i] {
			ans++
			s += i
		}
	}
	return
}