---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1000-1099/1014.Best%20Sightseeing%20Pair/README_EN.md
rating: 1730
source: Weekly Contest 129 Q3
tags:
    - Array
    - Dynamic Programming
---

<!-- problem:start -->

# [1014. Best Sightseeing Pair](https://leetcode.com/problems/best-sightseeing-pair)

[中文文档](/solution/1000-1099/1014.Best%20Sightseeing%20Pair/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>values</code> where values[i] represents the value of the <code>i<sup>th</sup></code> sightseeing spot. Two sightseeing spots <code>i</code> and <code>j</code> have a <strong>distance</strong> <code>j - i</code> between them.</p>

<p>The score of a pair (<code>i &lt; j</code>) of sightseeing spots is <code>values[i] + values[j] + i - j</code>: the sum of the values of the sightseeing spots, minus the distance between them.</p>

<p>Return <em>the maximum score of a pair of sightseeing spots</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> values = [8,1,5,2,6]
<strong>Output:</strong> 11
<strong>Explanation:</strong> i = 0, j = 2, values[i] + values[j] + i - j = 8 + 5 + 0 - 2 = 11
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> values = [1,2]
<strong>Output:</strong> 2
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= values.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= values[i] &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Enumeration

We can enumerate $j$ from left to right while maintaining the maximum value of $values[i] + i$ for elements to the left of $j$, denoted as $mx$. For each $j$, the maximum score is $mx + values[j] - j$. The answer is the maximum of these maximum scores for all positions.

The time complexity is $O(n)$, where $n$ is the length of the array $\textit{values}$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxScoreSightseeingPair(self, values: List[int]) -> int:
        ans = mx = 0
        for j, x in enumerate(values):
            ans = max(ans, mx + x - j)
            mx = max(mx, x + j)
        return ans
```

#### Java

```java
class Solution {
    public int maxScoreSightseeingPair(int[] values) {
        int ans = 0, mx = 0;
        for (int j = 0; j < values.length; ++j) {
            ans = Math.max(ans, mx + values[j] - j);
            mx = Math.max(mx, values[j] + j);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxScoreSightseeingPair(vector<int>& values) {
        int ans = 0, mx = 0;
        for (int j = 0; j < values.size(); ++j) {
            ans = max(ans, mx + values[j] - j);
            mx = max(mx, values[j] + j);
        }
        return ans;
    }
};
```

#### Go

```go
func maxScoreSightseeingPair(values []int) (ans int) {
	mx := 0
	for j, x := range values {
		ans = max(ans, mx+x-j)
		mx = max(mx, x+j)
	}
	return
}
```

#### TypeScript

```ts
function maxScoreSightseeingPair(values: number[]): number {
    let [ans, mx] = [0, 0];
    for (let j = 0; j < values.length; ++j) {
        ans = Math.max(ans, mx + values[j] - j);
        mx = Math.max(mx, values[j] + j);
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn max_score_sightseeing_pair(values: Vec<i32>) -> i32 {
        let mut ans = 0;
        let mut mx = 0;
        for (j, &x) in values.iter().enumerate() {
            ans = ans.max(mx + x - j as i32);
            mx = mx.max(x + j as i32);
        }
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
