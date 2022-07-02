func minRefuelStops(target int, startFuel int, stations [][]int) int {
	stations = append(stations, []int{target, 0})
	ans, prev := 0, 0
	q := &hp{}
	heap.Init(q)
	for _, s := range stations {
		d := s[0] - prev
		startFuel -= d
		for startFuel < 0 && q.Len() > 0 {
			startFuel += q.pop()
			ans++
		}
		if startFuel < 0 {
			return -1
		}
		q.push(s[1])
		prev = s[0]
	}
	return ans
}

type hp struct{ sort.IntSlice }

func (h hp) Less(i, j int) bool  { return h.IntSlice[i] > h.IntSlice[j] }
func (h *hp) Push(v interface{}) { h.IntSlice = append(h.IntSlice, v.(int)) }
func (h *hp) Pop() interface{} {
	a := h.IntSlice
	v := a[len(a)-1]
	h.IntSlice = a[:len(a)-1]
	return v
}
func (h *hp) push(v int) { heap.Push(h, v) }
func (h *hp) pop() int   { return heap.Pop(h).(int) }