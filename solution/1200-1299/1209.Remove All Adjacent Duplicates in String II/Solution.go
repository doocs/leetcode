func removeDuplicates(s string, k int) string {
    stk := []pair{}
    for _, c := range s {
        if len(stk) > 0 && stk[len(stk)-1].c == c {
            stk[len(stk)-1].v = (stk[len(stk)-1].v + 1) % k
            if stk[len(stk)-1].v == 0 {
                stk = stk[:len(stk)-1]
            }
        } else {
            stk = append(stk, pair{c, 1})
        }
    }
    ans := []rune{}
    for _, e := range stk {
        for i := 0; i < e.v; i++ {
            ans = append(ans, e.c)
        }
    }
    return string(ans)
}

type pair struct {
    c rune
    v int
}