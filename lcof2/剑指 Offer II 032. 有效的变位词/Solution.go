func isAnagram(s string, t string) bool {
	m, n := len(s), len(t)
	if m != n || s == t {
		return false
	}
	cnt := [26]int{}
	for i, c := range s {
		cnt[c-'a']++
		cnt[t[i]-'a']--
	}
	for _, x := range cnt {
		if x != 0 {
			return false
		}
	}
	return true
}