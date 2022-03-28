func kWeakestRows(mat [][]int, k int) []int {
	m, n := len(mat), len(mat[0])
	res := make([]int, m)
	var idx []int
	for i, row := range mat {
		idx = append(idx, i)
		left, right := 0, n
		for left < right {
			mid := (left + right) >> 1
			if row[mid] == 0 {
				right = mid
			} else {
				left = mid + 1
			}
		}
		res[i] = left
	}
	sort.Slice(idx, func(i, j int) bool {
		return res[idx[i]] < res[idx[j]] || (res[idx[i]] == res[idx[j]] && idx[i] < idx[j])
	})
	return idx[:k]
}