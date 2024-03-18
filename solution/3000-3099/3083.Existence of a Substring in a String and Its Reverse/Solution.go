func isSubstringPresent(s string) bool {
	st := [26][26]bool{}
	for i := 0; i < len(s)-1; i++ {
		st[s[i+1]-'a'][s[i]-'a'] = true
	}
	for i := 0; i < len(s)-1; i++ {
		if st[s[i]-'a'][s[i+1]-'a'] {
			return true
		}
	}
	return false
}