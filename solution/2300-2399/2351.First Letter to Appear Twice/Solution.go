func repeatedCharacter(s string) byte {
	cnt := [26]int{}
	for i := 0; ; i++ {
		cnt[s[i]-'a']++
		if cnt[s[i]-'a'] == 2 {
			return s[i]
		}
	}
}