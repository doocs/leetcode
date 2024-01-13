func countRoutes(locations []int, start int, finish int, fuel int) int {
	n := len(locations)
	const mod = 1e9 + 7
	f := make([][]int, n)
	for i := range f {
		f[i] = make([]int, fuel+1)
	}
	for k := 0; k <= fuel; k++ {
		f[finish][k] = 1
	}
	for k := 0; k <= fuel; k++ {
		for i := 0; i < n; i++ {
			for j := 0; j < n; j++ {
				if j != i && abs(locations[i]-locations[j]) <= k {
					f[i][k] = (f[i][k] + f[j][k-abs(locations[i]-locations[j])]) % mod
				}
			}
		}
	}
	return f[start][fuel]
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}