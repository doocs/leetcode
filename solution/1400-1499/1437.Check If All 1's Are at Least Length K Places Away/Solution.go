func kLengthApart(nums []int, k int) bool {
	j := -1
	for i, v := range nums {
		if v == 1 {
			if j != -1 && i-j-1 < k {
				return false
			}
			j = i
		}
	}
	return true
}