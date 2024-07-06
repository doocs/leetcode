---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0900-0999/0942.DI%20String%20Match/README.md
tags:
    - 贪心
    - 数组
    - 双指针
    - 字符串
---

<!-- problem:start -->

# [942. 增减字符串匹配](https://leetcode.cn/problems/di-string-match)

[English Version](/solution/0900-0999/0942.DI%20String%20Match/README_EN.md)

## 题目描述

<!-- description:start -->

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

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：贪心

我们可以使用两个指针 `low` 和 `high` 分别表示当前的最小值和最大值，然后遍历字符串 `s`，如果当前字符是 `I`，那么我们就将 `low` 加入到结果数组中，并且 `low` 自增 1；如果当前字符是 `D`，那么我们就将 `high` 加入到结果数组中，并且 `high` 自减 1。

最后，我们将 `low` 加入到结果数组中，返回结果数组即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为字符串 `s` 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def diStringMatch(self, s: str) -> List[int]:
        low, high = 0, len(s)
        ans = []
        for c in s:
            if c == "I":
                ans.append(low)
                low += 1
            else:
                ans.append(high)
                high -= 1
        ans.append(low)
        return ans
```

#### Java

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

#### C++

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

#### Go

```go
func diStringMatch(s string) (ans []int) {
	low, high := 0, len(s)
	for _, c := range s {
		if c == 'I' {
			ans = append(ans, low)
			low++
		} else {
			ans = append(ans, high)
			high--
		}
	}
	ans = append(ans, low)
	return
}
```

#### TypeScript

```ts
function diStringMatch(s: string): number[] {
    const ans: number[] = [];
    let [low, high] = [0, s.length];
    for (const c of s) {
        if (c === 'I') {
            ans.push(low++);
        } else {
            ans.push(high--);
        }
    }
    ans.push(low);
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn di_string_match(s: String) -> Vec<i32> {
        let mut low = 0;
        let mut high = s.len() as i32;
        let mut ans = Vec::with_capacity(s.len() + 1);

        for c in s.chars() {
            if c == 'I' {
                ans.push(low);
                low += 1;
            } else {
                ans.push(high);
                high -= 1;
            }
        }

        ans.push(low);
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
