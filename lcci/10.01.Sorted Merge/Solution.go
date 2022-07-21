func merge(A []int, m int, B []int, n int) {
	i, j := m-1, n-1
	for k := len(A) - 1; k >= 0; k-- {
		if j < 0 || (i >= 0 && A[i] >= B[j]) {
			A[k] = A[i]
			i--
		} else {
			A[k] = B[j]
			j--
		}
	}
}