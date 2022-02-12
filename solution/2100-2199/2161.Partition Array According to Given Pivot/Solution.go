func pivotArray(nums []int, pivot int) []int {
	var ans []int
	for _, x := range nums {
		if x < pivot {
			ans = append(ans, x)
		}
	}
	for _, x := range nums {
		if x == pivot {
			ans = append(ans, x)
		}
	}
	for _, x := range nums {
		if x > pivot {
			ans = append(ans, x)
		}
	}
	return ans
}