func minOperations(nums []int, k int) int {
	for i, v := range nums {
		nums[i] = v % k
	}

	ans := int(^uint(0) >> 1)

	for x := 0; x < k; x++ {
		for y := 0; y < k; y++ {
			if x != y {
				cnt := 0

				for i, v := range nums {
					target := x
					if i&1 == 1 {
						target = y
					}

					diff := abs(target - v)
					cnt += min(diff, k-diff)
				}

				ans = min(ans, cnt)
			}
		}
	}

	return ans
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}