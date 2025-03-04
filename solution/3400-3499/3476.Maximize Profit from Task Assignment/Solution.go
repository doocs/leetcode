func maxProfit(workers []int, tasks [][]int) (ans int64) {
	d := make(map[int]*hp)
	for _, t := range tasks {
		skill, profit := t[0], t[1]
		if _, ok := d[skill]; !ok {
			d[skill] = &hp{}
		}
		d[skill].push(profit)
	}
	for _, skill := range workers {
		if _, ok := d[skill]; !ok {
			continue
		}
		ans += int64(d[skill].pop())
		if d[skill].Len() == 0 {
			delete(d, skill)
		}
	}
	mx := 0
	for _, pq := range d {
		for pq.Len() > 0 {
			mx = max(mx, pq.pop())
		}
	}
	ans += int64(mx)
	return
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
func (h *hp) push(v int) { heap.Push(h, v) }
func (h *hp) pop() int   { return heap.Pop(h).(int) }
