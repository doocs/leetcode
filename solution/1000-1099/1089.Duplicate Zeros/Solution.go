func duplicateZeros(arr []int) {
	n := len(arr)
	i, k := -1, 0
	for k < n {
		i, k = i+1, k+1
		if arr[i] == 0 {
			k++
		}
	}
	j := n - 1
	if k == n+1 {
		arr[j] = 0
		i, j = i-1, j-1
	}
	for j >= 0 {
		arr[j] = arr[i]
		if arr[i] == 0 {
			j--
			arr[j] = arr[i]
		}
		i, j = i-1, j-1
	}
}