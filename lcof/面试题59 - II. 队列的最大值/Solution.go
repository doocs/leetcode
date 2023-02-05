type MaxQueue struct {
	q1, q2 []int
}

func Constructor() MaxQueue {
	return MaxQueue{[]int{}, []int{}}
}

func (this *MaxQueue) Max_value() int {
	if len(this.q2) == 0 {
		return -1
	}
	return this.q2[0]
}

func (this *MaxQueue) Push_back(value int) {
	for len(this.q2) > 0 && this.q2[len(this.q2)-1] < value {
		this.q2 = this.q2[:len(this.q2)-1]
	}
	this.q1 = append(this.q1, value)
	this.q2 = append(this.q2, value)
}

func (this *MaxQueue) Pop_front() int {
	if len(this.q1) == 0 {
		return -1
	}
	ans := this.q1[0]
	this.q1 = this.q1[1:]
	if this.q2[0] == ans {
		this.q2 = this.q2[1:]
	}
	return ans
}

/**
 * Your MaxQueue object will be instantiated and called as such:
 * obj := Constructor();
 * param_1 := obj.Max_value();
 * obj.Push_back(value);
 * param_3 := obj.Pop_front();
 */