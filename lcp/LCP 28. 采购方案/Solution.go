func purchasePlans(nums []int, target int) int {
	sort.Ints(nums)
	res, mod := 0, 1000000007
	for i, j := 0, len(nums)-1; i < j; i++ {
		for i < j && nums[i]+nums[j] > target {
			j--
		}
		if i < j {
			res = (res + j - i) % mod
		}
	}
	return res
}