func minStoneSum(piles []int, k int) int {
    q := &hp{piles}
    heap.Init(q)
    for k > 0 {
        p := q.pop()
        q.push((p + 1) >> 1)
        k--
    }
    ans := 0
    for q.Len() > 0 {
        ans += q.pop()
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