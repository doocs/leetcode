func grayCode(n int) []int {
	var ans []int
	for i := 0; i < 1<<n; i++ {
		ans = append(ans, i^(i>>1))
	}
	return ans
}