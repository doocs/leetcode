func minCost(nums []int, x int) int64 {
	n := len(nums)
	f := make([][]int, n)
	for i, v := range nums {
		f[i] = make([]int, n)
		f[i][0] = v
		for j := 1; j < n; j++ {
			f[i][j] = min(f[i][j-1], nums[(i-j+n)%n])
		}
	}
	ans := 1 << 60
	for j := 0; j < n; j++ {
		cost := x * j
		for i := 0; i < n; i++ {
			cost += f[i][j]
		}
		ans = min(ans, cost)
	}
	return int64(ans)
}