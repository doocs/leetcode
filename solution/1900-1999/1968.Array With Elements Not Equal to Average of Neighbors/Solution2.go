func rearrangeArray(nums []int) []int {
	rand.Seed(time.Now().UnixNano())
outer:
	for {
		rand.Shuffle(len(nums), func(i, j int) { nums[i], nums[j] = nums[j], nums[i] })
		for i := 1; i < len(nums)-1; i++ {
			if nums[i]*2 == nums[i-1]+nums[i+1] {
				continue outer
			}
		}
		return nums
	}
}