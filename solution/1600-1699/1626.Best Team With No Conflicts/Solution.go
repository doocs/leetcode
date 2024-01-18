func bestTeamScore(scores []int, ages []int) int {
	n := len(ages)
	arr := make([][2]int, n)
	for i := range ages {
		arr[i] = [2]int{scores[i], ages[i]}
	}
	sort.Slice(arr, func(i, j int) bool {
		a, b := arr[i], arr[j]
		return a[0] < b[0] || a[0] == b[0] && a[1] < b[1]
	})
	f := make([]int, n)
	for i := range arr {
		for j := 0; j < i; j++ {
			if arr[i][1] >= arr[j][1] {
				f[i] = max(f[i], f[j])
			}
		}
		f[i] += arr[i][0]
	}
	return slices.Max(f)
}