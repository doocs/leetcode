class Solution {
    func perfectMenu(_ materials: [Int], _ cookbooks: [[Int]], _ attribute: [[Int]], _ limit: Int) -> Int {
        let n = cookbooks.count
        var ans = -1

        for mask in 0..<(1 << n) {
            var a = 0, b = 0
            var cnt = [Int](repeating: 0, count: 5)

            for i in 0..<n {
                if (mask >> i & 1) == 1 {
                    a += attribute[i][0]
                    b += attribute[i][1]
                    for j in 0..<cookbooks[i].count {
                        cnt[j] += cookbooks[i][j]
                    }
                }
            }

            var ok = true
            for i in 0..<5 {
                if cnt[i] > materials[i] {
                    ok = false
                    break
                }
            }

            if b >= limit && a > ans && ok {
                ans = a
            }
        }

        return ans
    }
}