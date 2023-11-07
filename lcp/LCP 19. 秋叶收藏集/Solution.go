func minimumOperations(leaves string) int {
	n := len(leaves)
	f := make([][3]int, n)
	inf := 1 << 30
	for i := range f {
		f[i] = [3]int{inf, inf, inf}
	}
	if leaves[0] == 'y' {
		f[0][0] = 1
	} else {
		f[0][0] = 0
	}
	for i := 1; i < n; i++ {
		if leaves[i] == 'r' {
			f[i][0] = f[i-1][0]
			f[i][1] = min(f[i-1][0], f[i-1][1]) + 1
			f[i][2] = min(f[i-1][2], f[i-1][1])
		} else {
			f[i][0] = f[i-1][0] + 1
			f[i][1] = min(f[i-1][0], f[i-1][1])
			f[i][2] = min(f[i-1][2], f[i-1][1]) + 1
		}
	}
	return f[n-1][2]
}