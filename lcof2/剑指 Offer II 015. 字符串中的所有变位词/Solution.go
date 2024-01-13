func findAnagrams(s string, p string) (ans []int) {
	m, n := len(s), len(p)
	if m < n {
		return
	}
	var cnt1, cnt2 [26]int
	for i, ch := range p {
		cnt1[s[i]-'a']++
		cnt2[ch-'a']++
	}
	if cnt1 == cnt2 {
		ans = append(ans, 0)
	}
	for i := n; i < m; i++ {
		cnt1[s[i]-'a']++
		cnt1[s[i-n]-'a']--
		if cnt1 == cnt2 {
			ans = append(ans, i-n+1)
		}
	}
	return
}