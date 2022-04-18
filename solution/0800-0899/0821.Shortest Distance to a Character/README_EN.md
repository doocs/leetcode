# [821. Shortest Distance to a Character](https://leetcode.com/problems/shortest-distance-to-a-character)

[中文文档](/solution/0800-0899/0821.Shortest%20Distance%20to%20a%20Character/README.md)

## Description

<p>Given a string <code>s</code> and a character <code>c</code> that occurs in <code>s</code>, return <em>an array of integers </em><code>answer</code><em> where </em><code>answer.length == s.length</code><em> and </em><code>answer[i]</code><em> is the <strong>distance</strong> from index </em><code>i</code><em> to the <strong>closest</strong> occurrence of character </em><code>c</code><em> in </em><code>s</code>.</p>

<p>The <strong>distance</strong> between two indices <code>i</code> and <code>j</code> is <code>abs(i - j)</code>, where <code>abs</code> is the absolute value function.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;loveleetcode&quot;, c = &quot;e&quot;
<strong>Output:</strong> [3,2,1,0,1,0,0,1,2,2,1,0]
<strong>Explanation:</strong> The character &#39;e&#39; appears at indices 3, 5, 6, and 11 (0-indexed).
The closest occurrence of &#39;e&#39; for index 0 is at index 3, so the distance is abs(0 - 3) = 3.
The closest occurrence of &#39;e&#39; for index 1 is at index 3, so the distance is abs(1 - 3) = 2.
For index 4, there is a tie between the &#39;e&#39; at index 3 and the &#39;e&#39; at index 5, but the distance is still the same: abs(4 - 3) == abs(4 - 5) = 1.
The closest occurrence of &#39;e&#39; for index 8 is at index 6, so the distance is abs(8 - 6) = 2.
</pre>

<p><strong>Example 2:</strong></p>

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

```

### **Java**

```java

```

### **TypeScript**

```ts
function shortestToChar(s: string, c: string): number[] {
    const n = s.length;
    let ans = [];
    let pre = Infinity;
    for (let i = 0; i < n; i++) {
        if (s.charAt(i) == c) pre = i;
        ans[i] = Math.abs(pre - i);
    }
    pre = Infinity;
    for (let i = n - 1; i > -1; i--) {
        if (s.charAt(i) == c) pre = i;
        ans[i] = Math.min(Math.abs(pre - i), ans[i]);
    }
    return ans;
}
```

```ts
function shortestToChar(s: string, c: string): number[] {
    const n = s.length;
    const idxs = [];
    for (let i = 0; i < n; i++) {
        if (s[i] === c) {
            idxs.push(i);
        }
    }
    idxs.push(Infinity);

    const res = new Array(n);
    let i = 0;
    for (let j = 0; j < n; j++) {
        if (Math.abs(idxs[i] - j) > Math.abs(idxs[i + 1] - j)) {
            i++;
        }
        res[j] = Math.abs(idxs[i] - j);
    }
    return res;
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
