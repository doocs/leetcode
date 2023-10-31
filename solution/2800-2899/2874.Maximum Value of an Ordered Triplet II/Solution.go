func maximumTripletValue(nums []int) int64 {
	ans, mx, mx_diff := 0, 0, 0
	for _, num := range nums {
		ans = max(ans, mx_diff*num)
		mx = max(mx, num)
		mx_diff = max(mx_diff, mx-num)
	}
	return int64(ans)
}