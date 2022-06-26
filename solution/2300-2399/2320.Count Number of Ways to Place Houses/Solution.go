func countHousePlacements(n int) int {
	mod := int(1e9) + 7
	f := make([][]int, n)
	for i := range f {
		f[i] = make([]int, 2)
	}
	f[0] = []int{1, 1}
	for i := 1; i < n; i++ {
		f[i][0] = (f[i-1][0] + f[i-1][1]) % mod
		f[i][1] = f[i-1][0]
	}
	s := f[n-1][0] + f[n-1][1]
	return (s * s) % mod
}