func minExp(x, c int) (int, int){
    d := math.Sqrt(float64(x))
    if d<2 || float64(int(d)) < d{
        return x, c
    }
    return minExp(int(d), c+1)
}


func maximumLength(nums []int) int {
    m := make(map[int][]int)
    for i := range nums{
        base, c := minExp(nums[i], 1)
        m[base] = append(m[base], c)
    }
    max := 1
    for _, v := range m{
        v := matchPattern(v)
        max = Max(max, v)
    }
    _, ok := m[1]; if ok{
        if len(m[1]) %2 > 0{
            max = Max(max, len(m[1]))    
        }else{
            max = Max(max, len(m[1])-1)    
        }
    }
    return max
}

func Max(i,j int) int{
    if i>j{return i}
    return j
}

func matchPattern(arr []int) int{
    sort.Slice(arr, func(i,j int)bool{return arr[i] < arr[j]})
    start := arr[0]
    bin := 2
    for i := range arr{
        if bin == 0{
            start++
            bin = 2
        }
        if arr[i] == start{
            bin--
        }
    }
    if bin == 1 {
        return 2*(start-arr[0])+1
    } else if bin == 0{
        return 2*(start-arr[0])+1
    } else {
        return 2*(start-arr[0])-1
    }
}
