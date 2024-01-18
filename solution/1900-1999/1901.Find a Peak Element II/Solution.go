func findPeakGrid(mat [][]int) []int {
	maxPos := func(arr []int) int {
		j := 0
		for i := 1; i < len(arr); i++ {
			if arr[i] > arr[j] {
				j = i
			}
		}
		return j
	}
	l, r := 0, len(mat)-1
	for l < r {
		mid := (l + r) >> 1
		j := maxPos(mat[mid])
		if mat[mid][j] > mat[mid+1][j] {
			r = mid
		} else {
			l = mid + 1
		}
	}
	return []int{l, maxPos(mat[l])}
}