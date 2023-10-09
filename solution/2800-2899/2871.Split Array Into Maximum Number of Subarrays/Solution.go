func maxSubarrays(nums []int) int {
	ans, score := 1, -1
	for _, num := range nums {
		score &= num
		if score == 0 {
			score--
			ans++
		}
	}
	if ans == 1 {
		return 1
	}
	return ans - 1
}