func longestSubsequence(nums []int) int {
	var xor, cnt0 int
	for _, x := range nums {
		xor ^= x
		if x == 0 {
			cnt0++
		}
	}
	n := len(nums)
	if xor != 0 {
		return n
	}
	if cnt0 == n {
		return 0
	}
	return n - 1
}
