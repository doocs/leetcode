func minNumberOfHours(initialEnergy int, initialExperience int, energy []int, experience []int) (ans int) {
	s := 0
	for _, x := range energy {
		s += x
	}
	if y := s - initialEnergy + 1; y > 0 {
		ans = y
	}
	for _, x := range experience {
		if initialExperience <= x {
			ans += x - initialExperience + 1
			initialExperience = x + 1
		}
		initialExperience += x
	}
	return
}