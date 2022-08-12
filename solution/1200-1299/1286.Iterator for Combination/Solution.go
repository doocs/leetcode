type CombinationIterator struct {
	curr int
	size int
	cs   []byte
}

func Constructor(characters string, combinationLength int) CombinationIterator {
	n := len(characters)
	curr := (1 << n) - 1
	size := combinationLength
	cs := make([]byte, n)
	for i := range characters {
		cs[n-i-1] = characters[i]
	}
	return CombinationIterator{curr, size, cs}
}

func (this *CombinationIterator) Next() string {
	for this.curr >= 0 && bits.OnesCount(uint(this.curr)) != this.size {
		this.curr--
	}
	ans := []byte{}
	for i := range this.cs {
		if (this.curr >> i & 1) == 1 {
			ans = append(ans, this.cs[i])
		}
	}
	for i, j := 0, len(ans)-1; i < j; i, j = i+1, j-1 {
		ans[i], ans[j] = ans[j], ans[i]
	}
	this.curr--
	return string(ans)
}

func (this *CombinationIterator) HasNext() bool {
	for this.curr >= 0 && bits.OnesCount(uint(this.curr)) != this.size {
		this.curr--
	}
	return this.curr >= 0
}

/**
 * Your CombinationIterator object will be instantiated and called as such:
 * obj := Constructor(characters, combinationLength);
 * param_1 := obj.Next();
 * param_2 := obj.HasNext();
 */