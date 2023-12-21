func distinctNumbers(nums []int, k int) (ans []int) {
	m := slices.Max(nums)
	cnt := make([]int, m+1)
	v := 0
	for _, x := range nums[:k] {
		cnt[x]++
		if cnt[x] == 1 {
			v++
		}
	}
	ans = append(ans, v)
	for i := k; i < len(nums); i++ {
		cnt[nums[i]]++
		if cnt[nums[i]] == 1 {
			v++
		}
		cnt[nums[i-k]]--
		if cnt[nums[i-k]] == 0 {
			v--
		}
		ans = append(ans, v)
	}
	return
}