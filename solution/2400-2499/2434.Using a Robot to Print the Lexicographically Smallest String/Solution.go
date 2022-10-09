func robotWithString(s string) string {
	cnt := make([]int, 26)
	for _, c := range s {
		cnt[c-'a']++
	}
	mi := byte('a')
	stk := []byte{}
	ans := []byte{}
	for i := range s {
		cnt[s[i]-'a']--
		for mi < 'z' && cnt[mi-'a'] == 0 {
			mi++
		}
		stk = append(stk, s[i])
		for len(stk) > 0 && stk[len(stk)-1] <= mi {
			ans = append(ans, stk[len(stk)-1])
			stk = stk[:len(stk)-1]
		}
	}
	return string(ans)
}