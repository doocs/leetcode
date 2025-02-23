type MinHeap []int

func (h MinHeap) Len() int           { return len(h) }
func (h MinHeap) Less(i, j int) bool { return h[i] < h[j] }
func (h MinHeap) Swap(i, j int)      { h[i], h[j] = h[j], h[i] }
func (h *MinHeap) Push(x interface{}) {
	*h = append(*h, x.(int))
}
func (h *MinHeap) Pop() interface{} {
	old := *h
	n := len(old)
	x := old[n-1]
	*h = old[0 : n-1]
	return x
}

func maxSum(grid [][]int, limits []int, k int) int64 {
	pq := &MinHeap{}
	heap.Init(pq)
	n := len(grid)

	for i := 0; i < n; i++ {
		nums := make([]int, len(grid[i]))
		copy(nums, grid[i])
		limit := limits[i]
		sort.Ints(nums)

		for j := 0; j < limit; j++ {
			heap.Push(pq, nums[len(nums)-j-1])
			if pq.Len() > k {
				heap.Pop(pq)
			}
		}
	}

	var ans int64 = 0
	for pq.Len() > 0 {
		ans += int64(heap.Pop(pq).(int))
	}

	return ans
}
