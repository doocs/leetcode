type TripleInOne struct {
	cap int
	stk []int
}

func Constructor(stackSize int) TripleInOne {
	return TripleInOne{stackSize, make([]int, stackSize*3+3)}
}

func (this *TripleInOne) Push(stackNum int, value int) {
	if this.stk[this.cap*3+stackNum] < this.cap {
		this.stk[this.cap*stackNum+this.stk[this.cap*3+stackNum]] = value
		this.stk[this.cap*3+stackNum]++
	}
}

func (this *TripleInOne) Pop(stackNum int) int {
	if this.IsEmpty(stackNum) {
		return -1
	}
	this.stk[this.cap*3+stackNum]--
	return this.stk[this.cap*stackNum+this.stk[this.cap*3+stackNum]]
}

func (this *TripleInOne) Peek(stackNum int) int {
	if this.IsEmpty(stackNum) {
		return -1
	}
	return this.stk[this.cap*stackNum+this.stk[this.cap*3+stackNum]-1]
}

func (this *TripleInOne) IsEmpty(stackNum int) bool {
	return this.stk[this.cap*3+stackNum] == 0
}

/**
 * Your TripleInOne object will be instantiated and called as such:
 * obj := Constructor(stackSize);
 * obj.Push(stackNum,value);
 * param_2 := obj.Pop(stackNum);
 * param_3 := obj.Peek(stackNum);
 * param_4 := obj.IsEmpty(stackNum);
 */