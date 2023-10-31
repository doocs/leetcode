func maximumSum(arr []int) int {
	n := len(arr)
	left := make([]int, n)
	right := make([]int, n)
	ans := -(1 << 30)
	for i, s := 0, 0; i < n; i++ {
		s = max(s, 0) + arr[i]
		left[i] = s
		ans = max(ans, s)
	}
	for i, s := n-1, 0; i >= 0; i-- {
		s = max(s, 0) + arr[i]
		right[i] = s
	}
	for i := 1; i < n-1; i++ {
		ans = max(ans, left[i-1]+right[i+1])
	}
	return ans
}