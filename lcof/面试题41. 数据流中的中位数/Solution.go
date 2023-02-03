type MedianFinder struct {
	q1, q2 hp
}

/** initialize your data structure here. */
func Constructor() MedianFinder {
	return MedianFinder{hp{}, hp{}}
}

func (this *MedianFinder) AddNum(num int) {
	if this.q1.Len() > this.q2.Len() {
		heap.Push(&this.q1, num)
		heap.Push(&this.q2, -heap.Pop(&this.q1).(int))
	} else {
		heap.Push(&this.q2, -num)
		heap.Push(&this.q1, -heap.Pop(&this.q2).(int))
	}
}

func (this *MedianFinder) FindMedian() float64 {
	if this.q1.Len() > this.q2.Len() {
		return float64(this.q1.IntSlice[0])
	}
	return float64(this.q1.IntSlice[0]-this.q2.IntSlice[0]) / 2.0
}

type hp struct{ sort.IntSlice }

func (h hp) Less(i, j int) bool  { return h.IntSlice[i] < h.IntSlice[j] }
func (h *hp) Push(v interface{}) { h.IntSlice = append(h.IntSlice, v.(int)) }
func (h *hp) Pop() interface{} {
	a := h.IntSlice
	v := a[len(a)-1]
	h.IntSlice = a[:len(a)-1]
	return v
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * obj := Constructor();
 * obj.AddNum(num);
 * param_2 := obj.FindMedian();
 */