func findDuplicate(nums []int) int {
	left, right := 1, len(nums)-1
	for left < right {
		mid := (left + right) >> 1
		cnt := 0
		for _, v := range nums {
			if v <= mid {
				cnt++
			}
		}
		if cnt > mid {
			right = mid
		} else {
			left = mid + 1
		}
	}
	return left
}