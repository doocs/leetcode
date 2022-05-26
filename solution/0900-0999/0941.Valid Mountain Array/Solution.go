func validMountainArray(arr []int) bool {
	n := len(arr)
	if n < 3 {
		return false
	}
	l, r := 0, n-1
	for l+1 < n-1 && arr[l] < arr[l+1] {
		l++
	}
	for r-1 > 0 && arr[r] < arr[r-1] {
		r--
	}
	return l == r
}