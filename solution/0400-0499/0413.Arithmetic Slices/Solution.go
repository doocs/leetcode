func numberOfArithmeticSlices(nums []int) (ans int) {
	cnt, d := 0, 3000
	for i, b := range nums[1:] {
		a := nums[i]
		if b-a == d {
			cnt++
		} else {
			d = b - a
			cnt = 0
		}
		ans += cnt
	}
	return
}