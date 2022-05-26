func subarraySum(nums []int, k int) int {
	counter := map[int]int{0: 1}
	ans, s := 0, 0
	for _, num := range nums {
		s += num
		ans += counter[s-k]
		counter[s]++
	}
	return ans
}