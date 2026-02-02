func alternatingXOR(nums []int, target1 int, target2 int) int {
	mod := 1_000_000_007
	cnt1 := make(map[int]int)
	cnt2 := make(map[int]int)
	cnt2[0] = 1

	pre := 0
	ans := 0

	for _, x := range nums {
		pre ^= x
		a := cnt2[pre^target1]
		b := cnt1[pre^target2]
		ans = (a + b) % mod
		cnt1[pre] = (cnt1[pre] + a) % mod
		cnt2[pre] = (cnt2[pre] + b) % mod
	}

	return ans
}
