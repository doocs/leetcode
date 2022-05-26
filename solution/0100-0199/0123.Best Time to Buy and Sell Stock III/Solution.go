func maxProfit(prices []int) int {
    f1, f2, f3, f4 := -prices[0], 0, -prices[0], 0
    for i := 1; i < len(prices); i++ {
        f1 = max(f1, -prices[i])
        f2 = max(f2, f1 + prices[i])
        f3 = max(f3, f2 - prices[i])
        f4 = max(f4, f3 + prices[i])
    }
    return f4
}

func max(a, b int) int {
    if a > b {
        return a
    }
    return b
}