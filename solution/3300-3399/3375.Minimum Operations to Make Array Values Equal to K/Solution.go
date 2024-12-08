func minOperations(nums []int, k int) int {
	mi := 1 << 30
	s := map[int]bool{}
	for _, x := range nums {
		if x < k {
			return -1
		}
		s[x] = true
		mi = min(mi, x)
	}
	if mi == k {
		return len(s) - 1
	}
	return len(s)
}
