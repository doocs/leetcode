func dietPlanPerformance(calories []int, k int, lower int, upper int) (ans int) {
	n := len(calories)
	s := make([]int, n+1)
	for i, x := range calories {
		s[i+1] = s[i] + x
	}
	for i := 0; i < n-k+1; i++ {
		t := s[i+k] - s[i]
		if t < lower {
			ans--
		} else if t > upper {
			ans++
		}
	}
	return
}