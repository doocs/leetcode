func digitCount(num string) bool {
	cnt := [10]int{}
	for _, c := range num {
		cnt[c-'0']++
	}
	for i, c := range num {
		if int(c-'0') != cnt[i] {
			return false
		}
	}
	return true
}