func mySqrt(x int) int {
	if x == 0 {
		return 0
	}
	low, high := 1, x
	for low < high {
		mid := low + (high-low+1)>>1
		if x/mid >= mid {
			low = mid
		} else {
			high = mid - 1
		}
	}
	return low
}