func countSubarrays(nums []int, k int) int {
	n := len(nums)
	var i int
	for j, v := range nums {
		if v == k {
			i = j
			break
		}
	}
	ans := 1
	d := make([]int, n<<1|1)
	mi, mx := 0, 0
	for j := i + 1; j < n; j++ {
		if nums[j] < k {
			mi++
		} else {
			mx++
		}
		if mx-mi >= 0 && mx-mi <= 1 {
			ans++
		}
		d[mx-mi+n]++
	}
	mi, mx = 0, 0
	for j := i - 1; j >= 0; j-- {
		if nums[j] < k {
			mi++
		} else {
			mx++
		}
		if mx-mi >= 0 && mx-mi <= 1 {
			ans++
		}
		ans += d[mi-mx+n] + d[mi-mx+n+1]
	}
	return ans
}