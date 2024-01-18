func canCross(stones []int) bool {
	n := len(stones)
	f := make([][]bool, n)
	for i := range f {
		f[i] = make([]bool, n)
	}
	f[0][0] = true
	for i := 1; i < n; i++ {
		for j := i - 1; j >= 0; j-- {
			k := stones[i] - stones[j]
			if k-1 > j {
				break
			}
			f[i][k] = f[j][k-1] || f[j][k] || f[j][k+1]
			if i == n-1 && f[i][k] {
				return true
			}
		}
	}
	return false
}