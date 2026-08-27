var primes [100001][]int

func init() {
	for i := 2; i < 100001; i++ {
		if len(primes[i]) == 0 {
			for j := i; j < 100001; j += i {
				primes[j] = append(primes[j], i)
			}
		}
	}
}

func longestSubarray(nums []int, k int) int {
	cnt := map[int]int{}

	ans := 0
	l := 0

	for r, x := range nums {

		for _, p := range primes[x] {
			cnt[p]++
		}

		for len(cnt) > k {
			for _, p := range primes[nums[l]] {
				cnt[p]--
				if cnt[p] == 0 {
					delete(cnt, p)
				}
			}
			l++
		}

		ans = max(ans, r-l+1)
	}

	return ans
}
