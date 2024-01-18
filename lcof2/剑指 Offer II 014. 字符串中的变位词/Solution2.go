func checkInclusion(s1 string, s2 string) bool {
	m, n := len(s1), len(s2)
	if m > n {
		return false
	}
	cnt := [26]int{}
	for i := 0; i < m; i++ {
		cnt[s1[i]-'a']--
		cnt[s2[i]-'a']++
	}
	diff := 0
	for _, x := range cnt {
		if x != 0 {
			diff++
		}
	}
	if diff == 0 {
		return true
	}
	for i := m; i < n; i++ {
		a, b := s2[i-m]-'a', s2[i]-'a'
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
			return true
		}
	}
	return false
}