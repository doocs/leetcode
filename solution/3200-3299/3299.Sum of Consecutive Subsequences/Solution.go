func getSum(nums []int) int {
	const mod = 1e9 + 7

	calc := func(nums []int) int64 {
		n := len(nums)
		left := make([]int64, n)
		right := make([]int64, n)
		cnt := make(map[int]int64)

		for i := 1; i < n; i++ {
			cnt[nums[i-1]] += 1 + cnt[nums[i-1]-1]
			left[i] = cnt[nums[i]-1]
		}

		cnt = make(map[int]int64)

		for i := n - 2; i >= 0; i-- {
			cnt[nums[i+1]] += 1 + cnt[nums[i+1]+1]
			right[i] = cnt[nums[i]+1]
		}

		var ans int64
		for i, x := range nums {
			ans = (ans + (left[i]+right[i]+(left[i]*right[i]%mod))*int64(x)%mod) % mod
		}
		return ans
	}

	x := calc(nums)
	for i, j := 0, len(nums)-1; i < j; i, j = i+1, j-1 {
		nums[i], nums[j] = nums[j], nums[i]
	}
	y := calc(nums)
	s := int64(0)
	for _, num := range nums {
		s += int64(num)
	}
	return int((x + y + s) % mod)
}
