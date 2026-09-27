func longestSubarray(nums []int, k int) int {
	f := func(nums []int) int {
		d := map[int]int{0: -1}
		s, res := 0, 0
		for i, x := range nums {
			s = (s + x) % k
			if s < 0 {
				s += k
			}
			if j, ok := d[s]; ok {
				res = max(res, i-j)
			} else {
				d[s] = i
			}
		}
		return res
	}

	ans := f(nums)
	for i, x := range nums {
		nums[i] = -x
		ans = max(ans, f(nums))
		nums[i] = x
	}
	return ans
}
