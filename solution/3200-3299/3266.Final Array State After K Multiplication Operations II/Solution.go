func getFinalState(nums []int, k int, multiplier int) []int {
	if multiplier == 1 {
		return nums
	}
	n := len(nums)
	pq := make(hp, n)
	for i, x := range nums {
		pq[i] = pair{x, i}
	}
	heap.Init(&pq)
	m := slices.Max(nums)
	for ; k > 0 && pq[0].x < m; k-- {
		x := pq[0]
		heap.Pop(&pq)
		x.x *= multiplier
		heap.Push(&pq, x)
	}
	const mod int = 1e9 + 7

	for i := range nums {
		p := heap.Pop(&pq).(pair)
		x, j := p.x, p.i
		power := k / n
		if i < k%n {
			power++
		}
		nums[j] = (x % mod) * qpow(multiplier, power, mod) % mod
	}
	return nums
}

func qpow(a, n, mod int) int {
	ans := 1 % mod
	a = a % mod
	for n > 0 {
		if n&1 == 1 {
			ans = (ans * a) % mod
		}
		a = (a * a) % mod
		n >>= 1
	}
	return int(ans)
}

type pair struct{ x, i int }
type hp []pair

func (h hp) Len() int           { return len(h) }
func (h hp) Less(i, j int) bool { return h[i].x < h[j].x || h[i].x == h[j].x && h[i].i < h[j].i }
func (h hp) Swap(i, j int)      { h[i], h[j] = h[j], h[i] }
func (h *hp) Push(x any)        { *h = append(*h, x.(pair)) }
func (h *hp) Pop() any          { a := *h; x := a[len(a)-1]; *h = a[:len(a)-1]; return x }
