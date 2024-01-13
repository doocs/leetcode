func findKDistantIndices(nums []int, key int, k int) (ans []int) {
	for i := range nums {
		for j, x := range nums {
			if abs(i-j) <= k && x == key {
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