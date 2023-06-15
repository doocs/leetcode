func findAnagrams(s string, p string) (ans []int) {
	m, n := len(s), len(p)
	if m < n {
		return
	}
	cnt1 := [26]int{}
	cnt2 := [26]int{}
	for _, c := range p {
		cnt1[c-'a']++
	}
	for _, c := range s[:n-1] {
		cnt2[c-'a']++
	}
	for i := n - 1; i < m; i++ {
		cnt2[s[i]-'a']++
		if cnt1 == cnt2 {
			ans = append(ans, i-n+1)
		}
		cnt2[s[i-n+1]-'a']--
	}
	return
}