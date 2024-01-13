func sumZero(n int) []int {
	ans := make([]int, n)
	for i, j := 1, 0; i <= n/2; i, j = i+1, j+1 {
		ans[j] = i
		j++
		ans[j] = -i
	}
	return ans
}