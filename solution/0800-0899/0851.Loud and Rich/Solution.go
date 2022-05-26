func loudAndRich(richer [][]int, quiet []int) []int {
    n := len(quiet)
    ans := make([]int, n)
    g := make([][]int, n)
    for i := 0; i < n; i++ {
        ans[i] = -1
        g[i] = make([]int, 0)
    }
    for _, r := range richer {
        g[r[1]] = append(g[r[1]], r[0])
    }

    var dfs func(i int)
    dfs = func(i int) {
        if ans[i] != - 1 {
            return
        }
        ans[i] = i
        for _, j := range g[i] {
            dfs(j)
            if quiet[ans[j]] < quiet[ans[i]] {
                ans[i] = ans[j]
            }
        }
    }

    for i := 0; i < n; i++ {
        dfs(i)
    }
    return ans
}