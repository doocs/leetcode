func countMajoritySubarrays(nums []int, target int) (ans int) {
	n := len(nums)
	for i := range nums {
		cnt := map[int]int{}
		for j := i; j < n; j++ {
			k := j - i + 1
			cnt[nums[j]]++
			if cnt[target] > k/2 {
				ans++
			}
		}
	}
	return
}
