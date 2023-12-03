func countDivisibleSubstrings(word string) (ans int) {
	d := []string{"ab", "cde", "fgh", "ijk", "lmn", "opq", "rst", "uvw", "xyz"}
	mp := [26]int{}
	for i, s := range d {
		for _, c := range s {
			mp[c-'a'] = i + 1
		}
	}
	n := len(word)
	for i := 0; i < n; i++ {
		s := 0
		for j := i; j < n; j++ {
			s += mp[word[j]-'a']
			if s%(j-i+1) == 0 {
				ans++
			}
		}
	}
	return
}