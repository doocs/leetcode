func canReach(arr []int, start int) bool {
	q := []int{start}
	for len(q) > 0 {
		i := q[0]
		q = q[1:]
		if arr[i] == 0 {
			return true
		}
		x := arr[i]
		arr[i] = -1
		for _, j := range []int{i + x, i - x} {
			if j >= 0 && j < len(arr) && arr[j] >= 0 {
				q = append(q, j)
			}
		}
	}
	return false
}