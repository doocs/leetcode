func fourSum(nums []int, target int) [][]int {
	n, res := len(nums), make([][]int, 0)
	if n < 4 {
		return res
	}
	sort.Ints(nums)
	for i := 0; i < n-3; i++ {
		if i > 0 && nums[i] == nums[i-1] {
			continue
		}
		for j := i + 1; j < n-2; j++ {
			if j > i+1 && nums[j] == nums[j-1] {
				continue
			}
			k, l := j+1, n-1
			for k < l {
				if nums[i]+nums[j]+nums[k]+nums[l] == target {
					res = append(res, []int{nums[i], nums[j], nums[k], nums[l]})
					k++
					l--
					for k < n && nums[k] == nums[k-1] {
						k++
					}
					for l > j && nums[l] == nums[l+1] {
						l--
					}
				} else if nums[i]+nums[j]+nums[k]+nums[l] < target {
					k++
				} else {
					l--
				}
			}
		}
	}
	return res
}