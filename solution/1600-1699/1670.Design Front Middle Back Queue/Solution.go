type FrontMiddleBackQueue struct {
	q1, q2 Deque
}

func Constructor() FrontMiddleBackQueue {
	return FrontMiddleBackQueue{}
}

func (this *FrontMiddleBackQueue) PushFront(val int) {
	this.q1.PushFront(val)
	this.rebalance()
}

func (this *FrontMiddleBackQueue) PushMiddle(val int) {
	this.q1.PushBack(val)
	this.rebalance()
}

func (this *FrontMiddleBackQueue) PushBack(val int) {
	this.q2.PushBack(val)
	this.rebalance()
}

func (this *FrontMiddleBackQueue) PopFront() int {
	if this.q1.Empty() && this.q2.Empty() {
		return -1
	}
	var val int
	if !this.q1.Empty() {
		val = this.q1.PopFront()
	} else {
		val = this.q2.PopFront()
	}
	this.rebalance()
	return val
}

func (this *FrontMiddleBackQueue) PopMiddle() int {
	if this.q1.Empty() && this.q2.Empty() {
		return -1
	}
	var val int
	if this.q1.Size() == this.q2.Size() {
		val = this.q1.PopBack()
	} else {
		val = this.q2.PopFront()
	}
	this.rebalance()
	return val
}

func (this *FrontMiddleBackQueue) PopBack() int {
	if this.q2.Empty() {
		return -1
	}
	val := this.q2.PopBack()
	this.rebalance()
	return val
}

func (this *FrontMiddleBackQueue) rebalance() {
	if this.q1.Size() > this.q2.Size() {
		this.q2.PushFront(this.q1.PopBack())
	}
	if this.q2.Size() > this.q1.Size()+1 {
		this.q1.PushBack(this.q2.PopFront())
	}
}

// template
type Deque struct{ l, r []int }

func (q Deque) Empty() bool {
	return len(q.l) == 0 && len(q.r) == 0
}

func (q Deque) Size() int {
	return len(q.l) + len(q.r)
}

func (q *Deque) PushFront(v int) {
	q.l = append(q.l, v)
}

func (q *Deque) PushBack(v int) {
	q.r = append(q.r, v)
}

func (q *Deque) PopFront() (v int) {
	if len(q.l) > 0 {
		q.l, v = q.l[:len(q.l)-1], q.l[len(q.l)-1]
	} else {
		v, q.r = q.r[0], q.r[1:]
	}
	return
}

func (q *Deque) PopBack() (v int) {
	if len(q.r) > 0 {
		q.r, v = q.r[:len(q.r)-1], q.r[len(q.r)-1]
	} else {
		v, q.l = q.l[0], q.l[1:]
	}
	return
}

func (q Deque) Front() int {
	if len(q.l) > 0 {
		return q.l[len(q.l)-1]
	}
	return q.r[0]
}

func (q Deque) Back() int {
	if len(q.r) > 0 {
		return q.r[len(q.r)-1]
	}
	return q.l[0]
}

func (q Deque) Get(i int) int {
	if i < len(q.l) {
		return q.l[len(q.l)-1-i]
	}
	return q.r[i-len(q.l)]
}

/**
 * Your FrontMiddleBackQueue object will be instantiated and called as such:
 * obj := Constructor();
 * obj.PushFront(val);
 * obj.PushMiddle(val);
 * obj.PushBack(val);
 * param_4 := obj.PopFront();
 * param_5 := obj.PopMiddle();
 * param_6 := obj.PopBack();
 */