func maximumTripletValue(nums []int) int64 {
	ans, mx, mxDiff := 0, 0, 0
	for _, x := range nums {
		ans = max(ans, mxDiff*x)
		mxDiff = max(mxDiff, mx-x)
		mx = max(mx, x)
	}
	return int64(ans)
}
