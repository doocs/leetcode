func validSubarrays(nums []int, k int) int64 {
	n := len(nums)
	peaks := []int{}

	for i := 1; i < n-1; i++ {
		if nums[i] > nums[i-1] && nums[i] > nums[i+1] {
			peaks = append(peaks, i)
		}
	}

	var ans int64
	for j, p := range peaks {
		leftMin := max(p-k, 0)
		if j > 0 {
			leftMin = max(leftMin, peaks[j-1]+1)
		}

		rightMax := min(p+k, n-1)
		if j < len(peaks)-1 {
			rightMax = min(rightMax, peaks[j+1]-1)
		}

		ans += int64(p-leftMin+1) * int64(rightMax-p+1)
	}

	return ans
}
