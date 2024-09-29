func countOfSubstrings(word string, k int) int {
	f := func(k int) int {
		var ans int = 0
		l, x := 0, 0
		cnt := make(map[rune]int)
		vowel := func(c rune) bool {
			return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u'
		}
		for _, c := range word {
			if vowel(c) {
				cnt[c]++
			} else {
				x++
			}
			for x >= k && len(cnt) == 5 {
				d := rune(word[l])
				l++
				if vowel(d) {
					cnt[d]--
					if cnt[d] == 0 {
						delete(cnt, d)
					}
				} else {
					x--
				}
			}
			ans += l
		}
		return ans
	}

	return f(k) - f(k+1)
}
