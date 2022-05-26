func longestCommomSubsequence(arrays [][]int) []int {
    counter := make(map[int]int)
    n := len(arrays)
    var res []int
    for _, array := range arrays {
        for _, e := range array {
            counter[e]++
            if counter[e] == n {
                res = append(res, e)
            }
        }
    }
    return res
}