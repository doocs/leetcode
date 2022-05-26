func subarraysDivByK(nums []int, k int) int {
	counter := map[int]int{0: 1}
	ans, s := 0, 0
	for _, num := range nums {
		s += num
		t := (s%k + k) % k
		ans += counter[t]
		counter[t]++
	}
	return ans
}