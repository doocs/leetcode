type MagicDictionary struct {
	words   map[string]bool
	counter map[string]int
}

/** Initialize your data structure here. */
func Constructor() MagicDictionary {
	return MagicDictionary{
		words:   make(map[string]bool),
		counter: make(map[string]int),
	}
}

func (this *MagicDictionary) BuildDict(dictionary []string) {
	for _, word := range dictionary {
		this.words[word] = true
		for _, p := range patterns(word) {
			this.counter[p]++
		}
	}
}

func (this *MagicDictionary) Search(searchWord string) bool {
	for _, p := range patterns(searchWord) {
		if this.counter[p] > 1 || (this.counter[p] == 1 && !this.words[searchWord]) {
			return true
		}
	}
	return false
}

func patterns(word string) []string {
	var res []string
	for i := 0; i < len(word); i++ {
		res = append(res, word[:i]+"."+word[i+1:])
	}
	return res
}

/**
 * Your MagicDictionary object will be instantiated and called as such:
 * obj := Constructor();
 * obj.BuildDict(dictionary);
 * param_2 := obj.Search(searchWord);
 */