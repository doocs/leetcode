func corpFlightBookings(bookings [][]int, n int) []int {
	ans := make([]int, n)
	for _, e := range bookings {
		first, last, seats := e[0], e[1], e[2]
		ans[first-1] += seats
		if last < n {
			ans[last] -= seats
		}
	}
	for i := 1; i < n; i++ {
		ans[i] += ans[i-1]
	}
	return ans
}