func reverseWords(s string) string {
	words := []string{}
	i, n := 0, len(s)
	for i < n {
		for i < n && s[i] == ' ' {
			i++
		}
		if i < n {
			j := i
			t := []byte{}
			for j < n && s[j] != ' ' {
				t = append(t, s[j])
				j++
			}
			words = append(words, string(t))
			i = j
		}
	}
	for i, j := 0, len(words)-1; i < j; i, j = i+1, j-1 {
		words[i], words[j] = words[j], words[i]
	}
	return strings.Join(words, " ")
}