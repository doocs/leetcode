# [481. Magical String](https://leetcode.com/problems/magical-string)

[中文文档](/solution/0400-0499/0481.Magical%20String/README.md)

## Description

<p>A magical string <code>s</code> consists of only <code>&#39;1&#39;</code> and <code>&#39;2&#39;</code> and obeys the following rules:</p>

<ul>
	<li>The string s is magical because concatenating the number of contiguous occurrences of characters <code>&#39;1&#39;</code> and <code>&#39;2&#39;</code> generates the string <code>s</code> itself.</li>
</ul>

<p>The first few elements of <code>s</code> is <code>s = &quot;1221121221221121122&hellip;&hellip;&quot;</code>. If we group the consecutive <code>1</code>&#39;s and <code>2</code>&#39;s in <code>s</code>, it will be <code>&quot;1 22 11 2 1 22 1 22 11 2 11 22 ......&quot;</code> and the occurrences of <code>1</code>&#39;s or <code>2</code>&#39;s in each group are <code>&quot;1 2 2 1 1 2 1 2 2 1 2 2 ......&quot;</code>. You can see that the occurrence sequence is <code>s</code> itself.</p>

<p>Given an integer <code>n</code>, return the number of <code>1</code>&#39;s in the first <code>n</code> number in the magical string <code>s</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 6
<strong>Output:</strong> 3
<strong>Explanation:</strong> The first 6 elements of magical string s is &quot;122112&quot; and it contains three 1&#39;s, so return 3.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 1
<strong>Output:</strong> 1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def magicalString(self, n: int) -> int:
        s = [1, 2, 2]
        i = 2
        while len(s) < n:
            pre = s[-1]
            cur = 3 - pre
            s += [cur] * s[i]
            i += 1
        return s[:n].count(1)
```

### **Java**

```java
class Solution {
    public int magicalString(int n) {
        List<Integer> s = new ArrayList<>(Arrays.asList(1, 2, 2));
        for (int i = 2; s.size() < n; ++i) {
            int pre = s.get(s.size() - 1);
            int cur = 3 - pre;
            for (int j = 0; j < s.get(i); ++j) {
                s.add(cur);
            }
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            if (s.get(i) == 1) {
                ++ans;
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int magicalString(int n) {
        vector<int> s = {1, 2, 2};
        for (int i = 2; s.size() < n; ++i) {
            int pre = s.back();
            int cur = 3 - pre;
            for (int j = 0; j < s[i]; ++j) {
                s.emplace_back(cur);
            }
        }
        return count(s.begin(), s.begin() + n, 1);
    }
};
```

### **Go**

```go
func magicalString(n int) (ans int) {
	s := []int{1, 2, 2}
	for i := 2; len(s) < n; i++ {
		pre := s[len(s)-1]
		cur := 3 - pre
		for j := 0; j < s[i]; j++ {
			s = append(s, cur)
		}
	}
	for _, c := range s[:n] {
		if c == 1 {
			ans++
		}
	}
	return
}
```

### **TypeScript**

```ts
function magicalString(n: number): number {
    const cs = [...'1221121'];
    let i = 5;
    while (cs.length < n) {
        const c = cs[cs.length - 1];
        cs.push(c === '1' ? '2' : '1');
        if (cs[i] !== '1') {
            cs.push(c === '1' ? '2' : '1');
        }
        i++;
    }
    return cs.slice(0, n).reduce((r, c) => r + (c === '1' ? 1 : 0), 0);
}
```

### **Rust**

```rust
impl Solution {
    pub fn magical_string(n: i32) -> i32 {
        let n = n as usize;
        let mut s = String::from("1221121");
        let mut i = 5;
        while s.len() < n {
            let c = s.as_bytes()[s.len() - 1];
            s.push(if c == b'1' { '2' } else { '1' });
            if s.as_bytes()[i] != b'1' {
                s.push(if c == b'1' { '2' } else { '1' });
            }
            i += 1;
        }
        s.as_bytes()[0..n].iter().filter(|&v| v == &b'1').count() as i32
    }
}
```

### **...**

```

```

<!-- tabs:end -->
