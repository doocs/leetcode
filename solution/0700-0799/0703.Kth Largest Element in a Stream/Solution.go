type KthLargest struct {
	k    int
	minQ hp
}

func Constructor(k int, nums []int) KthLargest {
	minQ := hp{}
	this := KthLargest{k, minQ}
	for _, x := range nums {
		this.Add(x)
	}
	return this
}

func (this *KthLargest) Add(val int) int {
	heap.Push(&this.minQ, val)
	if this.minQ.Len() > this.k {
		heap.Pop(&this.minQ)
	}
	return this.minQ.IntSlice[0]
}

type hp struct{ sort.IntSlice }

func (h *hp) Less(i, j int) bool { return h.IntSlice[i] < h.IntSlice[j] }
func (h *hp) Pop() interface{} {
	old := h.IntSlice
	n := len(old)
	x := old[n-1]
	h.IntSlice = old[0 : n-1]
	return x
}
func (h *hp) Push(x interface{}) {
	h.IntSlice = append(h.IntSlice, x.(int))
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * obj := Constructor(k, nums);
 * param_1 := obj.Add(val);
 */