func maxGoodNumber(nums []int) int {
	f := func(i, j, k int) int {
		a := strconv.FormatInt(int64(nums[i]), 2)
		b := strconv.FormatInt(int64(nums[j]), 2)
		c := strconv.FormatInt(int64(nums[k]), 2)
		res, _ := strconv.ParseInt(a+b+c, 2, 64)
		return int(res)
	}
	ans := f(0, 1, 2)
	ans = max(ans, f(0, 2, 1))
	ans = max(ans, f(1, 0, 2))
	ans = max(ans, f(1, 2, 0))
	ans = max(ans, f(2, 0, 1))
	ans = max(ans, f(2, 1, 0))
	return ans
}
