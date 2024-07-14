class Solution {
    func shortestSeq(_ big: [Int], _ small: [Int]) -> [Int] {
        let needCount = small.count
        var need = [Int: Int]()
        var window = [Int: Int]()
        small.forEach { need[$0, default: 0] += 1 }
        
        var count = needCount
        var minLength = Int.max
        var result = (-1, -1)
        
        var left = 0
        for right in 0..<big.count {
            let element = big[right]
            if need[element] != nil {
                window[element, default: 0] += 1
                if window[element]! <= need[element]! {
                    count -= 1
                }
            }
            
            while count == 0 {
                if right - left + 1 < minLength {
                    minLength = right - left + 1
                    result = (left, right)
                }
                
                let leftElement = big[left]
                if need[leftElement] != nil {
                    window[leftElement]! -= 1
                    if window[leftElement]! < need[leftElement]! {
                        count += 1
                    }
                }
                left += 1
            }
        }
        
        return result.0 == -1 ? [] : [result.0, result.1]
    }
}