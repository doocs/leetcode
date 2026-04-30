---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3800-3899/3884.First%20Matching%20Character%20From%20Both%20Ends/README.md
rating: 1161
source: 第 495 场周赛 Q1
tags:
    - 双指针
    - 字符串
---

<!-- problem:start -->

# [3884. 双端字符匹配](https://leetcode.cn/problems/first-matching-character-from-both-ends)

[English Version](/solution/3800-3899/3884.First%20Matching%20Character%20From%20Both%20Ends/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个长度为 <code>n</code> 的字符串 <code>s</code>，其中只包含小写英文字母。</p>

<p>返回最小的下标 <code>i</code>，使得 <code>s[i] == s[n - i - 1]</code>。</p>

<p>如果不存在这样的下标，返回 <code>-1</code>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">s = "abcacbd"</span></p>

<p><strong>输出：</strong> <span class="example-io">1</span></p>

<p><strong>解释：</strong></p>

<p>在下标 <code>i = 1</code> 处，<code>s[1]</code> 和 <code>s[5]</code> 的值均为 <code>'b'</code>。</p>

<p>没有更小的下标满足条件，因此答案是 1。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">s = "abc"</span></p>

<p><strong>输出：</strong> <span class="example-io">1</span></p>

<p><strong>解释：</strong></p>

<p>​​​​​​​在下标 <code>i = 1</code> 处，左右对应位置重合，因此字符均为 <code>'b'</code>。</p>

<p>没有更小的下标满足条件，因此答案是 1。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">s = "abcdab"</span></p>

<p><strong>输出：</strong> <span class="example-io">-1</span></p>

<p><strong>解释：</strong></p>

<p>​​​​​​​对于每个下标 <code>i</code>，位置 <code>i</code> 和 <code>n - i - 1</code> 的字符均不相同。</p>

<p>因此，不存在有效下标，答案是 <code>-1</code>。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n == s.length &lt;= 100</code></li>
	<li><code>s</code> 仅包含小写英文字母。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

我们遍历字符串 $s$ 的前半部分，对于每个下标 $i$，我们比较位置 $i$ 和位置 $n - i - 1$ 的字符是否相同。如果相同，我们返回下标 $i$。如果遍历结束后没有找到满足条件的下标，我们返回 -1。

时间复杂度 $O(n)$，其中 $n$ 是字符串 $s$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def firstMatchingIndex(self, s: str) -> int:
        for i in range(len(s) // 2 + 1):
            if s[i] == s[-i - 1]:
                return i
        return -1
```

#### Java

```java
class Solution {
    public int firstMatchingIndex(String s) {
        int n = s.length();
        for (int i = 0; i < n / 2 + 1; ++i) {
            if (s.charAt(i) == s.charAt(n - i - 1)) {
                return i;
            }
        }
        return -1;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int firstMatchingIndex(string s) {
        int n = s.size();
        for (int i = 0; i < n / 2 + 1; ++i) {
            if (s[i] == s[n - i - 1]) {
                return i;
            }
        }
        return -1;
    }
};
```

#### Go

```go
func firstMatchingIndex(s string) int {
	n := len(s)
	for i := 0; i < n/2+1; i++ {
		if s[i] == s[n-i-1] {
			return i
		}
	}
	return -1
}
```

#### TypeScript

```ts
function firstMatchingIndex(s: string): number {
    const n = s.length;
    for (let i = 0; i < Math.floor(n / 2) + 1; i++) {
        if (s[i] === s[n - i - 1]) {
            return i;
        }
    }
    return -1;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
