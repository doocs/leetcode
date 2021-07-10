func getMaxLen(nums []int) int {
	f1, f2 := 0, 0
	if nums[0] > 0 {
		f1 = 1
	}
	if nums[0] < 0 {
		f2 = 1
	}
	res := f1
	for i := 1; i < len(nums); i++ {
		if nums[i] > 0 {
			f1++
			if f2 > 0 {
				f2++
			} else {
				f2 = 0
			}
		} else if nums[i] < 0 {
			pf1, pf2 := f1, f2
			f2 = pf1 + 1
			if pf2 > 0 {
				f1 = pf2 + 1
			} else {
				f1 = 0
			}
		} else {
			f1, f2 = 0, 0
		}
		res = max(res, f1)
	}
	return res
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}