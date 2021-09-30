func leastMinutes(n int) int {
	speed, res := 1, 1
	for speed < n {
		speed <<= 1
		res++
	}
	return res
}