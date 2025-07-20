---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3616.Number%20of%20Student%20Replacements/README_EN.md
---

<!-- problem:start -->

# [3616. Number of Student Replacements 🔒](https://leetcode.com/problems/number-of-student-replacements)

[中文文档](/solution/3600-3699/3616.Number%20of%20Student%20Replacements/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>ranks</code> where <code>ranks[i]</code> represents the rank of the <code>i<sup>th</sup></code> student arriving <strong>in order</strong>. A lower number indicates a <strong>better</strong> rank.</p>

<p>Initially, the first student is <strong>selected</strong> by default.</p>

<p>A <strong>replacement</strong> occurs when a student with a <strong>strictly</strong> better rank arrives and <strong>replaces</strong> the current selection.</p>

<p>Return the total number of replacements made.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">ranks = [4,1,2]</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The first student with <code>ranks[0] = 4</code> is initially selected.</li>
	<li>The second student with <code>ranks[1] = 1</code> is better than the current selection, so a replacement occurs.</li>
	<li>The third student has a worse rank, so no replacement occurs.</li>
	<li>Thus, the number of replacements is 1.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">ranks = [2,2,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The first student with <code>ranks[0] = 2</code> is initially selected.</li>
	<li>Neither of <code>ranks[1] = 2</code> or <code>ranks[2] = 3</code> is better than the current selection.</li>
	<li>Thus, the number of replacements is 0.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= ranks.length &lt;= 10<sup>5</sup>​​​​​​​</code></li>
	<li><code>1 &lt;= ranks[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

We use a variable $\text{cur}$ to record the rank of the currently selected student. We iterate through the array $\text{ranks}$, and if we encounter a student with a better rank (i.e., $\text{ranks}[i] < \text{cur}$), we update $\text{cur}$ and increment the answer by one.

After the iteration, we return the answer.

The time complexity is $O(n)$, where $n$ is the number of students. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def totalReplacements(self, ranks: List[int]) -> int:
        ans, cur = 0, ranks[0]
        for x in ranks:
            if x < cur:
                cur = x
                ans += 1
        return ans
```

#### Java

```java
class Solution {
    public int totalReplacements(int[] ranks) {
        int ans = 0;
        int cur = ranks[0];
        for (int x : ranks) {
            if (x < cur) {
                cur = x;
                ++ans;
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
    int totalReplacements(vector<int>& ranks) {
        int ans = 0;
        int cur = ranks[0];
        for (int x : ranks) {
            if (x < cur) {
                cur = x;
                ++ans;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func totalReplacements(ranks []int) (ans int) {
	cur := ranks[0]
	for _, x := range ranks {
		if x < cur {
			cur = x
			ans++
		}
	}
	return
}
```

#### TypeScript

```ts
function totalReplacements(ranks: number[]): number {
    let [ans, cur] = [0, ranks[0]];
    for (const x of ranks) {
        if (x < cur) {
            cur = x;
            ans++;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
