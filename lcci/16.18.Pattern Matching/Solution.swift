class Solution {
    private var pattern: String = ""
    private var value: String = ""

    func patternMatching(_ pattern: String, _ value: String) -> Bool {
        self.pattern = pattern
        self.value = value
        var cnt = [Int](repeating: 0, count: 2)
        for c in pattern {
            cnt[Int(c.asciiValue! - Character("a").asciiValue!)] += 1
        }
        let n = value.count
        if cnt[0] == 0 {
            return n % cnt[1] == 0 && String(repeating: String(value.prefix(n / cnt[1])), count: cnt[1]) == value
        }
        if cnt[1] == 0 {
            return n % cnt[0] == 0 && String(repeating: String(value.prefix(n / cnt[0])), count: cnt[0]) == value
        }
        for la in 0...n {
            if la * cnt[0] > n {
                break
            }
            if (n - la * cnt[0]) % cnt[1] == 0 {
                let lb = (n - la * cnt[0]) / cnt[1]
                if check(la, lb) {
                    return true
                }
            }
        }
        return false
    }

    private func check(_ la: Int, _ lb: Int) -> Bool {
        var a: String? = nil
        var b: String? = nil
        var index = value.startIndex

        for c in pattern {
            if c == "a" {
                let end = value.index(index, offsetBy: la)
                if let knownA = a {
                    if knownA != value[index..<end] {
                        return false
                    }
                } else {
                    a = String(value[index..<end])
                }
                index = end
            } else {
                let end = value.index(index, offsetBy: lb)
                if let knownB = b {
                    if knownB != value[index..<end] {
                        return false
                    }
                } else {
                    b = String(value[index..<end])
                }
                index = end
            }
        }
        return a != b
    }
}