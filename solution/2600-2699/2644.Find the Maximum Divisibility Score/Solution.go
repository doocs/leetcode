func maxDivScore(nums []int, divisors []int) int {
	ans, mx := divisors[0], 0
	for _, div := range divisors {
		cnt := 0
		for _, x := range nums {
			if x%div == 0 {
				cnt++
			}
		}
		if mx < cnt {
			ans, mx = div, cnt
		} else if mx == cnt && ans > div {
			ans = div
		}
	}
	return ans
}
