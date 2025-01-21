class Solution {
    private var plate: [String] = []
    private var num: Int = 0
    private var m: Int = 0
    private var n: Int = 0
    private let dirs = [0, 1, 0, -1, 0]

    func ballGame(_ num: Int, _ plate: [String]) -> [[Int]] {
        self.num = num
        self.plate = plate
        self.m = plate.count
        self.n = plate[0].count
        var ans: [[Int]] = []

        for i in 1..<m - 1 {
            if plate[i][0] == "." && check(i, 0, 0) {
                ans.append([i, 0])
            }
            if plate[i][n - 1] == "." && check(i, n - 1, 2) {
                ans.append([i, n - 1])
            }
        }

        for j in 1..<n - 1 {
            if plate[0][j] == "." && check(0, j, 1) {
                ans.append([0, j])
            }
            if plate[m - 1][j] == "." && check(m - 1, j, 3) {
                ans.append([m - 1, j])
            }
        }

        return ans
    }

    private func check(_ i: Int, _ j: Int, _ d: Int) -> Bool {
        var k = num
        var i = i, j = j, d = d

        while plate[i][j] != "O" {
            if k == 0 {
                return false
            }

            if plate[i][j] == "W" {
                d = (d + 3) % 4
            } else if plate[i][j] == "E" {
                d = (d + 1) % 4
            }

            i += dirs[d]
            j += dirs[d + 1]

            if i < 0 || i >= m || j < 0 || j >= n {
                return false
            }

            k -= 1
        }

        return true
    }
}

private extension String {
    subscript(_ index: Int) -> Character {
        return self[self.index(self.startIndex, offsetBy: index)]
    }
}