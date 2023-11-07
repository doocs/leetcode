func maxSum(nums []int, k int) (ans int) {
	cnt := [31]int{}
	for _, x := range nums {
		for i := 0; i < 31; i++ {
			if x>>i&1 == 1 {
				cnt[i]++
			}
		}
	}
	const mod int = 1e9 + 7
	for ; k > 0; k-- {
		x := 0
		for i := 0; i < 31; i++ {
			if cnt[i] > 0 {
				x |= 1 << i
				cnt[i]--
			}
		}
		ans = (ans + x*x) % mod
	}
	return
}