func maxGCDScore(nums []int, k int) int64 {
	n := len(nums)
	cnt := make([]int, n)
	for i, x := range nums {
		for x%2 == 0 {
			cnt[i]++
			x /= 2
		}
	}

	ans := 0
	for l := 0; l < n; l++ {
		g := 0
		mi := math.MaxInt32
		t := 0
		for r := l; r < n; r++ {
			g = gcd(g, nums[r])
			if cnt[r] < mi {
				mi = cnt[r]
				t = 1
			} else if cnt[r] == mi {
				t++
			}
			length := r - l + 1
			score := g * length
			if t <= k {
				score *= 2
			}
			ans = max(ans, score)
		}
	}

	return int64(ans)
}

func gcd(a, b int) int {
	for b != 0 {
		a, b = b, a%b
	}
	return a
}