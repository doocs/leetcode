func getFinalState(nums []int, k int, multiplier int) []int {
	h := &hp{nums: nums}
	for i := range nums {
		heap.Push(h, i)
	}

	for k > 0 {
		i := heap.Pop(h).(int)
		nums[i] *= multiplier
		heap.Push(h, i)
		k--
	}

	return nums
}

type hp struct {
	sort.IntSlice
	nums []int
}

func (h *hp) Less(i, j int) bool {
	if h.nums[h.IntSlice[i]] == h.nums[h.IntSlice[j]] {
		return h.IntSlice[i] < h.IntSlice[j]
	}
	return h.nums[h.IntSlice[i]] < h.nums[h.IntSlice[j]]
}

func (h *hp) Pop() any {
	old := h.IntSlice
	n := len(old)
	x := old[n-1]
	h.IntSlice = old[:n-1]
	return x
}

func (h *hp) Push(x any) {
	h.IntSlice = append(h.IntSlice, x.(int))
}
