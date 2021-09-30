func triangleNumber(nums []int) int {
	n := len(nums)
	sort.Ints(nums)
	ans := 0
	for i := 0; i < n-2; i++ {
		for j := i + 1; j < n-1; j++ {
			left, right := j+1, n
			for left < right {
				mid := int(uint(left+right) >> 1)
				if nums[mid] < nums[i]+nums[j] {
					left = mid + 1
				} else {
					right = mid
				}
			}
			ans += left - j - 1
		}
	}
	return ans
}
