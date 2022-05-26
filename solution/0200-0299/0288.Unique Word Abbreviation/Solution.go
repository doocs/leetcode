type ValidWordAbbr struct {
	words map[string]map[string]bool
}

func Constructor(dictionary []string) ValidWordAbbr {
	words := make(map[string]map[string]bool)
	for _, word := range dictionary {
		abbr := wordAbbr(word)
		if words[abbr] == nil {
			words[abbr] = make(map[string]bool)
		}
		words[abbr][word] = true
	}
	return ValidWordAbbr{words}
}

func (this *ValidWordAbbr) IsUnique(word string) bool {
	abbr := wordAbbr(word)
	words := this.words[abbr]
	return words == nil || (len(words) == 1 && words[word])
}

func wordAbbr(s string) string {
	n := len(s)
	if n <= 2 {
		return s
	}
	return s[0:1] + strconv.Itoa(n-2) + s[n-1:]
}

/**
 * Your ValidWordAbbr object will be instantiated and called as such:
 * obj := Constructor(dictionary);
 * param_1 := obj.IsUnique(word);
 */