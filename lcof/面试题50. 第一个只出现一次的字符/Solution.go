func firstUniqChar(s string) byte {
	cnt := [26]int{}
	for _, c := range s {
		cnt[c-'a']++
	}
	for _, c := range s {
		if cnt[c-'a'] == 1 {
			return byte(c)
		}
	}
	return ' '
}