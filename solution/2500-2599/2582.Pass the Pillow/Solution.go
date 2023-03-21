func passThePillow(n int, time int) int {
	k, mod := time/(n-1), time%(n-1)
	if k&1 == 1 {
		return n - mod
	}
	return mod + 1
}