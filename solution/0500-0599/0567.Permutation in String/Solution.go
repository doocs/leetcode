func checkInclusion(s1 string, s2 string) bool {
	n, m := len(s1), len(s2)
	if n > m {
		return false
	}
	cnt := [26]int{}
	for i := range s1 {
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
	for i := n; i < m; i++ {
		a, b := s2[i-n]-'a', s2[i]-'a'
		if cnt[b] == 0 {
			diff++
		}
		cnt[b]++
		if cnt[b] == 0 {
			diff--
		}
		if cnt[a] == 0 {
			diff++
		}
		cnt[a]--
		if cnt[a] == 0 {
			diff--
		}
		if diff == 0 {
			return true
		}
	}
	return false
}