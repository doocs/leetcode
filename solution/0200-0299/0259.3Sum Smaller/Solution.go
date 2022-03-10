func threeSumSmaller(nums []int, target int) int {
	sort.Ints(nums)
	ans := 0
	for i, n := 0, len(nums); i < n; i++ {
		j, k := i+1, n-1
		for j < k {
			s := nums[i] + nums[j] + nums[k]
			if s >= target {
				k--
			} else {
				ans += k - j
				j++
			}
		}
	}
	return ans
}