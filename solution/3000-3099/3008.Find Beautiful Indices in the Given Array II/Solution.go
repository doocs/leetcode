func beautifulIndices(s string, a string, b string, k int) []int {
    
    
    s_len := len(s)
    a_len := len(a)
    b_len := len(b)
    
    final := make([]int, 0)
    lps_a := make([]int, a_len)
    lps_b := make([]int, b_len)
    a_index := make([]int, 0)
    b_index := make([]int, 0)
    
    var pat func(lps []int, s_l int, pattern string)

    pat = func(lps []int, s_l int, pattern string){
        
        l := 0
        lps[0] = 0
        i := 1
        
        for i < s_l {
            if pattern[i] == pattern[l] {
                l++
                lps[i] = l
                i++
            } else {
                if l != 0 {
                    l = lps[l-1]
                } else {
                    lps[i] = l
                    i++
                }
            }   
        }
    }

    pat(lps_a, a_len, a)
    pat(lps_b, b_len, b)

    var kmp func(pat string, pat_l int, lps []int, index *[]int)

    kmp = func(pat string, pat_l int, lps []int, index *[]int){
        i := 0
        j := 0
        for s_len - i >= pat_l - j {
            if s[i] == pat[j]{
                i++
                j++
            }
            if j == pat_l{
                *index = append(*index, i-pat_l)
                j = lps[j-1]
            } else if s[i] != pat[j] {
                if j!=0 {
                    j = lps[j-1]
                } else {
                    i++
                }
            }
        }
    }

    kmp(a, a_len, lps_a, &a_index)
    kmp(b, b_len, lps_b, &b_index)
    
    // fmt.Println(a_index, b_index)

    i := 0
    j := 0

    for i < len(a_index) && j < len(b_index) {
        if a_index[i]+k >= b_index[j] && a_index[i]-k <= b_index[j]{
            final = append(final, a_index[i])
            i++
        } else if a_index[i] - k > b_index[j]{
            j++
        } else {
            i++
        }
    }
    
    return final
}
