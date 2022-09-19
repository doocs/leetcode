func frequencySort(nums []int) []int {
	cnt := make([]int, 201)
	for _, v := range nums {
		cnt[v+100]++
	}
	sort.Slice(nums, func(i, j int) bool {
		a, b := nums[i]+100, nums[j]+100
		return cnt[a] < cnt[b] || cnt[a] == cnt[b] && a > b
	})
	return nums
}