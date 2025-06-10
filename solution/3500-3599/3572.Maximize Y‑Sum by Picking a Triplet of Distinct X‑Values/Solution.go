func maxSumDistinctTriplet(x []int, y []int) int {
	n := len(x)
	arr := make([][2]int, n)
	for i := 0; i < n; i++ {
		arr[i] = [2]int{x[i], y[i]}
	}
	sort.Slice(arr, func(i, j int) bool {
		return arr[i][1] > arr[j][1]
	})
	ans := 0
	vis := make(map[int]bool)
	for i := 0; i < n; i++ {
		a, b := arr[i][0], arr[i][1]
		if !vis[a] {
			vis[a] = true
			ans += b
			if len(vis) == 3 {
				return ans
			}
		}
	}
	return -1
}
