func threeSum(nums []int) [][]int {
	sort(nums)
	result := make([][]int, 0)
	for i, n := range nums {
		if n > 0 {
			break
		}
		if i > 0 && nums[i] == nums[i-1] {
			continue
		}
		left := i + 1
		right := len(nums) - 1
		for left < right {
			r := nums[left] + nums[right] + n
			if r == 0 {
				result = append(result, []int{nums[left], nums[right], n})
				left++
				for left < right && nums[left] == nums[left-1] {
					left++
				}
				right--
				for left < right && nums[right] == nums[right+1] {
					right--
				}
			} else if r < 0 {
				left++
			} else if r > 0 {
				right--
			}
		}
	}
	return result
}

// quick sort
func sort(array []int) {
	if len(array) == 0 {
		return
	}
	left := 0
	right := len(array) - 1
	obj := array[left]
	for left < right {
		for left < right && array[right] >= obj {
			right--
		}
		array[left] = array[right]

		for left < right && array[left] <= obj {
			left++
		}
		array[right] = array[left]
	}
	array[left] = obj
	sort(array[:left])
	sort(array[right+1:])
}