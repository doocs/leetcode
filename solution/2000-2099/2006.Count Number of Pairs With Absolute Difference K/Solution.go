func countKDifference(nums []int, k int) int {
	ans := 0
	counter := make([]int, 110)
	for _, num := range nums {
		if num >= k {
			ans += counter[num-k]
		}
		if num+k <= 100 {
			ans += counter[num+k]
		}
		counter[num]++
	}
	return ans
}