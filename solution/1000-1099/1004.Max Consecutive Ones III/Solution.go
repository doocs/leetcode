func longestOnes(nums []int, k int) int {
	l, cnt := 0, 0
	for _, x := range nums {
		cnt += x ^ 1
		if cnt > k {
			cnt -= nums[l] ^ 1
			l++
		}
	}
	return len(nums) - l
}