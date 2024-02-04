func maximumSubarraySum(nums []int, k int) int64 {
    p := make(map[int]int64)
    var r int64 = math.MinInt64
    p[nums[0]] = 0
    var s int64 = 0
    n := len(nums)
    for i := 0; ; i++ {
        s += int64(nums[i])
        if t, ok := p[nums[i]-k]; ok {
            r = max(r, s-t)
        }
        if t, ok := p[nums[i]+k]; ok {
            r = max(r, s-t)
        }
        if i+1 == n {
            break
        }
        if t, ok := p[nums[i+1]]; !ok || t > s {
            p[nums[i+1]] = s
        }
    }
    if r == math.MinInt64 {
        return 0
    }
    return r
}
