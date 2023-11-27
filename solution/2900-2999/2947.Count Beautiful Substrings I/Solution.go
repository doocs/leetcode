func beautifulSubstrings(s string, k int) (ans int) {
	n := len(s)
	vs := [26]int{}
	for _, c := range "aeiou" {
		vs[c-'a'] = 1
	}
	for i := 0; i < n; i++ {
		vowels := 0
		for j := i; j < n; j++ {
			vowels += vs[s[j]-'a']
			consonants := j - i + 1 - vowels
			if vowels == consonants && vowels*consonants%k == 0 {
				ans++
			}
		}
	}
	return
}