func tupleSameProduct(nums []int) int {
	product, n := make(map[int]int), len(nums)
	for i := 1; i < n; i++ {
		for j := 0; j < i; j++ {
			multiplier := nums[i] * nums[j]
			if _, ok := product[multiplier]; !ok {
				product[multiplier] = 0
			}
			product[multiplier] += 1
		}
	}
	ans := 0
	for _, v := range product {
		ans += v * (v - 1) / 2
	}
	return ans << 3
}