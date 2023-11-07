func maxScore(nums1 []int, nums2 []int, k int) int64 {
	type pair struct{ a, b int }
	nums := []pair{}
	for i, a := range nums1 {
		b := nums2[i]
		nums = append(nums, pair{a, b})
	}
	sort.Slice(nums, func(i, j int) bool { return nums[i].b > nums[j].b })
	q := hp{}
	var ans, s int
	for _, e := range nums {
		a, b := e.a, e.b
		s += a
		heap.Push(&q, a)
		if q.Len() == k {
			ans = max(ans, s*b)
			s -= heap.Pop(&q).(int)
		}
	}
	return int64(ans)
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