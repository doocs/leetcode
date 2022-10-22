type StackOfPlates struct {
	stk [][]int
	cap int
}

func Constructor(cap int) StackOfPlates {
	return StackOfPlates{[][]int{}, cap}
}

func (this *StackOfPlates) Push(val int) {
	if this.cap == 0 {
		return
	}
	if len(this.stk) == 0 || len(this.stk[len(this.stk)-1]) >= this.cap {
		this.stk = append(this.stk, []int{})
	}
	this.stk[len(this.stk)-1] = append(this.stk[len(this.stk)-1], val)
}

func (this *StackOfPlates) Pop() int {
	return this.PopAt(len(this.stk) - 1)
}

func (this *StackOfPlates) PopAt(index int) int {
	ans := -1
	if index >= 0 && index < len(this.stk) {
		t := this.stk[index]
		ans = t[len(t)-1]
		this.stk[index] = this.stk[index][:len(t)-1]
		if len(this.stk[index]) == 0 {
			this.stk = append(this.stk[:index], this.stk[index+1:]...)
		}
	}
	return ans
}

/**
 * Your StackOfPlates object will be instantiated and called as such:
 * obj := Constructor(cap);
 * obj.Push(val);
 * param_2 := obj.Pop();
 * param_3 := obj.PopAt(index);
 */