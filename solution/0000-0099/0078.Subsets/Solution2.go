func subsets(nums []int) (ans [][]int) {
	n := len(nums)
	for mask := 0; mask < 1<<n; mask++ {
		t := []int{}
		for i, x := range nums {
			if mask>>i&1 == 1 {
				t = append(t, x)
			}
		}
		ans = append(ans, t)
	}
	return
}