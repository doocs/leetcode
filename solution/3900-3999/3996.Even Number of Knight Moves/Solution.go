func canReach(start []int, target []int) bool {
	return (start[0]+start[1])%2 == (target[0]+target[1])%2
}
