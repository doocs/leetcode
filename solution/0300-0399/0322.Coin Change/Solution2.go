func coinChange(coins []int, amount int) int {
	n := amount
	f := make([]int, n+1)
	for i := range f {
		f[i] = 1 << 30
	}
	f[0] = 0
	for _, x := range coins {
		for j := x; j <= n; j++ {
			f[j] = min(f[j], f[j-x]+1)
		}
	}
	if f[n] > n {
		return -1
	}
	return f[n]
}