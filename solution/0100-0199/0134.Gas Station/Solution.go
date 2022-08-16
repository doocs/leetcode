func canCompleteCircuit(gas []int, cost []int) int {
    n := len(gas)
    i, j := n - 1, n - 1
    cnt, s := 0, 0
    for cnt < n {
        s += gas[j] - cost[j]
        cnt++
        j = (j + 1) % n
        for s < 0 && cnt < n {
            i--
            s += gas[i] - cost[i]
            cnt++
        }
    }
    if s < 0 {
        return -1
    }
    return i
}