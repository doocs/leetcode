func mostBooked(n int, meetings [][]int) int {
	sort.Slice(meetings, func(i, j int) bool { return meetings[i][0] < meetings[j][0] })
	idle := hp{make([]int, n)}
	for i := 0; i < n; i++ {
		idle.IntSlice[i] = i
	}
	busy := hp2{}
	cnt := make([]int, n)
	for _, v := range meetings {
		s, e := v[0], v[1]
		for len(busy) > 0 && busy[0].end <= s {
			heap.Push(&idle, heap.Pop(&busy).(pair).i)
		}
		var i int
		if idle.Len() > 0 {
			i = heap.Pop(&idle).(int)
			heap.Push(&busy, pair{e, i})
		} else {
			x := heap.Pop(&busy).(pair)
			i = x.i
			heap.Push(&busy, pair{x.end + e - s, i})
		}
		cnt[i]++
	}
	ans := 0
	for i, v := range cnt {
		if cnt[ans] < v {
			ans = i
		}
	}
	return ans
}

type hp struct{ sort.IntSlice }

func (h *hp) Push(v interface{}) { h.IntSlice = append(h.IntSlice, v.(int)) }
func (h *hp) Pop() interface{} {
	a := h.IntSlice
	v := a[len(a)-1]
	h.IntSlice = a[:len(a)-1]
	return v
}

type pair struct{ end, i int }
type hp2 []pair

func (h hp2) Len() int { return len(h) }
func (h hp2) Less(i, j int) bool {
	a, b := h[i], h[j]
	return a.end < b.end || a.end == b.end && a.i < b.i
}
func (h hp2) Swap(i, j int)       { h[i], h[j] = h[j], h[i] }
func (h *hp2) Push(v interface{}) { *h = append(*h, v.(pair)) }
func (h *hp2) Pop() interface{}   { a := *h; v := a[len(a)-1]; *h = a[:len(a)-1]; return v }