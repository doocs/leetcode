func minLength(nums []int, k int) int {
	n := len(nums)
	ans := n + 1
	cnt := map[int]int{}
	l := 0
	var s int64 = 0
	for r := 0; r < n; r++ {
		cnt[nums[r]]++
		if cnt[nums[r]] == 1 {
			s += int64(nums[r])
		}
		for s >= int64(k) {
			if r-l+1 < ans {
				ans = r - l + 1
			}
			if cnt[nums[l]]--; cnt[nums[l]] == 0 {
				s -= int64(nums[l])
			}
			l++
		}
	}
	if ans > n {
		return -1
	}
	return ans
}
