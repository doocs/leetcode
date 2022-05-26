func subarraySum(nums []int, k int) int {
	m := map[int]int{0: 1}
	sum, ans := 0, 0
	for _, num := range nums {
		sum += num
		ans += m[sum-k]
		m[sum]++
	}
	return ans
}