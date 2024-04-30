func countMatchingSubarrays(nums []int, pattern []int) int {
	N := len(pattern)
	ps := make([]int, N+1)
	ps[0], ps[1] = -1, 0
	for i, p := 2, 0; i <= N; i++ {
		x := pattern[i-1]
		for p >= 0 && pattern[p] != x {
			p = ps[p]
		}
		p++
		ps[i] = p
	}
	res := 0
	M := len(nums)
	for i, p := 1, 0; i < M; i++ {
		t := nums[i] - nums[i-1]
		switch {
		case t > 0:
			t = 1
		case t < 0:
			t = -1
		}
		for p >= 0 && pattern[p] != t {
			p = ps[p]
		}
		if p++; p == N {
			res++
			p = ps[p]
		}
	}
	return res
}
