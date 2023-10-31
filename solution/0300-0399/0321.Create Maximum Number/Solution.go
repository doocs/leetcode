func maxNumber(nums1 []int, nums2 []int, k int) []int {
	m, n := len(nums1), len(nums2)
	l, r := max(0, k-n), min(k, m)
	f := func(nums []int, k int) []int {
		n := len(nums)
		stk := make([]int, k)
		top := -1
		remain := n - k
		for _, x := range nums {
			for top >= 0 && stk[top] < x && remain > 0 {
				top--
				remain--
			}
			if top+1 < k {
				top++
				stk[top] = x
			} else {
				remain--
			}
		}
		return stk
	}

	var compare func(nums1, nums2 []int, i, j int) bool
	compare = func(nums1, nums2 []int, i, j int) bool {
		if i >= len(nums1) {
			return false
		}
		if j >= len(nums2) {
			return true
		}
		if nums1[i] > nums2[j] {
			return true
		}
		if nums1[i] < nums2[j] {
			return false
		}
		return compare(nums1, nums2, i+1, j+1)
	}

	merge := func(nums1, nums2 []int) []int {
		m, n := len(nums1), len(nums2)
		ans := make([]int, m+n)
		i, j := 0, 0
		for k := range ans {
			if compare(nums1, nums2, i, j) {
				ans[k] = nums1[i]
				i++
			} else {
				ans[k] = nums2[j]
				j++
			}
		}
		return ans
	}

	ans := make([]int, k)
	for x := l; x <= r; x++ {
		arr1 := f(nums1, x)
		arr2 := f(nums2, k-x)
		arr := merge(arr1, arr2)
		if compare(arr, ans, 0, 0) {
			ans = arr
		}
	}
	return ans
}