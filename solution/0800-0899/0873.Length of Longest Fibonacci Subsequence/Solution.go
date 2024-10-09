func lenLongestFibSubseq(arr []int) (ans int) {
	n := len(arr)
	f := make([][]int, n)
	for i := range f {
		f[i] = make([]int, n)
	}

	d := make(map[int]int)
	for i, x := range arr {
		d[x] = i
		for j := 0; j < i; j++ {
			f[i][j] = 2
		}
	}

	for i := 2; i < n; i++ {
		for j := 1; j < i; j++ {
			t := arr[i] - arr[j]
			if k, ok := d[t]; ok && k < j {
				f[i][j] = max(f[i][j], f[j][k]+1)
				ans = max(ans, f[i][j])
			}
		}
	}

	return
}