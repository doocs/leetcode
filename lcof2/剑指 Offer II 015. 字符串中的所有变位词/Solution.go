func findAnagrams(s string, p string) (ans []int) {
	m, n := len(s), len(p)
	if m < n {
		return
	}
	cnt := [26]int{}
	for i := 0; i < n; i++ {
		cnt[s[i]-'a']++
		cnt[p[i]-'a']--
	}
	diff := 0
	for _, x := range cnt {
		if x != 0 {
			diff++
		}
	}
	if diff == 0 {
		ans = append(ans, 0)
	}
	for i := n; i < m; i++ {
		a, b := s[i-n]-'a', s[i]-'a'
		if cnt[a] == 0 {
			diff++
		}
		cnt[a]--
		if cnt[a] == 0 {
			diff--
		}
		if cnt[b] == 0 {
			diff++
		}
		cnt[b]++
		if cnt[b] == 0 {
			diff--
		}
		if diff == 0 {
			ans = append(ans, i-n+1)
		}
	}
	return
}