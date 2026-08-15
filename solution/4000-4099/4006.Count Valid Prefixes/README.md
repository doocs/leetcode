---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/4000-4099/4006.Count%20Valid%20Prefixes/README.md
rating: 1242
source: 第 188 场双周赛 Q1
---

<!-- problem:start -->

# [4006. 统计有效前缀数目](https://leetcode.cn/problems/count-valid-prefixes)

[English Version](/solution/4000-4099/4006.Count%20Valid%20Prefixes/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个 <strong>二进制</strong> 字符串 <code>s</code>。</p>

<p>如果 <code>s</code> 的某个 <strong>前缀</strong> 的字符可以重新排列成一个 <strong>交替</strong> 字符串，那么该前缀被认为是 <strong>有效</strong> 的。</p>

<p>返回 <code>s</code> 中有效前缀的数量。</p>

<p><strong>二进制</strong> 字符串是仅由 <code>'0'</code> 和 <code>'1'</code> 组成的字符串。</p>

<p>字符串的 <strong>前缀</strong> 是指从字符串的开头开始并延伸到其内任意点的 <strong>子字符串</strong>。</p>

<p><strong>子字符串</strong> 是字符串中连续且 <b>非空</b> 的字符序列。</p>

<p>如果一个字符串中没有两个相邻字符相等，那么它被认为是 <strong>交替</strong> 的。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">s = "00101"</span></p>

<p><strong>输出：</strong> <span class="example-io">3</span></p>

<p><strong>解释：</strong></p>

<p>有效的前缀是：</p>

<ul>
	<li><code>"0"</code>：它已经是一个交替字符串。</li>
	<li><code>"001"</code>：可以被重新排列成 <code>"010"</code>，这是一个交替字符串。</li>
	<li><code>"00101"</code>：可以被重新排列成 <code>"01010"</code>，这是一个交替字符串。</li>
</ul>

<p>因此，答案是 3。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">s = "101"</span></p>

<p><strong>输出：</strong> <span class="example-io">3</span></p>

<p><strong>解释：</strong></p>

<p><code>s = "101"</code> 的所有前缀都已经是交替字符串。因此，答案是 3。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 100</code></li>
	<li><code>s</code> 仅由 <code>'0'</code> 和 <code>'1'</code> 组成。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：计数

一个字符串能够重新排列成交替字符串，当且仅当其中 `'0'` 和 `'1'` 的数量之差不超过 $1$。

因此，我们遍历字符串 $s$，用一个变量 $t$ 维护当前前缀中 `'1'` 的个数减去 `'0'` 的个数（遇到 `'1'` 时加一，遇到 `'0'` 时减一）。如果 $|t| \leq 1$，说明当前前缀是有效的，答案加一。

时间复杂度 $O(n)$，其中 $n$ 为字符串 $s$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countValidPrefixes(self, s: str) -> int:
        ans = t = 0
        for c in s:
            t += 1 if c == '1' else -1
            ans += 1 if abs(t) <= 1 else 0
        return ans
```

#### Java

```java
class Solution {
    public int countValidPrefixes(String s) {
        int ans = 0, t = 0;
        for (char c : s.toCharArray()) {
            t += c == '1' ? 1 : -1;
            if (Math.abs(t) <= 1) {
                ans++;
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int countValidPrefixes(string s) {
        int ans = 0, t = 0;
        for (char c : s) {
            t += c == '1' ? 1 : -1;
            if (abs(t) <= 1) {
                ans++;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func countValidPrefixes(s string) int {
	ans, t := 0, 0
	for _, c := range s {
		if c == '1' {
			t++
		} else {
			t--
		}
		if t >= -1 && t <= 1 {
			ans++
		}
	}
	return ans
}
```

#### TypeScript

```ts
function countValidPrefixes(s: string): number {
    let ans = 0;
    let t = 0;
    for (const c of s) {
        t += c === '1' ? 1 : -1;
        if (Math.abs(t) <= 1) {
            ans++;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
