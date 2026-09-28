func rearrangeArray(nums []int) []int {
	mx := slices.Max(nums)

	cnt := make([]int, mx+1)
	for _, x := range nums {
		cnt[x]++
	}

	ans := make([]int, 0, len(nums))
	for len(ans) < len(nums) {
		for x := 1; x <= mx; x++ {
			if cnt[x] > 0 {
				ans = append(ans, x)
				cnt[x]--
			}
		}
	}
	return ans
}
