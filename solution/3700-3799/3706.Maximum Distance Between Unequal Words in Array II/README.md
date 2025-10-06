---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3706.Maximum%20Distance%20Between%20Unequal%20Words%20in%20Array%20II/README.md
---

<!-- problem:start -->

# [3706. Maximum Distance Between Unequal Words in Array II 🔒](https://leetcode.cn/problems/maximum-distance-between-unequal-words-in-array-ii)

[English Version](/solution/3700-3799/3706.Maximum%20Distance%20Between%20Unequal%20Words%20in%20Array%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>You are given a string array <code>words</code>.</p>

<p>Find the <strong>maximum distance</strong> between two <strong>distinct</strong> indices <code>i</code> and <code>j</code> such that:</p>

<ul>
	<li><code>words[i] != words[j]</code>, and</li>
	<li>the distance is defined as <code>j - i + 1</code>.</li>
</ul>

<p>Return the maximum distance among all such pairs. If no valid pair exists, return 0.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">words = [&quot;leetcode&quot;,&quot;leetcode&quot;,&quot;codeforces&quot;]</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p>In this example, <code>words[0]</code> and <code>words[2]</code> are not equal, and they have the maximum distance <code>2 - 0 + 1 = 3</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">words = [&quot;a&quot;,&quot;b&quot;,&quot;c&quot;,&quot;a&quot;,&quot;a&quot;]</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<p>In this example <code>words[1]</code> and <code>words[4]</code> have the largest distance of <code>4 - 1 + 1 = 4</code>.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">words = [&quot;z&quot;,&quot;z&quot;,&quot;z&quot;]</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p><strong>​​​​​​​</strong>In this example all the words are equal, thus the answer is 0.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= words.length &lt;= 10<sup>5</sup></code><span style="display: none;"> </span></li>
	<li><code>1 &lt;= words[i].length &lt;= 10</code></li>
	<li><code>words[i]</code> consists of lowercase English letters.</li>
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
