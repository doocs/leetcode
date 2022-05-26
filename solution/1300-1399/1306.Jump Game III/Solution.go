func canReach(arr []int, start int) bool {
	q := []int{start}
	for len(q) > 0 {
		i := q[0]
		if arr[i] == 0 {
			return true
		}
		q = q[1:]
		for _, j := range []int{i + arr[i], i - arr[i]} {
			if j >= 0 && j < len(arr) && arr[j] >= 0 {
				q = append(q, j)
			}
		}
		arr[i] = -1
	}
	return false
}