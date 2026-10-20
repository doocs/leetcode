func sortableIntegers(nums []int) int {
	n := len(nums)
	prefMax := make([]int, n+1)
	for i, x := range nums {
		prefMax[i+1] = max(prefMax[i], x)
	}
	suffMin := make([]int, n+1)
	suffMin[n] = 1000000000
	for i := n - 1; i >= 0; i-- {
		suffMin[i] = min(nums[i], suffMin[i+1])
	}
	prefDrop := make([]int, n)
	for i := 1; i < n; i++ {
		prefDrop[i] = prefDrop[i-1]
		if nums[i-1] > nums[i] {
			prefDrop[i]++
		}
	}
	check := func(k int) bool {
		for start := 0; start < n; start += k {
			end := start + k - 1
			drops := prefDrop[end] - prefDrop[start]
			if nums[end] > nums[start] {
				drops++
			}
			if drops > 1 || prefMax[start] > suffMin[start] {
				return false
			}
		}
		return true
	}
	ans := 0
	for i := 1; i <= n/i; i++ {
		if n%i == 0 {
			if check(i) {
				ans += i
			}
			if i != n/i && check(n/i) {
				ans += n / i
			}
		}
	}
	return ans
}
