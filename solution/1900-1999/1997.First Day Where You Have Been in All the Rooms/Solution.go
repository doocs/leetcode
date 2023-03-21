func firstDayBeenInAllRooms(nextVisit []int) int {
	n := len(nextVisit)
	f := make([]int, n)
	const mod = 1e9 + 7
	for i := 1; i < n; i++ {
		f[i] = (f[i-1] + 1 + f[i-1] - f[nextVisit[i-1]] + 1 + mod) % mod
	}
	return f[n-1]
}