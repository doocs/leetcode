func mostFrequentIDs(nums []int, freq []int) []int64 {
	n := len(nums)
	cnt := map[int]int{}
	lazy := map[int]int{}
	ans := make([]int64, n)
	pq := hp{}
	heap.Init(&pq)
	for i, x := range nums {
		f := freq[i]
		lazy[cnt[x]]++
		cnt[x] += f
		heap.Push(&pq, cnt[x])
		for pq.Len() > 0 && lazy[pq.IntSlice[0]] > 0 {
			lazy[pq.IntSlice[0]]--
			heap.Pop(&pq)
		}
		if pq.Len() > 0 {
			ans[i] = int64(pq.IntSlice[0])
		}
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