func leastMinutes(n int) int {
	ans := 1
	for speed := 1; speed < n; speed <<= 1 {
		ans++
	}
	return ans
}