func oneEditAway(first string, second string) bool {
	m, n := len(first), len(second)
	if m < n {
		return oneEditAway(second, first)
	}
	if m-n > 1 {
		return false
	}
	cnt := 0
	if m == n {
		for i := 0; i < n; i++ {
			if first[i] != second[i] {
				if cnt++; cnt > 1 {
					return false
				}
			}
		}
		return true
	}
	for i, j := 0, 0; i < m; i++ {
		if j == n || (j < n && first[i] != second[j]) {
			cnt++
		} else {
			j++
		}
	}
	return cnt < 2
}