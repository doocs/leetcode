func minimumK(nums []int) int {
	check := func(k int) bool {
		t := 0
		for _, x := range nums {
			t += (x + k - 1) / k
		}
		return t <= k*k
	}

	return sort.Search(100000, func(k int) bool {
		if k == 0 {
			return false
		}
		return check(k)
	})
}
