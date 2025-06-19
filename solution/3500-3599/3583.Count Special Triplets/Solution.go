func specialTriplets(nums []int) int {
	left := make(map[int]int)
	right := make(map[int]int)
	for _, x := range nums {
		right[x]++
	}
	ans := int64(0)
	mod := int64(1e9 + 7)
	for _, x := range nums {
		right[x]--
		ans = (ans + int64(left[x*2])*int64(right[x*2])%mod) % mod
		left[x]++
	}
	return int(ans)
}
