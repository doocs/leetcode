func numSubarrayBoundedMax(nums []int, left int, right int) int {
	f := func(x int) (cnt int) {
		t := 0
		for _, v := range nums {
			t++
			if v > x {
				t = 0
			}
			cnt += t
		}
		return
	}
	return f(right) - f(left-1)
}