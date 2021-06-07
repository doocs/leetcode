type MaxQueue struct {
	queue []int
	deque []int
}

func Constructor() MaxQueue {
	return MaxQueue{
		queue: make([]int, 0),
		deque: make([]int, 0),
	}
}

func (this *MaxQueue) Max_value() int {
	if len(this.deque) == 0 {
		return -1
	}
	return this.deque[0]
}

func (this *MaxQueue) Push_back(value int) {
	for len(this.deque) != 0 && this.deque[len(this.deque)-1] < value {
		this.deque = this.deque[:len(this.deque)-1]
	}
	this.deque = append(this.deque, value)
	this.queue = append(this.queue, value)
}

func (this *MaxQueue) Pop_front() int {
	if len(this.deque) == 0 {
		return -1
	}
	retVal := this.queue[0]
	this.queue = this.queue[1:]
	if this.deque[0] == retVal {
		this.deque = this.deque[1:]
	}
	return retVal
}
