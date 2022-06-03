func mostFrequent(nums []int, key int) int {
	cnt := make([]int, 1010)
	mx, ans := 0, 0
	for i, v := range nums[:len(nums)-1] {
		if v == key {
			target := nums[i+1]
			cnt[target]++
			if mx < cnt[target] {
				mx = cnt[target]
				ans = nums[i+1]
			}
		}
	}
	return ans
}