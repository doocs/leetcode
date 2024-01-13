func maxAlternatingSum(nums []int) int64 {
	n := len(nums)
	f := make([]int, n+1)
	g := make([]int, n+1)
	for i, x := range nums {
		i++
		f[i] = max(g[i-1]-x, f[i-1])
		g[i] = max(f[i-1]+x, g[i-1])
	}
	return int64(max(f[n], g[n]))
}