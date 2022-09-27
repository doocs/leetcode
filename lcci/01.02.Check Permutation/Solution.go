func CheckPermutation(s1 string, s2 string) bool {
	if len(s1) != len(s2) {
		return false
	}
	cnt := make([]int, 26)
	for _, c := range s1 {
		cnt[c-'a']++
	}
	for _, c := range s2 {
		cnt[c-'a']--
		if cnt[c-'a'] < 0 {
			return false
		}
	}
	return true
}