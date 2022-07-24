func repeatedCharacter(s string) byte {
	cnt := make([]int, 26)
	for _, c := range s {
		cnt[c-'a']++
		if cnt[c-'a'] == 2 {
			return byte(c)
		}
	}
	return '.'
}