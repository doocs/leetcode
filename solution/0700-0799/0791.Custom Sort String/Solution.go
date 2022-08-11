func customSortString(order string, s string) string {
	cnt := make([]int, 26)
	for _, c := range s {
		cnt[c-'a']++
	}
	ans := []rune{}
	for _, c := range order {
		for cnt[c-'a'] > 0 {
			ans = append(ans, c)
			cnt[c-'a']--
		}
	}
	for _, c := range s {
		for cnt[c-'a'] > 0 {
			ans = append(ans, c)
			cnt[c-'a']--
		}
	}
	return string(ans)
}