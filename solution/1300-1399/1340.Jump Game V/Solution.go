func maxJumps(arr []int, d int) (ans int) {
	n := len(arr)
	idx := make([]int, n)
	f := make([]int, n)
	for i := range f {
		idx[i] = i
		f[i] = 1
	}
	sort.Slice(idx, func(i, j int) bool { return arr[idx[i]] < arr[idx[j]] })
	for _, i := range idx {
		for j := i - 1; j >= 0; j-- {
			if i-j > d || arr[j] >= arr[i] {
				break
			}
			f[i] = max(f[i], 1+f[j])
		}
		for j := i + 1; j < n; j++ {
			if j-i > d || arr[j] >= arr[i] {
				break
			}
			f[i] = max(f[i], 1+f[j])
		}
		ans = max(ans, f[i])
	}
	return
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}