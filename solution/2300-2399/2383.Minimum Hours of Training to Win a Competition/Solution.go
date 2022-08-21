func minNumberOfHours(initialEnergy int, initialExperience int, energy []int, experience []int) int {
	ans := 0
	for i, a := range energy {
		b := experience[i]
		if initialEnergy <= a {
			t := a - initialEnergy + 1
			ans += t
			initialEnergy += t
		}
		if initialExperience <= b {
			t := b - initialExperience + 1
			ans += t
			initialExperience += t
		}
		initialEnergy -= a
		initialExperience += b
	}
	return ans
}