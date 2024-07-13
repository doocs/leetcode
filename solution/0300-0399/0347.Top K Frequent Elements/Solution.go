func topKFrequent(nums []int, k int) []int {
	cnt := map[int]int{}
	for _, x := range nums {
		cnt[x]++
	}
	pq := hp{}
	for x, c := range cnt {
		heap.Push(&pq, pair{x, c})
		if pq.Len() > k {
			heap.Pop(&pq)
		}
	}
	ans := make([]int, k)
	for i := 0; i < k; i++ {
		ans[i] = heap.Pop(&pq).(pair).v
	}
	return ans
}

type pair struct{ v, cnt int }
type hp []pair

func (h hp) Len() int           { return len(h) }
func (h hp) Less(i, j int) bool { return h[i].cnt < h[j].cnt }
func (h hp) Swap(i, j int)      { h[i], h[j] = h[j], h[i] }
func (h *hp) Push(v any)        { *h = append(*h, v.(pair)) }
func (h *hp) Pop() any          { a := *h; v := a[len(a)-1]; *h = a[:len(a)-1]; return v }
