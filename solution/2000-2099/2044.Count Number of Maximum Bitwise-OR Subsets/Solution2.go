func countMaxOrSubsets(nums []int) (ans int) {
	n := len(nums)
	mx := 0

	for mask := 0; mask < (1 << n); mask++ {
		t := 0
		for i, v := range nums {
			if (mask>>i)&1 == 1 {
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

	return
}
