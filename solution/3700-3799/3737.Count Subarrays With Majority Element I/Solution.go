func countMajoritySubarrays(nums []int, target int) (ans int) {
	n := len(nums)
	for i := range nums {
		cnt := 0
		for j := i; j < n; j++ {
			if nums[j] == target {
				cnt++
			}
			if k := j - i + 1; cnt*2 > k {
				ans++
			}
		}
	}
	return
}
