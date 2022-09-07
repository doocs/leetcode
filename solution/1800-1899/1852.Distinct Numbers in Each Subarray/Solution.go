func distinctNumbers(nums []int, k int) []int {
	cnt := map[int]int{}
	for _, v := range nums[:k] {
		cnt[v]++
	}
	ans := []int{len(cnt)}
	for i := k; i < len(nums); i++ {
		u := nums[i-k]
		cnt[u]--
		if cnt[u] == 0 {
			delete(cnt, u)
		}
		cnt[nums[i]]++
		ans = append(ans, len(cnt))
	}
	return ans
}