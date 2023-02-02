func getLeastNumbers(arr []int, k int) []int {
	n := len(arr)
	if k == n {
		return arr
	}
	var quickSort func(l, r int) []int
	quickSort = func(l, r int) []int {
		i, j := l, r
		for i < j {
			for i < j && arr[j] >= arr[l] {
				j--
			}
			for i < j && arr[i] <= arr[l] {
				i++
			}
			arr[i], arr[j] = arr[j], arr[i]
		}
		arr[i], arr[l] = arr[l], arr[i]
		if k < i {
			return quickSort(l, i-1)
		}
		if k > i {
			return quickSort(i+1, r)
		}
		return arr[:k]
	}
	return quickSort(0, n-1)
}