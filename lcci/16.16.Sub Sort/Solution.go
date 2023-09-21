func subSort(array []int) []int {
	n := len(array)
	mi, mx := math.MaxInt32, math.MinInt32
	left, right := -1, -1
	for i, x := range array {
		if x < mx {
			right = i
		} else {
			mx = x
		}
	}
	for i := n - 1; i >= 0; i-- {
		if array[i] > mi {
			left = i
		} else {
			mi = array[i]
		}
	}
	return []int{left, right}
}