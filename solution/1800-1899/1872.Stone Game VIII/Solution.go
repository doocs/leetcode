func stoneGameVIII(stones []int) int {
	n := len(stones)
	for i := 1; i < n; i++ {
		stones[i] += stones[i-1]
	}
	f := stones[n-1]
	for i := n - 2; i > 0; i-- {
		f = max(f, stones[i]-f)
	}
	return f
}