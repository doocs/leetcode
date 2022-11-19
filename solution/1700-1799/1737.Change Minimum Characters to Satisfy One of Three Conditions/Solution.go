func minCharacters(a string, b string) int {
	cnt1 := [26]int{}
	cnt2 := [26]int{}
	for _, c := range a {
		cnt1[c-'a']++
	}
	for _, c := range b {
		cnt2[c-'a']++
	}
	m, n := len(a), len(b)
	ans := m + n
	for i := 0; i < 26; i++ {
		ans = min(ans, m+n-cnt1[i]-cnt2[i])
	}
	f := func(cnt1, cnt2 [26]int) {
		for i := 1; i < 26; i++ {
			t := 0
			for j := i; j < 26; j++ {
				t += cnt1[j]
			}
			for j := 0; j < i; j++ {
				t += cnt2[j]
			}
			ans = min(ans, t)
		}
	}
	f(cnt1, cnt2)
	f(cnt2, cnt1)
	return ans
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}