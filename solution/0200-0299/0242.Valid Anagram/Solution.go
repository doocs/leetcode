func isAnagram(s string, t string) bool {
	if len(s) != len(t) {
		return false
	}
	var chars [26]int
	for i := 0; i < len(s); i++ {
		chars[s[i]-'a']++
		chars[t[i]-'a']--
	}
	for _, c := range chars {
		if c != 0 {
			return false
		}
	}
	return true
}