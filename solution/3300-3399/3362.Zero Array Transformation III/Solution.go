func maxRemoval(nums []int, queries [][]int) int {
	sort.Slice(queries, func(i, j int) bool {
		return queries[i][0] < queries[j][0]
	})

	var h hp
	heap.Init(&h)

	n := len(nums)
	d := make([]int, n+1)
	s, j := 0, 0

	for i := 0; i < n; i++ {
		s += d[i]
		for j < len(queries) && queries[j][0] <= i {
			heap.Push(&h, queries[j][1])
			j++
		}
		for s < nums[i] && h.Len() > 0 && h.IntSlice[0] >= i {
			s++
			end := heap.Pop(&h).(int)
			if end+1 < len(d) {
				d[end+1]--
			}
		}
		if s < nums[i] {
			return -1
		}
	}

	return h.Len()
}

type hp struct{ sort.IntSlice }

func (h hp) Less(i, j int) bool { return h.IntSlice[i] > h.IntSlice[j] }
func (h *hp) Push(v any)        { h.IntSlice = append(h.IntSlice, v.(int)) }
func (h *hp) Pop() any {
	a := h.IntSlice
	v := a[len(a)-1]
	h.IntSlice = a[:len(a)-1]
	return v
}
