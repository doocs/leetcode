type DinnerPlates struct {
	capacity int
	stacks   [][]int
	notFull  *redblacktree.Tree
}

func Constructor(capacity int) DinnerPlates {
	return DinnerPlates{capacity: capacity, notFull: redblacktree.NewWithIntComparator()}
}

func (this *DinnerPlates) Push(val int) {
	if this.notFull.Size() == 0 {
		this.stacks = append(this.stacks, []int{val})
		if this.capacity > 1 {
			this.notFull.Put(len(this.stacks)-1, nil)
		}
	} else {
		index, _ := this.notFull.Left().Key.(int)
		this.stacks[index] = append(this.stacks[index], val)
		if len(this.stacks[index]) == this.capacity {
			this.notFull.Remove(index)
		}
	}
}

func (this *DinnerPlates) Pop() int {
	return this.PopAtStack(len(this.stacks) - 1)
}

func (this *DinnerPlates) PopAtStack(index int) int {
	if index < 0 || index >= len(this.stacks) || len(this.stacks[index]) == 0 {
		return -1
	}
	val := this.stacks[index][len(this.stacks[index])-1]
	this.stacks[index] = this.stacks[index][:len(this.stacks[index])-1]
	if index == len(this.stacks)-1 && len(this.stacks[index]) == 0 {
		for len(this.stacks) > 0 && len(this.stacks[len(this.stacks)-1]) == 0 {
			this.notFull.Remove(len(this.stacks) - 1)
			this.stacks = this.stacks[:len(this.stacks)-1]
		}
	} else {
		this.notFull.Put(index, nil)
	}
	return val
}

/**
 * Your DinnerPlates object will be instantiated and called as such:
 * obj := Constructor(capacity);
 * obj.Push(val);
 * param_2 := obj.Pop();
 * param_3 := obj.PopAtStack(index);
 */