type MyCircularQueue struct {
	front    int
	size     int
	capacity int
	q        []int
}

func Constructor(k int) MyCircularQueue {
	q := make([]int, k)
	return MyCircularQueue{0, 0, k, q}
}

func (this *MyCircularQueue) EnQueue(value int) bool {
	if this.IsFull() {
		return false
	}
	idx := (this.front + this.size) % this.capacity
	this.q[idx] = value
	this.size++
	return true
}

func (this *MyCircularQueue) DeQueue() bool {
	if this.IsEmpty() {
		return false
	}
	this.front = (this.front + 1) % this.capacity
	this.size--
	return true
}

func (this *MyCircularQueue) Front() int {
	if this.IsEmpty() {
		return -1
	}
	return this.q[this.front]
}

func (this *MyCircularQueue) Rear() int {
	if this.IsEmpty() {
		return -1
	}
	idx := (this.front + this.size - 1) % this.capacity
	return this.q[idx]
}

func (this *MyCircularQueue) IsEmpty() bool {
	return this.size == 0
}

func (this *MyCircularQueue) IsFull() bool {
	return this.size == this.capacity
}

/**
 * Your MyCircularQueue object will be instantiated and called as such:
 * obj := Constructor(k);
 * param_1 := obj.EnQueue(value);
 * param_2 := obj.DeQueue();
 * param_3 := obj.Front();
 * param_4 := obj.Rear();
 * param_5 := obj.IsEmpty();
 * param_6 := obj.IsFull();
 */