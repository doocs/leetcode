---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0600-0699/0696.Count%20Binary%20Substrings/README.md
tags:
    - 双指针
    - 字符串
---

<!-- problem:start -->

# [696. 计数二进制子串](https://leetcode.cn/problems/count-binary-substrings)

[English Version](/solution/0600-0699/0696.Count%20Binary%20Substrings/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个字符串&nbsp;<code>s</code>，统计并返回具有相同数量 <code>0</code> 和 <code>1</code> 的非空（连续）子字符串的数量，并且这些子字符串中的所有 <code>0</code> 和所有 <code>1</code> 都是成组连续的。</p>

<p>重复出现（不同位置）的子串也要统计它们出现的次数。</p>
&nbsp;

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "00110011"
<strong>输出：</strong>6
<strong>解释：</strong>6 个子串满足具有相同数量的连续 1 和 0 ："0011"、"01"、"1100"、"10"、"0011" 和 "01" 。
注意，一些重复出现的子串（不同位置）要统计它们出现的次数。
另外，"00110011" 不是有效的子串，因为所有的 0（还有 1 ）没有组合在一起。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "10101"
<strong>输出：</strong>4
<strong>解释：</strong>有 4 个子串："10"、"01"、"10"、"01" ，具有相同数量的连续 1 和 0 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s[i]</code> 为 <code>'0'</code> 或 <code>'1'</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：遍历计数

我们可以遍历字符串 $s$，用一个变量 $\textit{pre}$ 记录上一个连续字符的数量，另一个变量 $\textit{cur}$ 记录当前连续字符的数量。那么以当前字符结尾的满足条件的子串数量为 $\min(\textit{pre}, \textit{cur})$。我们将 $\min(\textit{pre}, \textit{cur})$ 累加到答案中，并将 $\textit{cur}$ 的值赋给 $\textit{pre}$，继续遍历字符串 $s$ 直到结束。

时间复杂度 $O(n)$，其中 $n$ 是字符串 $s$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countBinarySubstrings(self, s: str) -> int:
        n = len(s)
        ans = i = 0
        pre = 0
        while i < n:
            j = i + 1
            while j < n and s[j] == s[i]:
                j += 1
            cur = j - i
            ans += min(pre, cur)
            pre = cur
            i = j
        return ans
```

#### Java

```java
class Solution {
    public int countBinarySubstrings(String s) {
        int n = s.length();
        int ans = 0;
        int i = 0;
        int pre = 0;
        while (i < n) {
            int j = i + 1;
            while (j < n && s.charAt(j) == s.charAt(i)) {
                j++;
            }
            int cur = j - i;
            ans += Math.min(pre, cur);
            pre = cur;
            i = j;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int countBinarySubstrings(string s) {
        int n = s.size();
        int ans = 0;
        int i = 0;
        int pre = 0;
        while (i < n) {
            int j = i + 1;
            while (j < n && s[j] == s[i]) {
                ++j;
            }
            int cur = j - i;
            ans += min(pre, cur);
            pre = cur;
            i = j;
        }
        return ans;
    }
};
```

#### Go

```go
func countBinarySubstrings(s string) (ans int) {
	n := len(s)
	i := 0
	pre := 0
	for i < n {
		j := i + 1
		for j < n && s[j] == s[i] {
			j++
		}
		cur := j - i
		ans += min(pre, cur)
		pre = cur
		i = j
	}
	return
}
```

#### TypeScript

```ts
function countBinarySubstrings(s: string): number {
    const n = s.length;
    let ans = 0;
    let i = 0;
    let pre = 0;

    while (i < n) {
        let j = i + 1;
        while (j < n && s[j] === s[i]) {
            j++;
        }
        const cur = j - i;
        ans += Math.min(pre, cur);
        pre = cur;
        i = j;
    }

    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn count_binary_substrings(s: String) -> i32 {
        let bytes = s.as_bytes();
        let n: usize = bytes.len();

        let mut ans: i32 = 0;
        let mut i: usize = 0;
        let mut pre: i32 = 0;

        while i < n {
            let mut j: usize = i + 1;
            while j < n && bytes[j] == bytes[i] {
                j += 1;
            }
            let cur: i32 = (j - i) as i32;
            ans += pre.min(cur);
            pre = cur;
            i = j;
        }

        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
