func isPossible(nums []int) bool {
	d := map[int]*hp{}
	for _, v := range nums {
		if d[v] == nil {
			d[v] = new(hp)
		}
		if h := d[v-1]; h != nil {
			heap.Push(d[v], heap.Pop(h).(int)+1)
			if h.Len() == 0 {
				delete(d, v-1)
			}
		} else {
			heap.Push(d[v], 1)
		}
	}
	for _, q := range d {
		if q.IntSlice[0] < 3 {
			return false
		}
	}
	return true
}

type hp struct{ sort.IntSlice }

func (h *hp) Push(v interface{}) { h.IntSlice = append(h.IntSlice, v.(int)) }
func (h *hp) Pop() interface{} {
	a := h.IntSlice
	v := a[len(a)-1]
	h.IntSlice = a[:len(a)-1]
	return v
}