type FreqStack struct {
	cnt map[int]int
	q   hp
	ts  int
}

func Constructor() FreqStack {
	return FreqStack{map[int]int{}, hp{}, 0}
}

func (this *FreqStack) Push(val int) {
	this.cnt[val]++
	this.ts++
	heap.Push(&this.q, tuple{this.cnt[val], this.ts, val})
}

func (this *FreqStack) Pop() int {
	val := heap.Pop(&this.q).(tuple).val
	this.cnt[val]--
	return val
}

type tuple struct{ cnt, ts, val int }
type hp []tuple

func (h hp) Len() int { return len(h) }
func (h hp) Less(i, j int) bool {
	return h[i].cnt > h[j].cnt || h[i].cnt == h[j].cnt && h[i].ts > h[j].ts
}
func (h hp) Swap(i, j int) { h[i], h[j] = h[j], h[i] }
func (h *hp) Push(v any)   { *h = append(*h, v.(tuple)) }
func (h *hp) Pop() any     { a := *h; v := a[len(a)-1]; *h = a[:len(a)-1]; return v }

/**
 * Your FreqStack object will be instantiated and called as such:
 * obj := Constructor();
 * obj.Push(val);
 * param_2 := obj.Pop();
 */