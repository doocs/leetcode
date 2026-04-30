func sortVowels(s string) string {
	st := map[rune]bool{'a': true, 'e': true, 'i': true, 'o': true, 'u': true}
	var vowels []rune
	cnt := make(map[rune]int)
	for _, c := range s {
		if !st[c] {
			continue
		}
		if _, ok := cnt[c]; !ok {
			vowels = append(vowels, c)
		}
		cnt[c]++
	}
	sort.Slice(vowels, func(i, j int) bool {
		return cnt[vowels[i]] > cnt[vowels[j]]
	})
	ans := []rune(s)
	i := 0
	for k, c := range s {
		if !st[c] {
			continue
		}
		char := vowels[i]
		ans[k] = char
		cnt[char]--
		if cnt[char] == 0 {
			i++
		}
	}
	return string(ans)
}
