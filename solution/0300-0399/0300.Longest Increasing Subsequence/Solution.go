func lengthOfLIS(nums []int) int {
	d := make([]int, len(nums)+1)
	d[1] = nums[0]
	size := 1
	for _, x := range nums[1:] {
		if x > d[size] {
			size++
			d[size] = x
		} else {
			left, right := 1, size
			for left < right {
				mid := (left + right) >> 1
				if d[mid] >= x {
					right = mid
				} else {
					left = mid + 1
				}
			}
			if d[left] < x {
				left = 1
			}
			d[left] = x
		}
	}
	return size
}