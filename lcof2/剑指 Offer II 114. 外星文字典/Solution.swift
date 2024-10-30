class Solution {
    func alienOrder(_ words: [String]) -> String {
        var graph = Array(repeating: Set<Int>(), count: 26)
        var indegree = Array(repeating: 0, count: 26)
        var seen = Array(repeating: false, count: 26)
        var letterCount = 0
        
        for i in 0..<words.count - 1 {
            for char in words[i] {
                let index = Int(char.asciiValue! - Character("a").asciiValue!)
                if !seen[index] {
                    seen[index] = true
                    letterCount += 1
                }
            }
            let minLength = min(words[i].count, words[i + 1].count)
            for j in 0..<minLength {
                let char1 = words[i][words[i].index(words[i].startIndex, offsetBy: j)]
                let char2 = words[i + 1][words[i + 1].index(words[i + 1].startIndex, offsetBy: j)]
                
                if char1 != char2 {
                    let c1 = Int(char1.asciiValue! - Character("a").asciiValue!)
                    let c2 = Int(char2.asciiValue! - Character("a").asciiValue!)
                    
                    if !graph[c1].contains(c2) {
                        graph[c1].insert(c2)
                        indegree[c2] += 1
                    }
                    break
                }
                
                if j == minLength - 1 && words[i].count > words[i + 1].count {
                    return ""
                }
            }
        }
        
        for char in words[words.count - 1] {
            let index = Int(char.asciiValue! - Character("a").asciiValue!)
            if !seen[index] {
                seen[index] = true
                letterCount += 1
            }
        }
        
        var queue = [Int]()
        for i in 0..<26 {
            if seen[i] && indegree[i] == 0 {
                queue.append(i)
            }
        }
        
        var order = ""
        while !queue.isEmpty {
            let u = queue.removeFirst()
            order += String(UnicodeScalar(u + Int(Character("a").asciiValue!))!)
            for v in graph[u] {
                indegree[v] -= 1
                if indegree[v] == 0 {
                    queue.append(v)
                }
            }
        }
        
        return order.count == letterCount ? order : ""
    }
}
