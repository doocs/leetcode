type CustomStack struct {
	s []int
	t int
}

func Constructor(maxSize int) CustomStack {
	s := make([]int, maxSize)
	return CustomStack{s, 0}
}

func (this *CustomStack) Push(x int) {
	if this.t < len(this.s) {
		this.s[this.t] = x
		this.t++
	}
}

func (this *CustomStack) Pop() int {
	if this.t == 0 {
		return -1
	}
	this.t--
	return this.s[this.t]
}

func (this *CustomStack) Increment(k int, val int) {
	for i := 0; i < k && i < this.t; i++ {
		this.s[i] += val
	}
}

/**
 * Your CustomStack object will be instantiated and called as such:
 * obj := Constructor(maxSize);
 * obj.Push(x);
 * param_2 := obj.Pop();
 * obj.Increment(k,val);
 */