func stoneGameVIII(stones []int) int {
	n := len(stones)
	for i := 1; i < n; i++ {
		stones[i] += stones[i-1]
	}
	f := make([]int, n)
	f[n-1] = stones[n-1]
	for i := n - 2; i > 0; i-- {
		f[i] = max(f[i+1], stones[i]-f[i+1])
	}
	return f[1]
}
