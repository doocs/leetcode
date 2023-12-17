func subsetsWithDup(nums []int) (ans [][]int) {
	sort.Ints(nums)
	n := len(nums)
	for mask := 0; mask < 1<<n; mask++ {
		t := []int{}
		ok := true
		for i := 0; i < n; i++ {
			if mask>>i&1 == 1 {
				if i > 0 && mask>>(i-1)&1 == 0 && nums[i] == nums[i-1] {
					ok = false
					break
				}
				t = append(t, nums[i])
			}
		}
		if ok {
			ans = append(ans, t)
		}
	}
	return
}