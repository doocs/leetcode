---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1500-1599/1528.Shuffle%20String/README.md
rating: 1193
source: 第 199 场周赛 Q1
tags:
    - 数组
    - 字符串
---

<!-- problem:start -->

# [1528. 重新排列字符串](https://leetcode.cn/problems/shuffle-string)

[English Version](/solution/1500-1599/1528.Shuffle%20String/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个字符串 <code>s</code> 和一个 <strong>长度相同</strong> 的整数数组 <code>indices</code> 。</p>

<p>请你重新排列字符串 <code>s</code> ，其中第 <code>i</code> 个字符需要移动到 <code>indices[i]</code> 指示的位置。</p>

<p>返回重新排列后的字符串。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1528.Shuffle%20String/images/q1.jpg" /></p>

<pre>
<strong>输入：</strong>s = "codeleet", <code>indices</code> = [4,5,6,7,0,2,1,3]
<strong>输出：</strong>"leetcode"
<strong>解释：</strong>如图所示，"codeleet" 重新排列后变为 "leetcode" 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "abc", <code>indices</code> = [0,1,2]
<strong>输出：</strong>"abc"
<strong>解释：</strong>重新排列后，每个字符都还留在原来的位置上。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>s.length == indices.length == n</code></li>
	<li><code>1 &lt;= n &lt;= 100</code></li>
	<li><code>s</code> 仅包含小写英文字母</li>
	<li><code>0 &lt;= indices[i] &lt;&nbsp;n</code></li>
	<li><code>indices</code> 的所有的值都是 <strong>唯一</strong> 的</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

我们创建一个与字符串长度相同的字符数组或字符串 $\textit{ans}$，然后遍历字符串 $\textit{s}$，将每个字符 $\textit{s}[i]$ 放置到 $\textit{ans}$ 的 $\textit{indices}[i]$ 位置上。最后将字符数组或字符串 $\textit{ans}$ 拼接成最终结果并返回。

时间复杂度 $O(n)$，空间复杂度 $O(n)$，其中 $n$ 是字符串的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def restoreString(self, s: str, indices: List[int]) -> str:
        ans = [None] * len(s)
        for c, j in zip(s, indices):
            ans[j] = c
        return "".join(ans)
```

#### Java

```java
class Solution {
    public String restoreString(String s, int[] indices) {
        int n = s.length();
        char[] ans = new char[n];
        for (int i = 0; i < n; ++i) {
            ans[indices[i]] = s.charAt(i);
        }
        return new String(ans);
    }
}
```

#### C++

```cpp
class Solution {
public:
    string restoreString(string s, vector<int>& indices) {
        int n = s.size();
        string ans(n, 0);
        for (int i = 0; i < n; ++i) {
            ans[indices[i]] = s[i];
        }
        return ans;
    }
};
```

#### Go

```go
func restoreString(s string, indices []int) string {
	ans := make([]rune, len(s))
	for i, c := range s {
		ans[indices[i]] = c
	}
	return string(ans)
}
```

#### TypeScript

```ts
function restoreString(s: string, indices: number[]): string {
    const ans: string[] = [];
    for (let i = 0; i < s.length; i++) {
        ans[indices[i]] = s[i];
    }
    return ans.join('');
}
```

#### Rust

```rust
impl Solution {
    pub fn restore_string(s: String, indices: Vec<i32>) -> String {
        let n = s.len();
        let mut ans = vec![' '; n];
        let chars: Vec<char> = s.chars().collect();
        for i in 0..n {
            ans[indices[i] as usize] = chars[i];
        }
        ans.iter().collect()
    }
}
```

#### JavaScript

```js
/**
 * @param {string} s
 * @param {number[]} indices
 * @return {string}
 */
var restoreString = function (s, indices) {
    const ans = [];
    for (let i = 0; i < s.length; i++) {
        ans[indices[i]] = s[i];
    }
    return ans.join('');
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
