func recoverArray(nums []int) []int {
	sort.Ints(nums)
	for i, n := 1, len(nums); i < n; i++ {
		d := nums[i] - nums[0]
		if d == 0 || d%2 == 1 {
			continue
		}
		vis := make([]bool, n)
		vis[i] = true
		ans := []int{(nums[0] + nums[i]) >> 1}
		for l, r := 1, i+1; r < n; l, r = l+1, r+1 {
			for l < n && vis[l] {
				l++
			}
			for r < n && nums[r]-nums[l] < d {
				r++
			}
			if r == n || nums[r]-nums[l] > d {
				break
			}
			vis[r] = true
			ans = append(ans, (nums[l]+nums[r])>>1)
		}
		if len(ans) == (n >> 1) {
			return ans
		}
	}
	return []int{}
}