func maxSlidingWindow(nums []int, k int) (ans []int) {
	q := hp{}
	for i, v := range nums[:k-1] {
		heap.Push(&q, pair{v, i})
	}
	for i := k - 1; i < len(nums); i++ {
		heap.Push(&q, pair{nums[i], i})
		for q[0].i <= i-k {
			heap.Pop(&q)
		}
		ans = append(ans, q[0].v)
	}
	return
}

type pair struct{ v, i int }

type hp []pair

func (h hp) Len() int { return len(h) }
func (h hp) Less(i, j int) bool {
	a, b := h[i], h[j]
	return a.v > b.v || (a.v == b.v && i < j)
}
func (h hp) Swap(i, j int) { h[i], h[j] = h[j], h[i] }
func (h *hp) Push(v any)   { *h = append(*h, v.(pair)) }
func (h *hp) Pop() any     { a := *h; v := a[len(a)-1]; *h = a[:len(a)-1]; return v }