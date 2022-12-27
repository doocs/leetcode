# [2515. Shortest Distance to Target String in a Circular Array](https://leetcode.com/problems/shortest-distance-to-target-string-in-a-circular-array)

[中文文档](/solution/2500-2599/2515.Shortest%20Distance%20to%20Target%20String%20in%20a%20Circular%20Array/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> <strong>circular</strong> string array <code>words</code> and a string <code>target</code>. A <strong>circular array</strong> means that the array&#39;s end connects to the array&#39;s beginning.</p>

<ul>
	<li>Formally, the next element of <code>words[i]</code> is <code>words[(i + 1) % n]</code> and the previous element of <code>words[i]</code> is <code>words[(i - 1 + n) % n]</code>, where <code>n</code> is the length of <code>words</code>.</li>
</ul>

<p>Starting from <code>startIndex</code>, you can move to either the next word or the previous word with <code>1</code> step at a time.</p>

<p>Return <em>the <strong>shortest</strong> distance needed to reach the string</em> <code>target</code>. If the string <code>target</code> does not exist in <code>words</code>, return <code>-1</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;hello&quot;,&quot;i&quot;,&quot;am&quot;,&quot;leetcode&quot;,&quot;hello&quot;], target = &quot;hello&quot;, startIndex = 1
<strong>Output:</strong> 1
<strong>Explanation:</strong> We start from index 1 and can reach &quot;hello&quot; by
- moving 3 units to the right to reach index 4.
- moving 2 units to the left to reach index 4.
- moving 4 units to the right to reach index 0.
- moving 1 unit to the left to reach index 0.
The shortest distance to reach &quot;hello&quot; is 1.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;a&quot;,&quot;b&quot;,&quot;leetcode&quot;], target = &quot;leetcode&quot;, startIndex = 0
<strong>Output:</strong> 1
<strong>Explanation:</strong> We start from index 0 and can reach &quot;leetcode&quot; by
- moving 2 units to the right to reach index 3.
- moving 1 unit to the left to reach index 3.
The shortest distance to reach &quot;leetcode&quot; is 1.</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> words = [&quot;i&quot;,&quot;eat&quot;,&quot;leetcode&quot;], target = &quot;ate&quot;, startIndex = 0
<strong>Output:</strong> -1
<strong>Explanation:</strong> Since &quot;ate&quot; does not exist in <code>words</code>, we return -1.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 100</code></li>
	<li><code>1 &lt;= words[i].length &lt;= 100</code></li>
	<li><code>words[i]</code> and <code>target</code> consist of only lowercase English letters.</li>
	<li><code>0 &lt;= startIndex &lt; words.length</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def closetTarget(self, words: List[str], target: str, startIndex: int) -> int:
        n = len(words)
        ans = n
        for i, w in enumerate(words):
            if w == target:
                t = abs(i - startIndex)
                ans = min(ans, t, n - t)
        return -1 if ans == n else ans
```

### **Java**

```java
class Solution {
    public int closetTarget(String[] words, String target, int startIndex) {
        int n = words.length;
        int ans = n;
        for (int i = 0; i < n; ++i) {
            String w = words[i];
            if (w.equals(target)) {
                int t = Math.abs(i - startIndex);
                ans = Math.min(ans, Math.min(t, n - t));
            }
        }
        return ans == n ? -1 : ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int closetTarget(vector<string>& words, string target, int startIndex) {
        int n = words.size();
        int ans = n;
        for (int i = 0; i < n; ++i) {
            auto w = words[i];
            if (w == target) {
                int t = abs(i - startIndex);
                ans = min(ans, min(t, n - t));
            }
        }
        return ans == n ? -1 : ans;
    }
};
```

### **Go**

```go
func closetTarget(words []string, target string, startIndex int) int {
	n := len(words)
	ans := n
	for i, w := range words {
		if w == target {
			t := abs(i - startIndex)
			ans = min(ans, min(t, n-t))
		}
	}
	if ans == n {
		return -1
	}
	return ans
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **TypeScript**

```ts
function closetTarget(
    words: string[],
    target: string,
    startIndex: number,
): number {
    const n = words.length;
    for (let i = 0; i <= n >> 1; i++) {
        if (
            words[(startIndex - i + n) % n] === target ||
            words[(startIndex + i) % n] === target
        ) {
            return i;
        }
    }
    return -1;
}
```

### **Rust**

```rust
impl Solution {
    pub fn closet_target(words: Vec<String>, target: String, start_index: i32) -> i32 {
        let start_index = start_index as usize;
        let n = words.len();
        for i in 0..=n >> 1 {
            if words[(start_index - i + n) % n] == target || words[(start_index + i) % n] == target
            {
                return i as i32;
            }
        }
        -1
    }
}
```

### **C**

```c
int closetTarget(char **words, int wordsSize, char *target, int startIndex) {
    for (int i = 0; i <= wordsSize >> 1; i++) {
        if (strcmp(words[(startIndex - i + wordsSize) % wordsSize], target) == 0 ||
            strcmp(words[(startIndex + i) % wordsSize], target) == 0) {
            return i;
        }
    }
    return -1;
}
```

### **...**

```

```

<!-- tabs:end -->
