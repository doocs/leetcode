type MedianFinder struct {
	small                hp
	large                hp
	delayed              map[int]int
	smallSize, largeSize int
	x                    int
}

func Constructor(x int) MedianFinder {
	return MedianFinder{hp{}, hp{}, map[int]int{}, 0, 0, x}
}

func (this *MedianFinder) AddNum(num int) {
	if this.smallSize < this.x || num <= -this.small.IntSlice[0] {
		heap.Push(&this.small, -num)
		this.smallSize++
	} else {
		heap.Push(&this.large, num)
		this.largeSize++
	}
	this.rebalance()
}

func (this *MedianFinder) Find() int {
	if this.smallSize == this.x {
		return -this.small.IntSlice[0]
	}
	return 0
}

func (this *MedianFinder) RemoveNum(num int) {
	this.delayed[num]++
	if num <= -this.small.IntSlice[0] {
		this.smallSize--
		if num == -this.small.IntSlice[0] {
			this.prune(&this.small)
		}
	} else {
		this.largeSize--
		if num == this.large.IntSlice[0] {
			this.prune(&this.large)
		}
	}
	this.rebalance()
}

func (this *MedianFinder) prune(pq *hp) {
	sign := 1
	if pq == &this.small {
		sign = -1
	}
	for pq.Len() > 0 && this.delayed[sign*pq.IntSlice[0]] > 0 {
		this.delayed[sign*pq.IntSlice[0]]--
		if this.delayed[sign*pq.IntSlice[0]] == 0 {
			delete(this.delayed, sign*pq.IntSlice[0])
		}
		heap.Pop(pq)
	}
}

func (this *MedianFinder) rebalance() {
	if this.smallSize > this.x {
		heap.Push(&this.large, -heap.Pop(&this.small).(int))
		this.smallSize--
		this.largeSize++
		this.prune(&this.small)
	} else if this.smallSize < this.x && this.largeSize > 0 {
		heap.Push(&this.small, -heap.Pop(&this.large).(int))
		this.smallSize++
		this.largeSize--
		this.prune(&this.large)
	}
}

type hp struct{ sort.IntSlice }

func (h hp) Less(i, j int) bool { return h.IntSlice[i] < h.IntSlice[j] }
func (h *hp) Push(v any)        { h.IntSlice = append(h.IntSlice, v.(int)) }
func (h *hp) Pop() any {
	a := h.IntSlice
	v := a[len(a)-1]
	h.IntSlice = a[:len(a)-1]
	return v
}

func getSubarrayBeauty(nums []int, k int, x int) []int {
	finder := Constructor(x)
	for _, num := range nums[:k] {
		if num < 0 {
			finder.AddNum(num)
		}
	}
	ans := []int{finder.Find()}
	for i := k; i < len(nums); i++ {
		if nums[i] < 0 {
			finder.AddNum(nums[i])
		}
		if nums[i-k] < 0 {
			finder.RemoveNum(nums[i-k])
		}
		ans = append(ans, finder.Find())
	}
	return ans
}