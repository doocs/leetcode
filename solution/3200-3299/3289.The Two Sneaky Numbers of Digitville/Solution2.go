func getSneakyNumbers(nums []int) []int {
	n := len(nums) - 2
	xx := nums[n] ^ nums[n+1]
	for i := 0; i < n; i++ {
		xx ^= i ^ nums[i]
	}
	k := bits.TrailingZeros(uint(xx))
	ans := make([]int, 2)
	for _, x := range nums {
		ans[(x>>k)&1] ^= x
	}
	for i := 0; i < n; i++ {
		ans[(i>>k)&1] ^= i
	}
	return ans
}
