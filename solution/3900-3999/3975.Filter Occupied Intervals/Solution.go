func filterOccupiedIntervals(occupiedIntervals [][]int, freeStart int, freeEnd int) [][]int {
    sort.Slice(occupiedIntervals, func(i, j int) bool {
        return occupiedIntervals[i][0] < occupiedIntervals[j][0]
    })

    busy := [][]int{occupiedIntervals[0]}

    for i := 1; i < len(occupiedIntervals); i++ {
        cur := occupiedIntervals[i]
        last := &busy[len(busy)-1]

        if (*last)[1]+1 < cur[0] {
            busy = append(busy, cur)
        } else {
            if cur[1] > (*last)[1] {
                (*last)[1] = cur[1]
            }
        }
    }

    ans := [][]int{}

    for _, it := range busy {
        s, e := it[0], it[1]

        if e < freeStart || s > freeEnd {
            ans = append(ans, []int{s, e})
        } else {
            if s < freeStart {
                ans = append(ans, []int{s, freeStart - 1})
            }
            if e > freeEnd {
                ans = append(ans, []int{freeEnd + 1, e})
            }
        }
    }

    return ans
}