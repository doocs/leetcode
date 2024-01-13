func kSimilarity(s1 string, s2 string) int {
	next := func(s string) []string {
		i := 0
		res := []string{}
		for ; s[i] == s2[i]; i++ {
		}
		for j := i + 1; j < len(s1); j++ {
			if s[j] == s2[i] && s[j] != s2[j] {
				res = append(res, s[:i]+string(s[j])+s[i+1:j]+string(s[i])+s[j+1:])
			}
		}
		return res
	}

	f := func(s string) int {
		cnt := 0
		for i := range s {
			if s[i] != s2[i] {
				cnt++
			}
		}
		return (cnt + 1) >> 1
	}

	q := hp{pair{f(s1), s1}}
	dist := map[string]int{s1: 0}
	for {
		s := heap.Pop(&q).(pair).s
		if s == s2 {
			return dist[s]
		}
		for _, nxt := range next(s) {
			if v, ok := dist[nxt]; !ok || v > dist[s]+1 {
				dist[nxt] = dist[s] + 1
				heap.Push(&q, pair{dist[nxt] + f(nxt), nxt})
			}
		}
	}
}

type pair struct {
	v int
	s string
}
type hp []pair

func (h hp) Len() int { return len(h) }
func (h hp) Less(i, j int) bool {
	a, b := h[i], h[j]
	return a.v < b.v
}
func (h hp) Swap(i, j int) { h[i], h[j] = h[j], h[i] }
func (h *hp) Push(v any)   { *h = append(*h, v.(pair)) }
func (h *hp) Pop() any     { a := *h; v := a[len(a)-1]; *h = a[:len(a)-1]; return v }