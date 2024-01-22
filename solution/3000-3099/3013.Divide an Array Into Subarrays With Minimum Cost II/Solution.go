func minimumCost(nums []int, k int, dist int) int64 {
	res := nums[0] + mins((windowTopKSum(nums[1:], dist+1, k-1, true))...)
	return int64(res)
}

func mins(nums ...int) int {
	res := nums[0]
	for _, num := range nums {
		if num < res {
			res = num
		}
	}
	return res
}

func windowTopKSum(nums []int, windowSize, k int, min bool) []int {
	n := len(nums)
	ts := NewTopKSum(k, min)
	res := []int{}
	for right := 0; right < n; right++ {
		ts.Add(nums[right])
		if right >= windowSize {
			ts.Discard(nums[right-windowSize])
		}
		if right >= windowSize-1 {
			res = append(res, ts.Query())
		}
	}
	return res
}

type TopKSum struct {
	sum     int
	k       int
	in      *Heap
	out     *Heap
	dIn     *Heap
	dOut    *Heap
	counter map[int]int
}

func NewTopKSum(k int, min bool) *TopKSum {
	var less func(a, b int) bool
	if min {
		less = func(a, b int) bool { return a < b }
	} else {
		less = func(a, b int) bool { return a > b }
	}
	return &TopKSum{
		k:       k,
		in:      NewHeap(less),
		out:     NewHeap(less),
		dIn:     NewHeap(less),
		dOut:    NewHeap(less),
		counter: map[int]int{},
	}
}

func (t *TopKSum) Query() int {
	return t.sum
}

func (t *TopKSum) Add(x int) {
	t.counter[x]++
	t.in.Push(-x)
	t.sum += x
	t.modify()
}

func (t *TopKSum) Discard(x int) bool {
	if t.counter[x] == 0 {
		return false
	}
	t.counter[x]--
	if t.in.Len() > 0 && -t.in.Top() == x {
		t.sum -= x
		t.in.Pop()
	} else if t.in.Len() > 0 && -t.in.Top() > x {
		t.sum -= x
		t.dIn.Push(-x)
	} else {
		t.dOut.Push(x)
	}
	t.modify()
	return true
}

func (t *TopKSum) SetK(k int) {
	t.k = k
	t.modify()
}

func (t *TopKSum) GetK() int {
	return t.k
}

func (t *TopKSum) Len() int {
	return t.in.Len() + t.out.Len() - t.dIn.Len() - t.dOut.Len()
}

func (t *TopKSum) Has(x int) bool {
	return t.counter[x] > 0
}

func (t *TopKSum) modify() {
	for t.out.Len() > 0 && (t.in.Len()-t.dIn.Len() < t.k) {
		p := t.out.Pop()
		if t.dOut.Len() > 0 && p == t.dOut.Top() {
			t.dOut.Pop()
		} else {
			t.sum += p
			t.in.Push(-p)
		}
	}

	for t.in.Len()-t.dIn.Len() > t.k {
		p := -t.in.Pop()
		if t.dIn.Len() > 0 && p == -t.dIn.Top() {
			t.dIn.Pop()
		} else {
			t.sum -= p
			t.out.Push(p)
		}
	}

	for t.dIn.Len() > 0 && t.in.Top() == t.dIn.Top() {
		t.in.Pop()
		t.dIn.Pop()
	}
}

type H = int

func NewHeap(less func(a, b H) bool, nums ...H) *Heap {
	nums = append(nums[:0:0], nums...)
	heap := &Heap{less: less, data: nums}
	heap.heapify()
	return heap
}

type Heap struct {
	data []H
	less func(a, b H) bool
}

func (h *Heap) Push(value H) {
	h.data = append(h.data, value)
	h.pushUp(h.Len() - 1)
}

func (h *Heap) Pop() (value H) {
	if h.Len() == 0 {
		panic("heap is empty")
	}

	value = h.data[0]
	h.data[0] = h.data[h.Len()-1]
	h.data = h.data[:h.Len()-1]
	h.pushDown(0)
	return
}

func (h *Heap) Top() (value H) {
	value = h.data[0]
	return
}

func (h *Heap) Len() int { return len(h.data) }

func (h *Heap) heapify() {
	n := h.Len()
	for i := (n >> 1) - 1; i > -1; i-- {
		h.pushDown(i)
	}
}

func (h *Heap) pushUp(root int) {
	for parent := (root - 1) >> 1; parent >= 0 && h.less(h.data[root], h.data[parent]); parent = (root - 1) >> 1 {
		h.data[root], h.data[parent] = h.data[parent], h.data[root]
		root = parent
	}
}

func (h *Heap) pushDown(root int) {
	n := h.Len()
	for left := (root<<1 + 1); left < n; left = (root<<1 + 1) {
		right := left + 1
		minIndex := root

		if h.less(h.data[left], h.data[minIndex]) {
			minIndex = left
		}

		if right < n && h.less(h.data[right], h.data[minIndex]) {
			minIndex = right
		}

		if minIndex == root {
			return
		}
		h.data[root], h.data[minIndex] = h.data[minIndex], h.data[root]
		root = minIndex
	}
}
