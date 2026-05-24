func minimumSwaps(nums []int) int {
	ans := 0
	n := len(nums)

	for i, j := 0, n-1; i < j; i, j = i+1, j-1 {
		for i < n && nums[i] != 0 {
			i++
		}

		for j > 0 && nums[j] == 0 {
			j--
		}

		if i >= j {
			break
		}

		ans++
	}

	return ans
}