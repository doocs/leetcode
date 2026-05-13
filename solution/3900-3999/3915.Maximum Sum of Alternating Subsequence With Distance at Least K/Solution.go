type fenwick []int64

func (f fenwick) update(i int, val int64) {
	for ; i < len(f); i += i & -i {
		f[i] = max(f[i], val)
	}
}

// [1, i] 中的最大值
func (f fenwick) preMax(i int) (res int64) {
	for ; i > 0; i &= i - 1 {
		res = max(res, f[i])
	}
	return
}

func maxAlternatingSum(nums []int, k int) (ans int64) {
	// 离散化 nums
	sorted := slices.Clone(nums)
	slices.Sort(sorted)
	sorted = slices.Compact(sorted)

	n := len(nums)
	fInc := make([]int64, n) // fInc[i] 表示以 nums[i] 结尾且最后两项递增的交替子序列的最大和
	fDec := make([]int64, n) // fDec[i] 表示以 nums[i] 结尾且最后两项递减的交替子序列的最大和

	// 值域树状数组
	m := len(sorted)
	inc := make(fenwick, m+1) // 维护 fInc[i] 的最大值
	dec := make(fenwick, m+1) // 维护 fDec[i] 的最大值

	for i, x := range nums {
		if i >= k {
			// 在这个时候才把 fInc[i-k] 和 fDec[i-k] 添加到值域树状数组中，从而保证转移来源的下标 <= i-k
			j := nums[i-k]
			inc.update(m-j, fInc[i-k]) // m-j 可以把后缀变成前缀
			dec.update(j+1, fDec[i-k])
		}

		j := sort.SearchInts(sorted, x)
		nums[i] = j // 注意这里修改了 nums[i]，这样上面的 nums[i-k] 无需二分

		fInc[i] = dec.preMax(j) + int64(x)     // 计算满足 nums[i'] < x 的 fDec[i'] 的最大值
		fDec[i] = inc.preMax(m-1-j) + int64(x) // 计算满足 nums[i'] > x 的 fInc[i'] 的最大值
		ans = max(ans, fInc[i], fDec[i])       // 枚举子序列以 nums[i] 结尾
	}

	return
}
