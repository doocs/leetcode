func maximumRemovals(s string, p string, removable []int) int {
	m, n := len(s), len(p)
	l, r := 0, len(removable)
	check := func(k int) bool {
		rem := make([]bool, m)
		for i := 0; i < k; i++ {
			rem[removable[i]] = true
		}
		i, j := 0, 0
		for i < m && j < n {
			if !rem[i] && s[i] == p[j] {
				j++
			}
			i++
		}
		return j == n
	}
	for l < r {
		mid := (l + r + 1) >> 1
		if check(mid) {
			l = mid
		} else {
			r = mid - 1
		}
	}
	return l
}
