func circularPermutation(n int, start int) (ans []int) {
	for i := 0; i < 1<<n; i++ {
		ans = append(ans, i^(i>>1)^start)
	}
	return
}