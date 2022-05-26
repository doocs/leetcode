func searchRange(nums []int, target int) []int {
	search := func(target int) int {
		left, right := 0, len(nums)
		for left < right {
			mid := (left + right) >> 1
			if nums[mid] >= target {
				right = mid
			} else {
				left = mid + 1
			}
		}
		return left
	}
	l, r := search(target), search(target+1)
	if l == len(nums) || l >= r {
		return []int{-1, -1}
	}
	return []int{l, r - 1}
}