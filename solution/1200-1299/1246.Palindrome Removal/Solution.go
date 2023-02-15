func minimumMoves(arr []int) int {
	n := len(arr)
	f := make([][]int, n)
	for i := range f {
		f[i] = make([]int, n)
		f[i][i] = 1
	}
	for i := n - 2; i >= 0; i-- {
		for j := i + 1; j < n; j++ {
			if i+1 == j {
				f[i][j] = 2
				if arr[i] == arr[j] {
					f[i][j] = 1
				}
			} else {
				t := 1 << 30
				if arr[i] == arr[j] {
					t = f[i+1][j-1]
				}
				for k := i; k < j; k++ {
					t = min(t, f[i][k]+f[k+1][j])
				}
				f[i][j] = t
			}
		}
	}
	return f[0][n-1]
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}