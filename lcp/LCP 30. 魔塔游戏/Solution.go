func magicTower(nums []int) (ans int) {
	q := hp{}
	blood, v := 1, 0
	for _, x := range nums {
		if x < 0 {
			heap.Push(&q, x)
		}
		blood += x
		if blood <= 0 {
			ans++
			t := heap.Pop(&q).(int)
			v += t
			blood -= t
		}
	}
	if blood+v <= 0 {
		return -1
	}
	return ans
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