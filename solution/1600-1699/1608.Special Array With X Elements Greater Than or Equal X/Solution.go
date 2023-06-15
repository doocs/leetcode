func specialArray(nums []int) int {
	sort.Ints(nums)
	n := len(nums)
	for x := 1; x <= n; x++ {
		left, right := 0, n
		for left < right {
			mid := (left + right) >> 1
			if nums[mid] >= x {
				right = mid
			} else {
				left = mid + 1
			}
		}
		cnt := n - left
		if cnt == x {
			return x
		}
	}
	return -1
}