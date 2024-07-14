func compressedString(word string) string {
	ans := []byte{}
	n := len(word)
	for i := 0; i < n; {
		j := i + 1
		for j < n && word[j] == word[i] {
			j++
		}
		k := j - i
		for k > 0 {
			x := min(9, k)
			ans = append(ans, byte('0'+x))
			ans = append(ans, word[i])
			k -= x
		}
		i = j
	}
	return string(ans)
}