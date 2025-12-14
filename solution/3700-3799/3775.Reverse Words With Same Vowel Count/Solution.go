func reverseWords(s string) string {
	words := strings.Fields(s)

	calc := func(w string) int {
		cnt := 0
		for _, c := range w {
			switch c {
			case 'a', 'e', 'i', 'o', 'u':
				cnt++
			}
		}
		return cnt
	}

	cnt := calc(words[0])
	var ans []string
	ans = append(ans, words[0])

	for i := 1; i < len(words); i++ {
		w := words[i]
		if calc(w) == cnt {
			b := []rune(w)
			for l, r := 0, len(b)-1; l < r; l, r = l+1, r-1 {
				b[l], b[r] = b[r], b[l]
			}
			w = string(b)
		}
		ans = append(ans, w)
	}

	return strings.Join(ans, " ")
}
