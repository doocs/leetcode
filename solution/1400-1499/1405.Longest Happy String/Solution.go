type pair struct {
	c   byte
	num int
}

type hp []pair

func (a hp) Len() int            { return len(a) }
func (a hp) Swap(i, j int)       { a[i], a[j] = a[j], a[i] }
func (a hp) Less(i, j int) bool  { return a[i].num > a[j].num }
func (a *hp) Push(x interface{}) { *a = append(*a, x.(pair)) }
func (a *hp) Pop() interface{}   { l := len(*a); t := (*a)[l-1]; *a = (*a)[:l-1]; return t }

func longestDiverseString(a int, b int, c int) string {
	var h hp
	if a > 0 {
		heap.Push(&h, pair{'a', a})
	}
	if b > 0 {
		heap.Push(&h, pair{'b', b})
	}
	if c > 0 {
		heap.Push(&h, pair{'c', c})
	}

	var ans []byte
	for len(h) > 0 {
		cur := heap.Pop(&h).(pair)
		if len(ans) >= 2 && ans[len(ans)-1] == cur.c && ans[len(ans)-2] == cur.c {
			if len(h) == 0 {
				break
			}
			next := heap.Pop(&h).(pair)
			ans = append(ans, next.c)
			if next.num > 1 {
				next.num--
				heap.Push(&h, next)
			}
			heap.Push(&h, cur)
		} else {
			ans = append(ans, cur.c)
			if cur.num > 1 {
				cur.num--
				heap.Push(&h, cur)
			}
		}
	}

	return string(ans)
}
