func alternatingSum(nums []int) (ans int) {
	for i, x := range nums {
		if i%2 == 0 {
			ans += x
		} else {
			ans -= x
		}
	}
	return
}
