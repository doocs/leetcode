func lastVisitedIntegers(words []string) (ans []int) {
	nums := []int{}
	k := 0
	for _, w := range words {
		if w == "prev" {
			k++
			i := len(nums) - k
			if i < 0 {
				ans = append(ans, -1)
			} else {
				ans = append(ans, nums[i])
			}
		} else {
			k = 0
			x, _ := strconv.Atoi(w)
			nums = append(nums, x)
		}
	}
	return
}