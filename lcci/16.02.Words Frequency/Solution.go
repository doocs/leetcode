type WordsFrequency struct {
	cnt map[string]int
}

func Constructor(book []string) WordsFrequency {
	cnt := map[string]int{}
	for _, x := range book {
		cnt[x]++
	}
	return WordsFrequency{cnt}
}

func (this *WordsFrequency) Get(word string) int {
	return this.cnt[word]
}

/**
 * Your WordsFrequency object will be instantiated and called as such:
 * obj := Constructor(book);
 * param_1 := obj.Get(word);
 */