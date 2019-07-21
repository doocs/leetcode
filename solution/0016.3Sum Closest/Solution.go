func threeSumClosest(nums []int, target int) int {
	sort(nums)
	lenNums := len(nums)
	result := nums[0] + nums[1] + nums[2]
	for i, n := range nums {
		left := i + 1
		right := lenNums - 1
		for left < right {
			r := nums[left] + nums[right] + n
			if abs(r - target) < abs(result - target) {
				result = r
			}
			if r > target {
				right--
			} else if r < target {
				left++
			} else {
				return result
			}
		}
	}
	return result
}

func abs(a int) int {
	if a >= 0 {
		return a
	}
	return -a;
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