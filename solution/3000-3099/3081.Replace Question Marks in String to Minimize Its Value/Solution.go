func minimizeStringValue(s string) string {
	cnt := [26]int{}
	k := 0
	for _, c := range s {
		if c == '?' {
			k++
		} else {
			cnt[c-'a']++
		}
	}
	pq := hp{}
	for i, c := range cnt {
		heap.Push(&pq, pair{c, i})
	}
	t := make([]int, k)
	for i := 0; i < k; i++ {
		p := heap.Pop(&pq).(pair)
		t[i] = p.c
		p.v++
		heap.Push(&pq, p)
	}
	sort.Ints(t)
	cs := []byte(s)
	j := 0
	for i, c := range cs {
		if c == '?' {
			cs[i] = byte(t[j] + 'a')
			j++
		}
	}
	return string(cs)
}

type pair struct{ v, c int }
type hp []pair

func (h hp) Len() int           { return len(h) }
func (h hp) Less(i, j int) bool { return h[i].v < h[j].v || h[i].v == h[j].v && h[i].c < h[j].c }
func (h hp) Swap(i, j int)      { h[i], h[j] = h[j], h[i] }
func (h *hp) Push(v any)        { *h = append(*h, v.(pair)) }
func (h *hp) Pop() any          { a := *h; v := a[len(a)-1]; *h = a[:len(a)-1]; return v }