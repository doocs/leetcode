func maxSelectedElements(nums []int) int {
	sort.Ints(nums)
	ans, prev := 1, math.MinInt32
	f0, f1 := 1, 1
	for _, x := range nums {
		if x == prev {
			f1 = f0 + 1
		} else if x == prev+1 {
			f0++
			f1++
		} else if x == prev+2 {
			f0 = f1 + 1
			f1 = 1
		} else {
			f0, f1 = 1, 1
		}
		ans = max(ans, f0, f1)
		prev = x
	}
	return ans
}
