type SortedStack struct {
	stk []int
}

func Constructor() SortedStack {
	return SortedStack{}
}

func (this *SortedStack) Push(val int) {
	t := make([]int, 0)
	for len(this.stk) > 0 && this.stk[len(this.stk)-1] < val {
		t = append(t, this.stk[len(this.stk)-1])
		this.stk = this.stk[:len(this.stk)-1]
	}
	this.stk = append(this.stk, val)
	for i := len(t) - 1; i >= 0; i-- {
		this.stk = append(this.stk, t[i])
	}
}

func (this *SortedStack) Pop() {
	if !this.IsEmpty() {
		this.stk = this.stk[:len(this.stk)-1]
	}
}

func (this *SortedStack) Peek() int {
	if this.IsEmpty() {
		return -1
	}
	return this.stk[len(this.stk)-1]
}

func (this *SortedStack) IsEmpty() bool {
	return len(this.stk) == 0
}

/**
 * Your SortedStack object will be instantiated and called as such:
 * obj := Constructor();
 * obj.Push(val);
 * obj.Pop();
 * param_3 := obj.Peek();
 * param_4 := obj.IsEmpty();
 */