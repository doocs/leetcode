class Solution {
    func oneEditAway(_ first: String, _ second: String) -> Bool {
        let m = first.count, n = second.count
        if m < n {
            return oneEditAway(second, first)
        }
        if m - n > 1 {
            return false
        }
        
        var cnt = 0
        var firstIndex = first.startIndex
        var secondIndex = second.startIndex
        
        if m == n {
            while secondIndex != second.endIndex {
                if first[firstIndex] != second[secondIndex] {
                    cnt += 1
                    if cnt > 1 {
                        return false
                    }
                }
                firstIndex = first.index(after: firstIndex)
                secondIndex = second.index(after: secondIndex)
            }
            return true
        } else {
            while firstIndex != first.endIndex {
                if secondIndex == second.endIndex || (secondIndex != second.endIndex && first[firstIndex] != second[secondIndex]) {
                    cnt += 1
                } else {
                    secondIndex = second.index(after: secondIndex)
                }
                firstIndex = first.index(after: firstIndex)
            }
        }
        return cnt < 2
    }
}
