func nthSuperUglyNumber(n int, primes []int) (x int) {
	q := hp{[]int{1}}
	for n > 0 {
		n--
		x = heap.Pop(&q).(int)
		for _, k := range primes {
			if x <= math.MaxInt32/k {
				heap.Push(&q, k*x)
			}
			if x%k == 0 {
				break
			}
		}
	}
	return
}

type hp struct{ sort.IntSlice }

func (h *hp) Push(v interface{}) { h.IntSlice = append(h.IntSlice, v.(int)) }
func (h *hp) Pop() interface{} {
	a := h.IntSlice
	v := a[len(a)-1]
	h.IntSlice = a[:len(a)-1]
	return v
}