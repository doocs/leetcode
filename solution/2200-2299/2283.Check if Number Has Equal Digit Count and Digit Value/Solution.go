func digitCount(num string) bool {
	cnt := make([]int, 10)
	for _, c := range num {
		cnt[c-'0']++
	}
	for i, c := range num {
		v := int(c - '0')
		if cnt[i] != v {
			return false
		}
	}
	return true
}