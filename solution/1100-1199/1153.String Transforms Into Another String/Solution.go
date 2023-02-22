func canConvert(str1 string, str2 string) bool {
	if str1 == str2 {
		return true
	}
	s := map[rune]bool{}
	for _, c := range str2 {
		s[c] = true
		if len(s) == 26 {
			return false
		}
	}
	d := [26]int{}
	for i, c := range str1 {
		a, b := int(c-'a'), int(str2[i]-'a')
		if d[a] == 0 {
			d[a] = b + 1
		} else if d[a] != b+1 {
			return false
		}
	}
	return true
}