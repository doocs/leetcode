class Solution {
    func findClosedNumbers(_ num: Int) -> [Int] {
        var ans = [-1, -1]
        let dirs = [0, 1, 0]
        
        for p in 0..<2 {
            let a = dirs[p], b = dirs[p + 1]
            var x = num
            var found = false
            
            for i in 1..<31 {
                if ((x >> i) & 1) == a && ((x >> (i - 1)) & 1) == b {
                    x ^= (1 << i)
                    x ^= (1 << (i - 1))
                    
                    var j = 0, k = i - 2
                    while j < k {
                        while j < k && ((x >> j) & 1) == b {
                            j += 1
                        }
                        while j < k && ((x >> k) & 1) == a {
                            k -= 1
                        }
                        if j < k {
                            x ^= (1 << j)
                            x ^= (1 << k)
                        }
                    }
                    ans[p] = x
                    found = true
                    break
                }
            }
            if !found {
                ans[p] = -1
            }
        }
        
        return ans
    }
}
