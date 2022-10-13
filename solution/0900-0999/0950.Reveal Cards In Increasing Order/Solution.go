func deckRevealedIncreasing(deck []int) []int {
	sort.Sort(sort.Reverse(sort.IntSlice(deck)))
	q := []int{}
	for _, v := range deck {
		if len(q) > 0 {
			q = append([]int{q[len(q)-1]}, q[:len(q)-1]...)
		}
		q = append([]int{v}, q...)
	}
	return q
}