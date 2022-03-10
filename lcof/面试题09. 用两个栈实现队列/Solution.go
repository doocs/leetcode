type CQueue struct {
	stk1 []int
	stk2 []int
}

func Constructor() CQueue {
	return CQueue{stk1: []int{}, stk2: []int{}}
}

func (this *CQueue) AppendTail(value int) {
	this.stk1 = append(this.stk1, value)
}

func (this *CQueue) DeleteHead() int {
	if len(this.stk2) == 0 {
		for len(this.stk1) > 0 {
			this.stk2 = append(this.stk2, this.stk1[len(this.stk1)-1])
			this.stk1 = this.stk1[0 : len(this.stk1)-1]
		}
	}
	if len(this.stk2) == 0 {
		return -1
	}
	ans := this.stk2[len(this.stk2)-1]
	this.stk2 = this.stk2[0 : len(this.stk2)-1]
	return ans
}

/**
 * Your CQueue object will be instantiated and called as such:
 * obj := Constructor();
 * obj.AppendTail(value);
 * param_2 := obj.DeleteHead();
 */