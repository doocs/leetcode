class Solution {
    func minWindow(_ s: String, _ t: String) -> String {
        let m = s.count, n = t.count
        if n > m {
            return ""
        }

        var need = [Character: Int]()
        var window = [Character: Int]()
        
        for ch in t {
            need[ch, default: 0] += 1
        }

        let sArray = Array(s)
        var start = 0, minLen = Int.max
        var left = 0, right = 0

        while right < m {
            let ch = sArray[right]
            window[ch, default: 0] += 1
            right += 1
            
            while check(need, window) {
                if right - left < minLen {
                    minLen = right - left
                    start = left
                }
                let leftChar = sArray[left]
                window[leftChar, default: 0] -= 1
                left += 1
            }
        }

        return minLen == Int.max ? "" : String(sArray[start..<start + minLen])
    }

    private func check(_ need: [Character: Int], _ window: [Character: Int]) -> Bool {
        for (key, value) in need {
            if window[key, default: 0] < value {
                return false
            }
        }
        return true
    }
}