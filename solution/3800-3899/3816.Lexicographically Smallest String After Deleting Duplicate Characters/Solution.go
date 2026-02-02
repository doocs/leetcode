func lexSmallestAfterDeletion(s string) string {
	cnt := [26]int{}
	for _, c := range s {
		cnt[c-'a']++
	}
	stk := []byte{}
	for _, c := range s {
		for len(stk) > 0 && stk[len(stk)-1] > byte(c) && cnt[stk[len(stk)-1]-'a'] > 1 {
			cnt[stk[len(stk)-1]-'a']--
			stk = stk[:len(stk)-1]
		}
		stk = append(stk, byte(c))
	}
	for cnt[stk[len(stk)-1]-'a'] > 1 {
		cnt[stk[len(stk)-1]-'a']--
		stk = stk[:len(stk)-1]
	}
	return string(stk)
}
