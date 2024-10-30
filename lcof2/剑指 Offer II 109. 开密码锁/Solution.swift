class Solution {
    func openLock(_ deadends: [String], _ target: String) -> Int {
        let deadSet = Set(deadends)
        if deadSet.contains(target) || deadSet.contains("0000") {
            return -1
        }
        if target == "0000" {
            return 0
        }
        
        var visited = Set<String>()
        var queue = ["0000"]
        visited.insert("0000")
        var step = 0
        
        while !queue.isEmpty {
            step += 1
            for _ in 0..<queue.count {
                let status = queue.removeFirst()
                for neighbor in getNeighbors(status) {
                    if visited.contains(neighbor) || deadSet.contains(neighbor) {
                        continue
                    }
                    if neighbor == target {
                        return step
                    }
                    queue.append(neighbor)
                    visited.insert(neighbor)
                }
            }
        }
        
        return -1
    }
    
    private func getNeighbors(_ lock: String) -> [String] {
        var neighbors = [String]()
        var chars = Array(lock)
        for i in 0..<4 {
            let original = chars[i]
            chars[i] = prevChar(original)
            neighbors.append(String(chars))
            chars[i] = nextChar(original)
            neighbors.append(String(chars))
            chars[i] = original
        }
        return neighbors
    }
    
    private func prevChar(_ c: Character) -> Character {
        return c == "0" ? "9" : Character(UnicodeScalar(c.asciiValue! - 1))
    }

    private func nextChar(_ c: Character) -> Character {
        return c == "9" ? "0" : Character(UnicodeScalar(c.asciiValue! + 1))
    }
}
