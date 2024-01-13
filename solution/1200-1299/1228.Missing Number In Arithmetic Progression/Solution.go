func missingNumber(arr []int) int {
	n := len(arr)
	d := (arr[n-1] - arr[0]) / n
	for i := 1; i < n; i++ {
		if arr[i] != arr[i-1]+d {
			return arr[i-1] + d
		}
	}
	return arr[0]
}