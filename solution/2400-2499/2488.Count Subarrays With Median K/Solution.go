func countSubarrays(nums []int, k int) int {
	i, n := 0, len(nums)
	for nums[i] != k {
		i++
	}
	ans := 1
	cnt := make([]int, n<<1|1)
	x := 0
	for j := i + 1; j < n; j++ {
		if nums[j] > k {
			x++
		} else {
			x--
		}
		if x >= 0 && x <= 1 {
			ans++
		}
		cnt[x+n]++
	}
	x = 0
	for j := i - 1; j >= 0; j-- {
		if nums[j] > k {
			x++
		} else {
			x--
		}
		if x >= 0 && x <= 1 {
			ans++
		}
		ans += cnt[-x+n] + cnt[-x+1+n]
	}
	return ans
}