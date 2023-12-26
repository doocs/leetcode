func isPossible(target []int) bool {
	pq := &hp{target}
	s := 0
	for _, x := range target {
		s += x
	}
	heap.Init(pq)
	for target[0] > 1 {
		mx := target[0]
		t := s - mx
		if t < 1 || mx-t < 1 {
			return false
		}
		x := mx % t
		if x == 0 {
			x = t
		}
		target[0] = x
		heap.Fix(pq, 0)
		s = s - mx + x
	}
	return true
}

type hp struct{ sort.IntSlice }

func (h hp) Less(i, j int) bool { return h.IntSlice[i] > h.IntSlice[j] }
func (hp) Pop() (_ any)         { return }
func (hp) Push(any)             {}