func canFormArray(arr []int, pieces [][]int) bool {
	for i := 0; i < len(arr); {
		k := 0
		for k < len(pieces) && pieces[k][0] != arr[i] {
			k++
		}
		if k == len(pieces) {
			return false
		}
		j := 0
		for j < len(pieces[k]) && arr[i] == pieces[k][j] {
			i, j = i+1, j+1
		}
	}
	return true
}