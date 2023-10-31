func maxBoxesInWarehouse(boxes []int, warehouse []int) int {
	n := len(warehouse)
	left := make([]int, n)
	left[0] = warehouse[0]
	for i := 1; i < n; i++ {
		left[i] = min(left[i-1], warehouse[i])
	}
	sort.Ints(boxes)
	i, j := 0, n-1
	for i < len(boxes) {
		for j >= 0 && left[j] < boxes[i] {
			j--
		}
		if j < 0 {
			break
		}
		i, j = i+1, j-1
	}
	return i
}