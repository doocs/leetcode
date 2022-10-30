func secondGreaterElement(nums []int) []int {
	stk := []int{}
	q := hp{}
	n := len(nums)
	ans := make([]int, n)
	for i := range ans {
		ans[i] = -1
	}
	for i, v := range nums {
		for len(q) > 0 && q[0].v < v {
			ans[q[0].i] = v
			heap.Pop(&q)
		}
		for len(stk) > 0 && nums[stk[len(stk)-1]] < v {
			heap.Push(&q, pair{nums[stk[len(stk)-1]], stk[len(stk)-1]})
			stk = stk[:len(stk)-1]
		}
		stk = append(stk, i)
	}
	return ans
}

type pair struct{ v, i int }

type hp []pair

func (h hp) Len() int { return len(h) }
func (h hp) Less(i, j int) bool {
	a, b := h[i], h[j]
	return a.v < b.v
}
func (h hp) Swap(i, j int)       { h[i], h[j] = h[j], h[i] }
func (h *hp) Push(v interface{}) { *h = append(*h, v.(pair)) }
func (h *hp) Pop() interface{}   { a := *h; v := a[len(a)-1]; *h = a[:len(a)-1]; return v }