type Ugly struct{ value, prime, index int }
type Queue []Ugly

func (u Queue) Len() int           { return len(u) }
func (u Queue) Swap(i, j int)      { u[i], u[j] = u[j], u[i] }
func (u Queue) Less(i, j int) bool { return u[i].value < u[j].value }
func (u *Queue) Push(v any)        { *u = append(*u, v.(Ugly)) }
func (u *Queue) Pop() any {
	old, x := *u, (*u)[len(*u)-1]
	*u = old[:len(old)-1]
	return x
}

func nthSuperUglyNumber(n int, primes []int) int {
	ugly, pq, p := make([]int, n+1), &Queue{}, 2
	ugly[1] = 1
	heap.Init(pq)
	for _, v := range primes {
		heap.Push(pq, Ugly{value: v, prime: v, index: 2})
	}
	for p <= n {
		top := heap.Pop(pq).(Ugly)
		if ugly[p-1] != top.value {
			ugly[p], p = top.value, p+1
		}
		top.value, top.index = ugly[top.index]*top.prime, top.index+1
		heap.Push(pq, top)
	}
	return ugly[n]
}