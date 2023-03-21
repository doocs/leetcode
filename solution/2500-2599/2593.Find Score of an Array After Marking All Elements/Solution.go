func findScore(nums []int) (ans int64) {
	h := hp{}
	for i, x := range nums {
		heap.Push(&h, pair{x, i})
	}
	n := len(nums)
	vis := make([]bool, n)
	for len(h) > 0 {
		p := heap.Pop(&h).(pair)
		x, i := p.x, p.i
		ans += int64(x)
		vis[i] = true
		for _, j := range []int{i - 1, i + 1} {
			if j >= 0 && j < n {
				vis[j] = true
			}
		}
		for len(h) > 0 && vis[h[0].i] {
			heap.Pop(&h)
		}
	}
	return
}

type pair struct{ x, i int }
type hp []pair

func (h hp) Len() int            { return len(h) }
func (h hp) Less(i, j int) bool  { return h[i].x < h[j].x || (h[i].x == h[j].x && h[i].i < h[j].i) }
func (h hp) Swap(i, j int)       { h[i], h[j] = h[j], h[i] }
func (h *hp) Push(v interface{}) { *h = append(*h, v.(pair)) }
func (h *hp) Pop() interface{}   { a := *h; v := a[len(a)-1]; *h = a[:len(a)-1]; return v }