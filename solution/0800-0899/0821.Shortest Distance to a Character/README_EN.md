# [821. Shortest Distance to a Character](https://leetcode.com/problems/shortest-distance-to-a-character)

[中文文档](/solution/0800-0899/0821.Shortest%20Distance%20to%20a%20Character/README.md)

## Description

<p>Given a string <code>s</code> and a character <code>c</code> that occurs in <code>s</code>, return <em>an array of integers </em><code>answer</code><em> where </em><code>answer.length == s.length</code><em> and </em><code>answer[i]</code><em> is the <strong>distance</strong> from index </em><code>i</code><em> to the <strong>closest</strong> occurrence of character </em><code>c</code><em> in </em><code>s</code>.</p>

<p>The <strong>distance</strong> between two indices <code>i</code> and <code>j</code> is <code>abs(i - j)</code>, where <code>abs</code> is the absolute value function.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;loveleetcode&quot;, c = &quot;e&quot;
<strong>Output:</strong> [3,2,1,0,1,0,0,1,2,2,1,0]
<strong>Explanation:</strong> The character &#39;e&#39; appears at indices 3, 5, 6, and 11 (0-indexed).
The closest occurrence of &#39;e&#39; for index 0 is at index 3, so the distance is abs(0 - 3) = 3.
The closest occurrence of &#39;e&#39; for index 1 is at index 3, so the distance is abs(1 - 3) = 2.
For index 4, there is a tie between the &#39;e&#39; at index 3 and the &#39;e&#39; at index 5, but the distance is still the same: abs(4 - 3) == abs(4 - 5) = 1.
The closest occurrence of &#39;e&#39; for index 8 is at index 6, so the distance is abs(8 - 6) = 2.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;aaab&quot;, c = &quot;b&quot;
<strong>Output:</strong> [3,2,1,0]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>4</sup></code></li>
	<li><code>s[i]</code> and <code>c</code> are lowercase English letters.</li>
	<li>It is guaranteed that <code>c</code> occurs at least once in <code>s</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def shortestToChar(self, s: str, c: str) -> List[int]:
        n = len(s)
        ans = [n] * n
        pre = -inf
        for i, ch in enumerate(s):
            if ch == c:
                pre = i
            ans[i] = min(ans[i], i - pre)
        suf = inf
        for i in range(n - 1, -1, -1):
            if s[i] == c:
                suf = i
            ans[i] = min(ans[i], suf - i)
        return ans
```

### **Java**

```java
class Solution {
    public int[] shortestToChar(String s, char c) {
        int n = s.length();
        int[] ans = new int[n];
        final int inf = 1 << 30;
        Arrays.fill(ans, inf);
        for (int i = 0, pre = -inf; i < n; ++i) {
            if (s.charAt(i) == c) {
                pre = i;
            }
            ans[i] = Math.min(ans[i], i - pre);
        }
        for (int i = n - 1, suf = inf; i >= 0; --i) {
            if (s.charAt(i) == c) {
                suf = i;
            }
            ans[i] = Math.min(ans[i], suf - i);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> shortestToChar(string s, char c) {
        int n = s.size();
        const int inf = 1 << 30;
        vector<int> ans(n, inf);
        for (int i = 0, pre = -inf; i < n; ++i) {
            if (s[i] == c) {
                pre = i;
            }
            ans[i] = min(ans[i], i - pre);
        }
        for (int i = n - 1, suf = inf; ~i; --i) {
            if (s[i] == c) {
                suf = i;
            }
            ans[i] = min(ans[i], suf - i);
        }
        return ans;
    }
};
```

### **Go**

```go
func shortestToChar(s string, c byte) []int {
	n := len(s)
	ans := make([]int, n)
	const inf int = 1 << 30
	pre := -inf
	for i := range s {
		if s[i] == c {
			pre = i
		}
		ans[i] = i - pre
	}
	suf := inf
	for i := n - 1; i >= 0; i-- {
		if s[i] == c {
			suf = i
		}
		ans[i] = min(ans[i], suf-i)
	}
	return ans
}
```

### **TypeScript**

```ts
function shortestToChar(s: string, c: string): number[] {
    const n = s.length;
    const inf = 1 << 30;
    const ans: number[] = new Array(n).fill(inf);
    for (let i = 0, pre = -inf; i < n; ++i) {
        if (s[i] === c) {
            pre = i;
        }
        ans[i] = i - pre;
    }
    for (let i = n - 1, suf = inf; i >= 0; --i) {
        if (s[i] === c) {
            suf = i;
        }
        ans[i] = Math.min(ans[i], suf - i);
    }
    return ans;
}
```

### **Rust**

```rust
impl Solution {
    pub fn shortest_to_char(s: String, c: char) -> Vec<i32> {
        let c = c as u8;
        let s = s.as_bytes();
        let n = s.len();
        let mut res = vec![i32::MAX; n];
        let mut pre = i32::MAX;
        for i in 0..n {
            if s[i] == c {
                pre = i as i32;
            }
            res[i] = i32::abs(i as i32 - pre);
        }
        pre = i32::MAX;
        for i in (0..n).rev() {
            if s[i] == c {
                pre = i as i32;
            }
            res[i] = res[i].min(i32::abs(i as i32 - pre));
        }
        res
    }
}
```

### **...**

```

```

<!-- tabs:end -->
