func findFinalValue(nums []int, original int) int {
	s := map[int]bool{}
	for _, x := range nums {
		s[x] = true
	}
	for s[original] {
		original <<= 1
	}
	return original
}