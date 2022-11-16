func fourSum(nums []int, target int) [][]int {
	ans, n := [][]int{}, len(nums)
	sort.Ints(nums)
	for i := 0; i < n; i++ {
		for j := i + 1; j < n; j++ {
			for l, r := j+1, n-1; l < r; {
				if nums[i]+nums[j]+nums[l]+nums[r] == target {
					ans = append(ans, []int{nums[i], nums[j], nums[l], nums[r]})
					l, r = l+1, r-1
					for l < r && nums[l] == nums[l-1] {
						l++
					}
					for l < r && nums[r] == nums[r+1] {
						r--
					}
				} else if nums[i]+nums[j]+nums[l]+nums[r] < target {
					l++
				} else {
					r--
				}
			}
			for j+1 < n && nums[j+1] == nums[j] {
				j++
			}
		}
		for i+1 < n && nums[i+1] == nums[i] {
			i++
		}
	}
	return ans
}
