# [942. 增减字符串匹配](https://leetcode.cn/problems/di-string-match)

[English Version](/solution/0900-0999/0942.DI%20String%20Match/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>由范围 <code>[0,n]</code> 内所有整数组成的 <code>n + 1</code> 个整数的排列序列可以表示为长度为 <code>n</code> 的字符串 <code>s</code> ，其中:</p>

<ul>
	<li>如果&nbsp;<code>perm[i] &lt; perm[i + 1]</code>&nbsp;，那么&nbsp;<code>s[i] == 'I'</code>&nbsp;</li>
	<li>如果&nbsp;<code>perm[i] &gt; perm[i + 1]</code>&nbsp;，那么 <code>s[i] == 'D'</code>&nbsp;</li>
</ul>

<p>给定一个字符串 <code>s</code> ，重构排列&nbsp;<code>perm</code> 并返回它。如果有多个有效排列perm，则返回其中 <strong>任何一个</strong> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "IDID"
<strong>输出：</strong>[0,4,1,3,2]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "III"
<strong>输出：</strong>[0,1,2,3]
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = "DDI"
<strong>输出：</strong>[3,2,0,1]</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code><font color="#c7254e"><font face="Menlo, Monaco, Consolas, Courier New, monospace"><span style="font-size:12.6px"><span style="background-color:#f9f2f4">s</span></span></font></font></code> 只包含字符&nbsp;<code>"I"</code>&nbsp;或&nbsp;<code>"D"</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

类似贪心思想，如果当前字母是 `I`，我们只需要选择当前可选的最小数字，就能保证后面的数字无论怎么排列，当前数字和下一个数字一定是递增关系。`D` 同理，选择当前可选的最大数字即可

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def diStringMatch(self, s: str) -> List[int]:
        n = len(s)
        low, high = 0, n
        ans = []
        for i in range(n):
            if s[i] == 'I':
                ans.append(low)
                low += 1
            else:
                ans.append(high)
                high -= 1
        ans.append(low)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int[] diStringMatch(String s) {
        int n = s.length();
        int low = 0, high = n;
        int[] ans = new int[n + 1];
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == 'I') {
                ans[i] = low++;
            } else {
                ans[i] = high--;
            }
        }
        ans[n] = low;
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> diStringMatch(string s) {
        int n = s.size();
        int low = 0, high = n;
        vector<int> ans(n + 1);
        for (int i = 0; i < n; ++i) {
            if (s[i] == 'I') {
                ans[i] = low++;
            } else {
                ans[i] = high--;
            }
        }
        ans[n] = low;
        return ans;
    }
};
```

### **Go**

```go
func diStringMatch(s string) []int {
	n := len(s)
	low, high := 0, n
	var ans []int
	for i := 0; i < n; i++ {
		if s[i] == 'I' {
			ans = append(ans, low)
			low++
		} else {
			ans = append(ans, high)
			high--
		}
	}
	ans = append(ans, low)
	return ans
}
```

### **TypeScript**

```ts
function diStringMatch(s: string): number[] {
    const n = s.length;
    const res = new Array(n + 1);
    let low = 0;
    let high = n;
    for (let i = 0; i < n; i++) {
        if (s[i] === 'I') {
            res[i] = low++;
        } else {
            res[i] = high--;
        }
    }
    res[n] = low;
    return res;
}
```

### **Rust**

```rust
impl Solution {
    pub fn di_string_match(s: String) -> Vec<i32> {
        let s = s.as_bytes();
        let n = s.len();
        let mut res = Vec::with_capacity(n + 1);
        let (mut low, mut high) = (-1, (n + 1) as i32);
        for i in 0..n {
            res.push(if s[i] == b'I' {
                low += 1;
                low
            } else {
                high -= 1;
                high
            });
        }
        res.push(low + 1);
        res
    }
}
```

### **...**

```

```

<!-- tabs:end -->
