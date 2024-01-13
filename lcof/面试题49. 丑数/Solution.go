func nthUglyNumber(n int) int {
	h := IntHeap([]int{1})
	heap.Init(&h)
	ans := 1
	vis := map[int]bool{1: true}
	for n > 0 {
		ans = heap.Pop(&h).(int)
		for _, v := range []int{2, 3, 5} {
			nxt := ans * v
			if !vis[nxt] {
				vis[nxt] = true
				heap.Push(&h, nxt)
			}
		}
		n--
	}
	return ans
}

type IntHeap []int

func (h IntHeap) Len() int           { return len(h) }
func (h IntHeap) Less(i, j int) bool { return h[i] < h[j] }
func (h IntHeap) Swap(i, j int)      { h[i], h[j] = h[j], h[i] }
func (h *IntHeap) Push(x any) {
	*h = append(*h, x.(int))
}
func (h *IntHeap) Pop() any {
	old := *h
	n := len(old)
	x := old[n-1]
	*h = old[0 : n-1]
	return x
}