func missingNumber(arr []int) int {
	n := len(arr)
	x := (arr[0] + arr[n-1]) * (n + 1) / 2
	y := 0
	for _, v := range arr {
		y += v
	}
	return x - y
}