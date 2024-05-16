---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1400-1499/1408.String%20Matching%20in%20an%20Array/README_EN.md
rating: 1223
source: Weekly Contest 184 Q1
tags:
    - Array
    - String
    - String Matching
---

<!-- problem:start -->

# [1408. String Matching in an Array](https://leetcode.com/problems/string-matching-in-an-array)

[中文文档](/solution/1400-1499/1408.String%20Matching%20in%20an%20Array/README.md)

## Description

<!-- description:start -->

<p>Given an array of string <code>words</code>, return <em>all strings in </em><code>words</code><em> that is a <strong>substring</strong> of another word</em>. You can return the answer in <strong>any order</strong>.</p>

<p>A <strong>substring</strong> is a contiguous sequence of characters within a string</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;mass&quot;,&quot;as&quot;,&quot;hero&quot;,&quot;superhero&quot;]
<strong>Output:</strong> [&quot;as&quot;,&quot;hero&quot;]
<strong>Explanation:</strong> &quot;as&quot; is substring of &quot;mass&quot; and &quot;hero&quot; is substring of &quot;superhero&quot;.
[&quot;hero&quot;,&quot;as&quot;] is also a valid answer.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;leetcode&quot;,&quot;et&quot;,&quot;code&quot;]
<strong>Output:</strong> [&quot;et&quot;,&quot;code&quot;]
<strong>Explanation:</strong> &quot;et&quot;, &quot;code&quot; are substring of &quot;leetcode&quot;.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;blue&quot;,&quot;green&quot;,&quot;bu&quot;]
<strong>Output:</strong> []
<strong>Explanation:</strong> No string of words is substring of another string.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 100</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 30</code></li>
	<li><code>words[i]</code> contains only lowercase English letters.</li>
	<li>All the strings of <code>words</code> are <strong>unique</strong>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Brute Force Enumeration

We directly enumerate all strings $words[i]$, and check whether it is a substring of other strings. If it is, we add it to the answer.

The time complexity is $O(n^3)$, and the space complexity is $O(n)$. Where $n$ is the length of the string array.

<!-- tabs:start -->

```python
class Solution:
    def stringMatching(self, words: List[str]) -> List[str]:
        ans = []
        for i, s in enumerate(words):
            if any(i != j and s in t for j, t in enumerate(words)):
                ans.append(s)
        return ans
```

```java
class Solution {
    public List<String> stringMatching(String[] words) {
        List<String> ans = new ArrayList<>();
        int n = words.length;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i != j && words[j].contains(words[i])) {
                    ans.add(words[i]);
                    break;
                }
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    vector<string> stringMatching(vector<string>& words) {
        vector<string> ans;
        int n = words.size();
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                if (i != j && words[j].find(words[i]) != string::npos) {
                    ans.push_back(words[i]);
                    break;
                }
            }
        }
        return ans;
    }
};
```

```go
func stringMatching(words []string) []string {
	ans := []string{}
	for i, w1 := range words {
		for j, w2 := range words {
			if i != j && strings.Contains(w2, w1) {
				ans = append(ans, w1)
				break
			}
		}
	}
	return ans
}
```

```ts
function stringMatching(words: string[]): string[] {
    const ans: string[] = [];
    const n = words.length;
    for (let i = 0; i < n; ++i) {
        for (let j = 0; j < n; ++j) {
            if (words[j].includes(words[i]) && i !== j) {
                ans.push(words[i]);
                break;
            }
        }
    }
    return ans;
}
```

```rust
impl Solution {
    pub fn string_matching(words: Vec<String>) -> Vec<String> {
        let mut ans = Vec::new();
        let n = words.len();
        for i in 0..n {
            for j in 0..n {
                if i != j && words[j].contains(&words[i]) {
                    ans.push(words[i].clone());
                    break;
                }
            }
        }
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
