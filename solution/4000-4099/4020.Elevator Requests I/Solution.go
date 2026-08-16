func elevatorRequests(n int, requests []int) int {
	ans := requests[0]
	for i, x := range requests[1:] {
		ans += abs(x - requests[i])
	}
	return ans
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
