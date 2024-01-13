func findClosestElements(arr []int, k int, x int) []int {
	l, r := 0, len(arr)
	for r-l > k {
		if x-arr[l] <= arr[r-1]-x {
			r--
		} else {
			l++
		}
	}
	return arr[l:r]
}