func digArtifacts(n int, artifacts [][]int, dig [][]int) int {
	s := map[int]bool{}
	for _, d := range dig {
		s[d[0]*n+d[1]] = true
	}
	check := func(a []int) bool {
		r1, c1, r2, c2 := a[0], a[1], a[2], a[3]
		for i := r1; i <= r2; i++ {
			for j := c1; j <= c2; j++ {
				if !s[i*n+j] {
					return false
				}
			}
		}
		return true
	}
	ans := 0
	for _, a := range artifacts {
		if check(a) {
			ans++
		}
	}
	return ans
}