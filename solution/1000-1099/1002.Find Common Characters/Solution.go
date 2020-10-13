func commonChars(A []string) []string {
    if len(A) == 0 {
        return []string{}
    }
    res := make([]int, 26)
    //以第一个字符串为基准，先统计出现次数
    for _, c := range A[0] {
        res[c - 'a']++
    }
    for i := 1; i < len(A); i++ {
        tmp := make([]int, 26)
        //统计后续每个字符串的字符出现次数
        for _, c := range A[i] {
            tmp[c - 'a']++
        }
        //比较，取小
        for j := 0; j < 26; j++ {
            res[j] = getMin(res[j], tmp[j])
        }
    }
    //遍历res,取出字符转换为string数组元素
    result := make([]string,0)
    for i := 0; i < len(res); i++ {
        if res[i] > 0 {
            for j := 0; j < res[i]; j++ {
                result = append(result, string('a' + i))
            }
        }
    }
    return result
}

func getMin(a,b int) int {
    if a > b{
        return b
    }
    return a
}





