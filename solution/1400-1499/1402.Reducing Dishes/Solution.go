func maxSatisfaction(satisfaction []int) (ans int) {
	sort.Slice(satisfaction, func(i, j int) bool { return satisfaction[i] > satisfaction[j] })
	s := 0
	for _, x := range satisfaction {
		s += x
		if s <= 0 {
			break
		}
		ans += s
	}
	return
}