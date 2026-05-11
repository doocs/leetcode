func countOppositeParity(nums []int) []int {
	n := len(nums)
	ans := make([]int, n)
	cnt := [2]int{}
	for i := n - 1; i >= 0; i-- {
		x := nums[i] & 1  // x 的奇偶性
		ans[i] = cnt[x^1] // 查询右侧奇偶性不等于 x（即 x^1）的元素个数 
		cnt[x]++
	}
	return ans
}