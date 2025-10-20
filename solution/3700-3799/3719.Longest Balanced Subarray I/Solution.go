func longestBalanced(nums []int) (ans int) {
	n := len(nums)
	for i := 0; i < n; i++ {
		vis := map[int]bool{}
		cnt := [2]int{}
		for j := i; j < n; j++ {
			if !vis[nums[j]] {
				vis[nums[j]] = true
				cnt[nums[j]&1]++
			}
			if cnt[0] == cnt[1] {
				ans = max(ans, j-i+1)
			}
		}
	}
	return
}
