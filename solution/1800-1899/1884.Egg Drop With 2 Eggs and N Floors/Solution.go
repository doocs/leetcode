func twoEggDrop(n int) int {
	f := make([]int, n+1)
	for i := range f {
		f[i] = 1 << 29
	}
	f[0] = 0
	for i := 1; i <= n; i++ {
		for j := 1; j <= i; j++ {
			f[i] = min(f[i], 1+max(j-1, f[i-j]))
		}
	}
	return f[n]
}
