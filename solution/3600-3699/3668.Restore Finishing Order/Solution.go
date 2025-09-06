func recoverOrder(order []int, friends []int) []int {
	n := len(order)
	d := make([]int, n+1)
	for i, x := range order {
		d[x] = i
	}
	sort.Slice(friends, func(i, j int) bool {
		return d[friends[i]] < d[friends[j]]
	})
	return friends
}
