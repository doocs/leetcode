func beautifulSubsets(nums []int, k int) int {
	ans := -1
	n := len(nums)
	cnt := [1010]int{}
	var dfs func(int)
	dfs = func(i int) {
		if i >= n {
			ans++
			return
		}
		dfs(i + 1)
		ok1 := nums[i]+k >= len(cnt) || cnt[nums[i]+k] == 0
		ok2 := nums[i]-k < 0 || cnt[nums[i]-k] == 0
		if ok1 && ok2 {
			cnt[nums[i]]++
			dfs(i + 1)
			cnt[nums[i]]--
		}
	}
	dfs(0)
	return ans
}