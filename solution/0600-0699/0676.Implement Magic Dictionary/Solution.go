type MagicDictionary struct {
	s   map[string]bool
	cnt map[string]int
}

/** Initialize your data structure here. */
func Constructor() MagicDictionary {
	return MagicDictionary{map[string]bool{}, map[string]int{}}
}

func (this *MagicDictionary) BuildDict(dictionary []string) {
	for _, word := range dictionary {
		this.s[word] = true
		for _, p := range gen(word) {
			this.cnt[p]++
		}
	}
}

func (this *MagicDictionary) Search(searchWord string) bool {
	for _, p := range gen(searchWord) {
		if this.cnt[p] > 1 || (this.cnt[p] == 1 && !this.s[searchWord]) {
			return true
		}
	}
	return false
}

func gen(word string) []string {
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