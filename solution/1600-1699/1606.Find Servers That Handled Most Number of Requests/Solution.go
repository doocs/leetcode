func busiestServers(k int, arrival, load []int) []int {
	free := redblacktree.NewWithIntComparator()
	for i := 0; i < k; i++ {
		free.Put(i, nil)
	}
	busy := hp{}
	cnt := make([]int, k)
	for i, t := range arrival {
		for len(busy) > 0 && busy[0].end <= t {
			free.Put(busy[0].server, nil)
			heap.Pop(&busy)
		}
		if free.Size() == 0 {
			continue
		}
		p, _ := free.Ceiling(i % k)
		if p == nil {
			p = free.Left()
		}
		server := p.Key.(int)
		cnt[server]++
		heap.Push(&busy, pair{t + load[i], server})
		free.Remove(server)
	}
	mx := 0
	for _, v := range cnt {
		if v > mx {
			mx = v
		}
	}
	var ans []int
	for i, v := range cnt {
		if v == mx {
			ans = append(ans, i)
		}
	}
	return ans
}

type pair struct{ end, server int }
type hp []pair

func (h hp) Len() int            { return len(h) }
func (h hp) Less(i, j int) bool  { return h[i].end < h[j].end }
func (h hp) Swap(i, j int)       { h[i], h[j] = h[j], h[i] }
func (h *hp) Push(v interface{}) { *h = append(*h, v.(pair)) }
func (h *hp) Pop() interface{}   { a := *h; v := a[len(a)-1]; *h = a[:len(a)-1]; return v }