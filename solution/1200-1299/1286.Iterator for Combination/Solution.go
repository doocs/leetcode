type CombinationIterator struct {
	cs  []string
	idx int
}

func Constructor(characters string, combinationLength int) CombinationIterator {
	t := []byte{}
	n := len(characters)
	cs := []string{}
	var dfs func(int)
	dfs = func(i int) {
		if len(t) == combinationLength {
			cs = append(cs, string(t))
			return
		}
		if i == n {
			return
		}
		t = append(t, characters[i])
		dfs(i + 1)
		t = t[:len(t)-1]
		dfs(i + 1)
	}
	dfs(0)
	return CombinationIterator{cs, 0}
}

func (this *CombinationIterator) Next() string {
	ans := this.cs[this.idx]
	this.idx++
	return ans
}

func (this *CombinationIterator) HasNext() bool {
	return this.idx < len(this.cs)
}

/**
 * Your CombinationIterator object will be instantiated and called as such:
 * obj := Constructor(characters, combinationLength);
 * param_1 := obj.Next();
 * param_2 := obj.HasNext();
 */