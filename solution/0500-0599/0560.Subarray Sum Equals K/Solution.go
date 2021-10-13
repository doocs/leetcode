func subarraySum(nums []int, k int) int {
	mp := make(map[int]int)
	mp[0] = 1
	res, s := 0, 0
	for _, num := range nums {
		s += num
		res += mp[s-k]
		mp[s]++
	}
	return res
}