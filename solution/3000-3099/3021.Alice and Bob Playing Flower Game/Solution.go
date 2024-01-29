func flowerGame(n int, m int) int64 {
    count := int64((n + 1) / 2)
    tol := int64((m + 1) / 2)
    ecount := int64(n / 2)
    etol := int64(m / 2)
    return count*etol + ecount*tol
}
