---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/lcci/17.13.Re-Space/README_EN.md
---

<!-- problem:start -->

# [17.13. Re-Space](https://leetcode.cn/problems/re-space-lcci)

[中文文档](/lcci/17.13.Re-Space/README.md)

## Description

<!-- description:start -->

<p>Oh, no! You have accidentally removed all spaces, punctuation, and capitalization in a lengthy document. A sentence like &quot;I reset the computer. It still didn&#39;t boot!&quot; became &quot;iresetthecomputeritstilldidntboot&#39;&#39;. You&#39;ll deal with the punctuation and capi&shy;talization later; right now you need to re-insert the spaces. Most of the words are in a dictionary but a few are not. Given a dictionary (a list of strings) and the document (a string), design an algorithm to unconcatenate the document in a way that minimizes the number of unrecognized characters. Return the number of unrecognized characters.</p>

<p><strong>Note: </strong>This&nbsp;problem is slightly different from the original one in the book.</p>

<p>&nbsp;</p>

<p><strong>Example: </strong></p>

<pre>

<strong>Input: </strong>

dictionary = [&quot;looked&quot;,&quot;just&quot;,&quot;like&quot;,&quot;her&quot;,&quot;brother&quot;]

sentence = &quot;jesslookedjustliketimherbrother&quot;

<strong>Output: </strong> 7

<strong>Explanation: </strong> After unconcatenating, we got &quot;<strong>jess</strong> looked just like <strong>tim</strong> her brother&quot;, which containing 7 unrecognized characters.

</pre>

<p><strong>Note: </strong></p>

<ul>
	<li><code>0 &lt;= len(sentence) &lt;= 1000</code></li>
	<li><code><font face="sans-serif, Arial, Verdana, Trebuchet MS">The total number of characters in&nbsp;</font>dictionary</code>&nbsp;is less than or equal to 150000.</li>
	<li>There are only lowercase letters in&nbsp;<code>dictionary</code>&nbsp;and&nbsp;<code>sentence</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

```python
class Solution:
    def respace(self, dictionary: List[str], sentence: str) -> int:
        s = set(dictionary)
        n = len(sentence)
        dp = [0] * (n + 1)
        for i in range(1, n + 1):
            dp[i] = dp[i - 1] + 1
            for j in range(i):
                if sentence[j:i] in s:
                    dp[i] = min(dp[i], dp[j])
        return dp[-1]
```

```java
class Solution {
    public int respace(String[] dictionary, String sentence) {
        Set<String> dict = new HashSet<>(Arrays.asList(dictionary));
        int n = sentence.length();
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - 1] + 1;
            for (int j = 0; j < i; ++j) {
                if (dict.contains(sentence.substring(j, i))) {
                    dp[i] = Math.min(dp[i], dp[j]);
                }
            }
        }
        return dp[n];
    }
}
```

```cpp
class Solution {
public:
    int respace(vector<string>& dictionary, string sentence) {
        unordered_set<string> s(dictionary.begin(), dictionary.end());
        int n = sentence.size();
        vector<int> dp(n + 1);
        for (int i = 1; i <= n; ++i) {
            dp[i] = dp[i - 1] + 1;
            for (int j = 0; j < i; ++j) {
                if (s.count(sentence.substr(j, i - j))) {
                    dp[i] = min(dp[i], dp[j]);
                }
            }
        }
        return dp[n];
    }
};
```

```go
func respace(dictionary []string, sentence string) int {
	s := map[string]bool{}
	for _, v := range dictionary {
		s[v] = true
	}
	n := len(sentence)
	dp := make([]int, n+1)
	for i := 1; i <= n; i++ {
		dp[i] = dp[i-1] + 1
		for j := 0; j < i; j++ {
			if s[sentence[j:i]] {
				dp[i] = min(dp[i], dp[j])
			}
		}
	}
	return dp[n]
}
```

```swift
class TrieNode {
    var children: [TrieNode?] = Array(repeating: nil, count: 26)
    var isEndOfWord = false
}

class Trie {
    private let root = TrieNode()

    func insert(_ word: String) {
        var node = root
        for char in word {
            let index = Int(char.asciiValue! - Character("a").asciiValue!)
            if node.children[index] == nil {
                node.children[index] = TrieNode()
            }
            node = node.children[index]!
        }
        node.isEndOfWord = true
    }

    func search(_ sentence: Array<Character>, start: Int, end: Int) -> Bool {
        var node = root
        for i in start...end {
            let index = Int(sentence[i].asciiValue! - Character("a").asciiValue!)
            guard let nextNode = node.children[index] else {
                return false
            }
            node = nextNode
        }
        return node.isEndOfWord
    }
}

class Solution {
    func respace(_ dictionary: [String], _ sentence: String) -> Int {
        let n = sentence.count
        guard n > 0 else { return 0 }
        let trie = Trie()
        dictionary.forEach { trie.insert($0) }
        let chars = Array(sentence)
        var dp = Array(repeating: Int.max, count: n + 1)
        dp[0] = 0
        for i in 1...n {
            dp[i] = dp[i - 1] + 1
            for j in 0..<i {
                if trie.search(chars, start: j, end: i - 1) {
                    dp[i] = min(dp[i], dp[j])
                }
            }
        }
        return dp[n]
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
