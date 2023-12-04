func countDivisibleSubstrings(word string) (ans int) {
	d := []string{"ab", "cde", "fgh", "ijk", "lmn", "opq", "rst", "uvw", "xyz"}
	mp := [26]int{}
	for i, s := range d {
		for _, c := range s {
			mp[c-'a'] = i + 1
		}
	}
	for i := 0; i < 10; i++ {
		cnt := map[int]int{0: 1}
		s := 0
		for _, c := range word {
			s += mp[c-'a'] - i
			ans += cnt[s]
			cnt[s]++
		}
	}
	return
}