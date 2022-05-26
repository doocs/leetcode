func threeSum(nums []int) [][]int {
	n := len(nums)
	ans := make([][]int, 0)
	sort.Ints(nums)
	for i := 0; i < n-2 && nums[i] <= 0; i++ {
		left, right := i+1, n-1
		for left < right {
			cur := nums[i] + nums[left] + nums[right]
			if cur < 0 {
				left++
			} else if cur > 0 {
				right--
			} else {
				ans = append(ans, []int{nums[i], nums[left], nums[right]})
				for left < right && nums[left] == nums[left+1] {
					left++
				}
				for left < right && nums[right] == nums[right-1] {
					right--
				}
				left++
				right--
			}
		}
		for i < n-2 && nums[i] == nums[i+1] {
			i++
		}
	}
	return ans
}