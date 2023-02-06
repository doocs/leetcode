func constructArr(a []int) []int {
	n := len(a)
	ans := make([]int, n)
	for i, left := 0, 1; i < n; i++ {
		ans[i] = left
		left *= a[i]
	}
	for i, right := n-1, 1; i >= 0; i-- {
		ans[i] *= right
		right *= a[i]
	}
	return ans
}