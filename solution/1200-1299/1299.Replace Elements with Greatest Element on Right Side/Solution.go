func replaceElements(arr []int) []int {
	for i, mx := len(arr)-1, -1; i >= 0; i-- {
		x := arr[i]
		arr[i] = mx
		mx = max(mx, x)
	}
	return arr
}
