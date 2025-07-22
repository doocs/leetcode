func removeAnagrams(words []string) []string {
	ans := []string{words[0]}
	check := func(s, t string) bool {
		if len(s) != len(t) {
			return true
		}
		cnt := [26]int{}
		for _, c := range s {
			cnt[c-'a']++
		}
		for _, c := range t {
			cnt[c-'a']--
			if cnt[c-'a'] < 0 {
				return true
			}
		}
		return false
	}
	for i, t := range words[1:] {
		if check(words[i], t) {
			ans = append(ans, t)
		}
	}
	return ans
}
