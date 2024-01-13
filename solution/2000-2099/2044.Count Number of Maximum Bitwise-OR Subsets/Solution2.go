func countMaxOrSubsets(nums []int) int {
	n := len(nums)
	ans := 0
	mx := 0
	for mask := 1; mask < 1<<n; mask++ {
		t := 0
		for i, v := range nums {
			if ((mask >> i) & 1) == 1 {
				t |= v
			}
		}
		if mx < t {
			mx = t
			ans = 1
		} else if mx == t {
			ans++
		}
	}
	return ans
}