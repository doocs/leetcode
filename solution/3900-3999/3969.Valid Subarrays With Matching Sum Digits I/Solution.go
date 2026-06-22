func countValidSubarrays(nums []int, x int) (ans int) {
    n := len(nums)

	for l := 0; l < n; l++ {
		var s int64
		for r := l; r < n; r++ {
			s += int64(nums[r])
			if s%10 == int64(x) && int(strconv.FormatInt(s, 10)[0]-'0') == x {
				ans++
			}
		}
	}

	return
}