func findTheWinner(n int, k int) int {
	if n == 1 {
		return 1
	}
	ans := (findTheWinner(n-1, k) + k) % n
	if ans == 0 {
		return n
	}
	return ans
}