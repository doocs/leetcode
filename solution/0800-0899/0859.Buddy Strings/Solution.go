func buddyStrings(s string, goal string) bool {
	m, n := len(s), len(goal)
	if m != n {
		return false
	}
	diff := 0
	cnt1 := make([]int, 26)
	cnt2 := make([]int, 26)
	for i := 0; i < n; i++ {
		cnt1[s[i]-'a']++
		cnt2[goal[i]-'a']++
		if s[i] != goal[i] {
			diff++
		}
	}
	f := false
	for i := 0; i < 26; i++ {
		if cnt1[i] != cnt2[i] {
			return false
		}
		if cnt1[i] > 1 {
			f = true
		}
	}
	return diff == 2 || (diff == 0 && f)
}