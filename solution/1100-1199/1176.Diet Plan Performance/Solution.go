func dietPlanPerformance(calories []int, k int, lower int, upper int) (ans int) {
	n := len(calories)
	s := 0
	for _, x := range calories[:k] {
		s += x
	}
	if s < lower {
		ans--
	} else if s > upper {
		ans++
	}
	for i := k; i < n; i++ {
		s += calories[i] - calories[i-k]
		if s < lower {
			ans--
		} else if s > upper {
			ans++
		}
	}
	return
}