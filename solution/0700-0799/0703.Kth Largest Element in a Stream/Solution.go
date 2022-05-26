type KthLargest struct {
	h *IntHeap
	k int
}

func Constructor(k int, nums []int) KthLargest {
	h := &IntHeap{}
	heap.Init(h)
	for _, v := range nums {
		heap.Push(h, v)
	}

	for h.Len() > k {
		heap.Pop(h)
	}

	return KthLargest{
		h: h,
		k: k,
	}
}

func (this *KthLargest) Add(val int) int {
	heap.Push(this.h, val)
	for this.h.Len() > this.k {
		heap.Pop(this.h)
	}

	return this.h.Top()
}

func connectSticks(sticks []int) int {
	h := IntHeap(sticks)
	heap.Init(&h)
	res := 0
	for h.Len() > 1 {
		val := heap.Pop(&h).(int)
		val += heap.Pop(&h).(int)
		res += val
		heap.Push(&h, val)
	}
	return res
}

type IntHeap []int

func (h IntHeap) Len() int           { return len(h) }
func (h IntHeap) Less(i, j int) bool { return h[i] < h[j] }
func (h IntHeap) Swap(i, j int)      { h[i], h[j] = h[j], h[i] }
func (h *IntHeap) Push(x interface{}) {
	*h = append(*h, x.(int))
}
func (h *IntHeap) Pop() interface{} {
	old := *h
	n := len(old)
	x := old[n-1]
	*h = old[0 : n-1]
	return x
}

func (h *IntHeap) Top() int {
	if (*h).Len() == 0 {
		return 0
	}

	return (*h)[0]
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * obj := Constructor(k, nums);
 * param_1 := obj.Add(val);
 */