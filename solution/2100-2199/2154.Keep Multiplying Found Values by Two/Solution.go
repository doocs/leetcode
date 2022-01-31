func findFinalValue(nums []int, original int) int {
	s := make(map[int]bool)
	for _, num := range nums {
		s[num] = true
	}
	for s[original] {
		original <<= 1
	}
	return original
}