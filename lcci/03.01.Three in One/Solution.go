type TripleInOne struct {
	data      []int
	offset    [3]int
	stackSize int
}

func Constructor(stackSize int) TripleInOne {
	total := stackSize * 3
	data := make([]int, total)
	offset := [3]int{}
	for i := 0; i < 3; i++ {
		offset[i] = i * stackSize
	}
	return TripleInOne{
		data:      data,
		offset:    offset,
		stackSize: stackSize,
	}
}

func (this *TripleInOne) Push(stackNum int, value int) {
	i := this.offset[stackNum]
	if i < (stackNum+1)*this.stackSize {
		this.data[i] = value
		this.offset[stackNum]++
	}
}

func (this *TripleInOne) Pop(stackNum int) int {
	i := this.offset[stackNum]
	if i == stackNum*this.stackSize {
		return -1
	}
	this.offset[stackNum]--
	return this.data[i-1]
}

func (this *TripleInOne) Peek(stackNum int) int {
	i := this.offset[stackNum]
	if i == stackNum*this.stackSize {
		return -1
	}
	return this.data[i-1]
}

func (this *TripleInOne) IsEmpty(stackNum int) bool {
	return this.offset[stackNum] == stackNum*this.stackSize
}

/**
 * Your TripleInOne object will be instantiated and called as such:
 * obj := Constructor(stackSize);
 * obj.Push(stackNum,value);
 * param_2 := obj.Pop(stackNum);
 * param_3 := obj.Peek(stackNum);
 * param_4 := obj.IsEmpty(stackNum);
 */
