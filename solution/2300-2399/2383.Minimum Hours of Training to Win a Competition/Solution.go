func minNumberOfHours(initialEnergy int, initialExperience int, energy []int, experience []int) int {
	ans := 0
	for i, a := range energy {
		b := experience[i]
		if initialEnergy <= a {
			ans += a - initialEnergy + 1
			initialEnergy = a + 1
		}
		if initialExperience <= b {
			ans += b - initialExperience + 1
			initialExperience = b + 1
		}
		initialEnergy -= a
		initialExperience += b
	}
	return ans
}