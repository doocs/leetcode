func digitCount(num string) bool {
	cnt := [10]int{}
	for _, c := range num {
		cnt[c-'0']++
	}
	for i, v := range num {
		if cnt[i] != int(v-'0') {
			return false
		}
	}
	return true
}