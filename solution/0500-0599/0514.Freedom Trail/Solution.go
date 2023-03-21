func findRotateSteps(ring string, key string) int {
	m, n := len(key), len(ring)
	pos := [26][]int{}
	for j, c := range ring {
		pos[c-'a'] = append(pos[c-'a'], j)
	}
	f := make([][]int, m)
	for i := range f {
		f[i] = make([]int, n)
		for j := range f[i] {
			f[i][j] = 1 << 30
		}
	}
	for _, j := range pos[key[0]-'a'] {
		f[0][j] = min(j, n-j) + 1
	}
	for i := 1; i < m; i++ {
		for _, j := range pos[key[i]-'a'] {
			for _, k := range pos[key[i-1]-'a'] {
				f[i][j] = min(f[i][j], f[i-1][k]+min(abs(j-k), n-abs(j-k))+1)
			}
		}
	}
	ans := 1 << 30
	for _, j := range pos[key[m-1]-'a'] {
		ans = min(ans, f[m-1][j])
	}
	return ans
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}