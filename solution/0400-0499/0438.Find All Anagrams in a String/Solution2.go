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
	j := 0
	for i, c := range s {
		cnt2[c-'a']++
		for cnt2[c-'a'] > cnt1[c-'a'] {
			cnt2[s[j]-'a']--
			j++
		}
		if i-j+1 == n {
			ans = append(ans, j)
		}
	}
	return
}