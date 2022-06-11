func isLongPressedName(name string, typed string) bool {
	m, n := len(name), len(typed)
	i, j := 0, 0
	for ; i < m && j < n; i, j = i+1, j+1 {
		if name[i] != typed[j] {
			return false
		}
		cnt1, cnt2 := 0, 0
		c := name[i]
		for i+1 < m && name[i+1] == c {
			i++
			cnt1++
		}
		for j+1 < n && typed[j+1] == c {
			j++
			cnt2++
		}
		if cnt1 > cnt2 {
			return false
		}
	}
	return i == m && j == n
}