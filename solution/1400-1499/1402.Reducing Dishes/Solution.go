func maxSatisfaction(satisfaction []int) int {
	sort.Ints(satisfaction)
	ans, presum := 0, 0
	for i := len(satisfaction) - 1; i >= 0; i-- {
		presum += satisfaction[i]
		if presum > 0 {
			ans += presum
		} else {
			break
		}
	}
	return ans
}