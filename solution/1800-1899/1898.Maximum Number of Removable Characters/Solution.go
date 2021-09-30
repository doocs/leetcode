func maximumRemovals(s string, p string, removable []int) int {
	left, right := 0, len(removable)
	for left < right {
		mid := (left + right + 1) >> 1
		if check(s, p, removable, mid) {
			left = mid
		} else {
			right = mid - 1
		}
	}
	return left
}

func check(s string, p string, removable []int, mid int) bool {
	m, n, i, j := len(s), len(p), 0, 0
	ids := make(map[int]bool)
	for k := 0; k < mid; k++ {
		ids[removable[k]] = true
	}
	for i < m && j < n {
		if !ids[i] && s[i] == p[j] {
			j++
		}
		i++
	}
	return j == n
}