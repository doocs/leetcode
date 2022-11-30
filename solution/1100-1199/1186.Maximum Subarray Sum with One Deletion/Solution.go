func maximumSum(arr []int) int {
	n := len(arr)
	left := make([]int, n)
	right := make([]int, n)
	t := 0
	ans := math.MinInt32
	for i, v := range arr {
		t = max(t, 0) + v
		left[i] = t
		ans = max(ans, left[i])
	}
	t = 0
	for i := n - 1; i >= 0; i-- {
		t = max(t, 0) + arr[i]
		right[i] = t
	}
	for i := 1; i < n-1; i++ {
		ans = max(ans, left[i-1]+right[i+1])
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}