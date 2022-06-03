func findKDistantIndices(nums []int, key int, k int) []int {
	n := len(nums)
	var ans []int
	for i := 0; i < n; i++ {
		for j, v := range nums {
			if abs(i-j) <= k && v == key {
				ans = append(ans, i)
				break
			}
		}
	}
	return ans
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}