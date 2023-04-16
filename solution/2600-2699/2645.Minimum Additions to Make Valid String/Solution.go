func addMinimum(word string) (ans int) {
	s := "abc"
	n := len(word)
	for i, j := 0, 0; j < n; i = (i + 1) % 3 {
		if word[j] != s[i] {
			ans++
		} else {
			j++
		}
	}
	if word[n-1] == 'b' {
		ans++
	} else if word[n-1] == 'a' {
		ans += 2
	}
	return
}
