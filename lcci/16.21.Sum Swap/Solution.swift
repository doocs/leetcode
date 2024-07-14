class Solution {
    func findSwapValues(_ array1: [Int], _ array2: [Int]) -> [Int] {
        var s1 = 0, s2 = 0
        var set = Set<Int>()
        
        for x in array1 {
            s1 += x
        }
        for x in array2 {
            s2 += x
            set.insert(x)
        }
        
        let diff = s1 - s2
        if diff % 2 != 0 {
            return []
        }
        let target = diff / 2
        
        for a in array1 {
            let b = a - target
            if set.contains(b) {
                return [a, b]
            }
        }
        return []
    }
}