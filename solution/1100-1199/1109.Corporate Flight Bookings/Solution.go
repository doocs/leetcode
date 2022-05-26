func corpFlightBookings(bookings [][]int, n int) []int {
	delta := make([]int, n)
	for _, booking := range bookings {
		first, last, seats := booking[0], booking[1], booking[2]
		delta[first-1] += seats
		if last < n {
			delta[last] -= seats
		}
	}
	for i := 0; i < n-1; i++ {
		delta[i+1] += delta[i]
	}
	return delta
}