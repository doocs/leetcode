func maxArea(height []int) int {
    i, j := 0, len(height) - 1
    res := 0
    for i != j {
        t := (j - i) * min(height[i], height[j])
        res = max(res, t)
        if height[i] < height[j] {
            i++
        } else {
            j--
        }
    }
    return res
}

func min(a, b int) int {
    if a > b {
        return b
    }
    return a
}

func max(a, b int) int {
    if a > b {
        return a
    }
    return b
}