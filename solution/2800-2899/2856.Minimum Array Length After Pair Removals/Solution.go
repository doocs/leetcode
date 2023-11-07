func minLengthAfterRemovals(nums []int) int {
	cnt := map[int]int{}
	for _, x := range nums {
		cnt[x]++
	}
	h := &hp{}
	for _, x := range cnt {
		h.push(x)
	}
	ans := len(nums)
	for h.Len() > 1 {
		x, y := h.pop(), h.pop()
		if x > 1 {
			h.push(x - 1)
		}
		if y > 1 {
			h.push(y - 1)
		}
		ans -= 2
	}
	return ans
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