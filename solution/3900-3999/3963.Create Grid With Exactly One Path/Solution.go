func createGrid(m int, n int) []string {
    g := make([][]byte, m)
    for i := range g {
        g[i] = make([]byte, n)
        for j := range g[i] {
            g[i][j] = '#'
        }
    }

    for j := 0; j < n; j++ {
        g[0][j] = '.'
    }

    for i := 0; i < m; i++ {
        g[i][n-1] = '.'
    }

    ans := make([]string, m)
    for i := range g {
        ans[i] = string(g[i])
    }
    return ans
}