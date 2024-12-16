func beautifulSplits(nums []int) (ans int) {
    n := len(nums)
    lcp := make([][]int, n+1)
    for i := range lcp {
        lcp[i] = make([]int, n+1)
    }

    for i := n - 1; i >= 0; i-- {
        for j := n - 1; j > i; j-- {
            if nums[i] == nums[j] {
                lcp[i][j] = lcp[i+1][j+1] + 1
            }
        }
    }

    for i := 1; i < n-1; i++ {
        for j := i + 1; j < n; j++ {
            a := i <= j-i && lcp[0][i] >= i
            b := j-i <= n-j && lcp[i][j] >= j-i
            if a || b {
                ans++
            }
        }
    }

    return
}
