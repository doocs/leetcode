---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3706.Maximum%20Distance%20Between%20Unequal%20Words%20in%20Array%20II/README.md
tags:
    - 数组
    - 字符串
---

<!-- problem:start -->

# [3706. 不同单词间的最大距离 II 🔒](https://leetcode.cn/problems/maximum-distance-between-unequal-words-in-array-ii)

[English Version](/solution/3700-3799/3706.Maximum%20Distance%20Between%20Unequal%20Words%20in%20Array%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个字符串数组 <code>words</code>。</p>

<p>找到两个&nbsp;<strong>不同&nbsp;</strong>下标 <code>i</code> 和 <code>j</code> 之间的&nbsp;<strong>最大距离&nbsp;</strong>，且满足以下条件：</p>

<ul>
	<li><code>words[i] != words[j]</code>，并且</li>
	<li>距离定义为 <code>j - i + 1</code>。</li>
</ul>

<p>返回所有满足条件的下标对中最大的距离。如果不存在有效的下标对，返回 0。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">words = ["leetcode","leetcode","codeforces"]</span></p>

<p><strong>输出：</strong> <span class="example-io">3</span></p>

<p><strong>解释：</strong></p>

<p>在此示例中，<code>words[0]</code> 和 <code>words[2]</code> 不相等，并且它们的最大距离为 <code>2 - 0 + 1 = 3</code>。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">words = ["a","b","c","a","a"]</span></p>

<p><strong>输出：</strong> <span class="example-io">4</span></p>

<p><strong>解释：</strong></p>

<p>在此示例中，<code>words[1]</code> 和 <code>words[4]</code> 的最大距离为 <code>4 - 1 + 1 = 4</code>。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">words = ["z","z","z"]</span></p>

<p><strong>输出：</strong> <span class="example-io">0</span></p>

<p><strong>解释：</strong></p>

<p>在此示例中，所有单词都相等，因此答案为 0。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= words[i].length &lt;= 10</code></li>
	<li><code>words[i]</code> 由小写英文字母组成。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：一次遍历

我们可以发现，最大距离的两个单词中至少有一个单词在数组的两端（即下标为 $0$ 或 $n - 1$）。否则，假设最大距离的两个单词分别在下标 $i$ 和 $j$ 处，即 $0 < i < j < n - 1$，那么单词 $\textit{words}[0]$ 和 $\textit{words}[j]$ 相同，而单词 $\textit{words}[n - 1]$ 和 $\textit{words}[i]$ 也相同（否则距离会更大），因此单词 $\textit{words}[0]$ 和 $\textit{words}[n - 1]$ 不同，且它们的距离 $n - 1 - 0 + 1 = n$ 一定大于 $j - i + 1$，与假设矛盾。因此，最大距离的两个单词中至少有一个单词在数组的两端。

所以，我们只需要遍历数组，计算每个单词与数组两端单词的距离，并更新最大距离。

时间复杂度 $O(n)$，其中 $n$ 是数组 $\textit{words}$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxDistance(self, words: List[str]) -> int:
        n = len(words)
        ans = 0
        for i in range(n):
            if words[i] != words[0]:
                ans = max(ans, i + 1)
            if words[i] != words[-1]:
                ans = max(ans, n - i)
        return ans
```

#### Java

```java
class Solution {
    public int maxDistance(String[] words) {
        int n = words.length;
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            if (!words[i].equals(words[0])) {
                ans = Math.max(ans, i + 1);
            }
            if (!words[i].equals(words[n - 1])) {
                ans = Math.max(ans, n - i);
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
    int maxDistance(vector<string>& words) {
        int n = words.size();
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            if (words[i] != words[0]) {
                ans = max(ans, i + 1);
            }
            if (words[i] != words[n - 1]) {
                ans = max(ans, n - i);
            }
        }
        return ans;
    }
};
```

#### Go

```go
func maxDistance(words []string) int {
	n := len(words)
	ans := 0
	for i := 0; i < n; i++ {
		if words[i] != words[0] {
			ans = max(ans, i+1)
		}
		if words[i] != words[n-1] {
			ans = max(ans, n-i)
		}
	}
	return ans
}
```

#### TypeScript

```ts
function maxDistance(words: string[]): number {
    const n = words.length;
    let ans = 0;
    for (let i = 0; i < n; i++) {
        if (words[i] !== words[0]) {
            ans = Math.max(ans, i + 1);
        }
        if (words[i] !== words[n - 1]) {
            ans = Math.max(ans, n - i);
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
