func sumZero(n int) []int {
	ans := make([]int, n)
	for i := 1; i < n; i++ {
		ans[i] = i
	}
	ans[0] = -n * (n - 1) / 2
	return ans
}