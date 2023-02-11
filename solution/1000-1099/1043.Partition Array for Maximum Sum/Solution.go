func maxSumAfterPartitioning(arr []int, k int) int {
	n := len(arr)
	f := make([]int, n+1)
	for i := 0; i < n; i++ {
		mx := 0
		for j := i; j >= max(0, i-k+1); j-- {
			mx = max(mx, arr[j])
			t := mx*(i-j+1) + f[j]
			f[i+1] = max(f[i+1], t)
		}
	}
	return f[n]
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}