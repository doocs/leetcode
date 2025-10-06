---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3700-3799/3706.Maximum%20Distance%20Between%20Unequal%20Words%20in%20Array%20II/README_EN.md
---

<!-- problem:start -->

# [3706. Maximum Distance Between Unequal Words in Array II 🔒](https://leetcode.com/problems/maximum-distance-between-unequal-words-in-array-ii)

[中文文档](/solution/3700-3799/3706.Maximum%20Distance%20Between%20Unequal%20Words%20in%20Array%20II/README.md)

## Description

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

## Solutions

<!-- solution:start -->

### Solution: Single Pass

We can observe that at least one of the two words with maximum distance must be at either end of the array (i.e., at index $0$ or $n - 1$). Otherwise, suppose the two words with maximum distance are at indices $i$ and $j$ where $0 < i < j < n - 1$. Then $\textit{words}[0]$ must be the same as $\textit{words}[j]$, and $\textit{words}[n - 1]$ must be the same as $\textit{words}[i]$ (otherwise the distance would be greater). This means $\textit{words}[0]$ and $\textit{words}[n - 1]$ are different, and their distance $n - 1 - 0 + 1 = n$ is definitely greater than $j - i + 1$, which contradicts our assumption. Therefore, at least one of the two words with maximum distance must be at either end of the array.

So, we only need to traverse the array, calculate the distance between each word and the words at both ends of the array, and update the maximum distance.

The time complexity is $O(n)$, where $n$ is the length of array $\textit{words}$. The space complexity is $O(1)$.

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
