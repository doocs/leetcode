func numGoodSubarrays(nums []int, k int) (ans int64) {
    s := 0
    cnt := map[int]int{0: 1}
    for _, x := range nums {
        s = (s + x) % k
        ans += int64(cnt[s])
        cnt[s]++
    }

    n := len(nums)
    for i := 0; i < n; {
        j := i + 1
        for j < n && nums[j] == nums[i] {
            j++
        }
        m := j - i
        for h := 1; h <= m; h++ {
            if int64(nums[i])*int64(h)%int64(k) == 0 {
                ans -= int64(m - h)
            }
        }
        i = j
    }
    return
}
