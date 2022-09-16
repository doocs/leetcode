func furthestBuilding(heights []int, bricks int, ladders int) int {
	q := hp{}
	n := len(heights)
	for i, a := range heights[:n-1] {
		b := heights[i+1]
		d := b - a
		if d > 0 {
			heap.Push(&q, d)
			if q.Len() > ladders {
				bricks -= heap.Pop(&q).(int)
				if bricks < 0 {
					return i
				}
			}
		}
	}
	return n - 1
}

type hp struct{ sort.IntSlice }

func (h *hp) Push(v interface{}) { h.IntSlice = append(h.IntSlice, v.(int)) }
func (h *hp) Pop() interface{} {
	a := h.IntSlice
	v := a[len(a)-1]
	h.IntSlice = a[:len(a)-1]
	return v
}