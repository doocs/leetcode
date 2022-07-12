func minOperations(nums []int) int {
	ans, mx := 0, 0
	for _, v := range nums {
		mx = max(mx, v)
		for v > 0 {
			ans += v & 1
			v >>= 1
		}
	}
	if mx > 0 {
		for mx > 0 {
			ans++
			mx >>= 1
		}
		ans--
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}