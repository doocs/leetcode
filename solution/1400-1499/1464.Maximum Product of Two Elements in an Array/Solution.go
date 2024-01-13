func maxProduct(nums []int) int {
	ans := 0
	for i, a := range nums {
		for _, b := range nums[i+1:] {
			t := (a - 1) * (b - 1)
			if ans < t {
				ans = t
			}
		}
	}
	return ans
}