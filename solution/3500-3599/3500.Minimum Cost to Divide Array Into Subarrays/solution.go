func minimumCost(a []int, b []int, k int) int64 {
    n := len(a)
    dp := make([]int, n + 1)
    for i := range n {
        dp[i] = math.MaxInt / 4
    }
    prea := make([]int, n + 1)
    preb := make([]int, n + 1)
    for i := range n {
        prea[i + 1] = prea[i] + a[i]
        preb[i + 1] = preb[i] + b[i]
    }
    for i := n - 1; i >= 0; i-- {
        for j := i; j < n; j++ {
            tot := (preb[j + 1] - preb[i]) * (prea[j + 1] - prea[0]) + (preb[n] - preb[i]) * k
            dp[i] = min(dp[i], tot + dp[j + 1])
        }
    }
    return int64(dp[0])
}