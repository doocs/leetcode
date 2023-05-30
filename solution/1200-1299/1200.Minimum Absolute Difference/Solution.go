func minimumAbsDifference(arr []int) (ans [][]int) {
	sort.Ints(arr)
	mi := 1 << 30
	n := len(arr)
	for i := 0; i < n-1; i++ {
		if t := arr[i+1] - arr[i]; t < mi {
			mi = t
		}
	}
	for i := 0; i < n-1; i++ {
		if arr[i+1]-arr[i] == mi {
			ans = append(ans, []int{arr[i], arr[i+1]})
		}
	}
	return
}