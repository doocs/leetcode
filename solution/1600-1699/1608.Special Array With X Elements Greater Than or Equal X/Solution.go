func specialArray(nums []int) int {
	n := len(nums)
	sort.Ints(nums)
	for x := 0; x <= n; x++ {
		left, right := 0, n
		for left < right {
			mid := (left + right) >> 1
			if nums[mid] >= x {
				right = mid
			} else {
				left = mid + 1
			}
		}
		cnt := n - 1 - left + 1
		if cnt == x {
			return x
		}
	}
	return -1
}