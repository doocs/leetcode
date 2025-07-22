func kthCharacter(k int) byte {
	word := []int{0}
	for len(word) < k {
		m := len(word)
		for i := 0; i < m; i++ {
			word = append(word, (word[i]+1)%26)
		}
	}
	return 'a' + byte(word[k-1])
}
