---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3000-3099/3075.Maximize%20Happiness%20of%20Selected%20Children/README_EN.md
rating: 1325
source: Weekly Contest 388 Q2
tags:
    - Greedy
    - Array
    - Sorting
---

<!-- problem:start -->

# [3075. Maximize Happiness of Selected Children](https://leetcode.com/problems/maximize-happiness-of-selected-children)

[中文文档](/solution/3000-3099/3075.Maximize%20Happiness%20of%20Selected%20Children/README.md)

## Description

<!-- description:start -->

<p>You are given an array <code>happiness</code> of length <code>n</code>, and a <strong>positive</strong> integer <code>k</code>.</p>

<p>There are <code>n</code> children standing in a queue, where the <code>i<sup>th</sup></code> child has <strong>happiness value</strong> <code>happiness[i]</code>. You want to select <code>k</code> children from these <code>n</code> children in <code>k</code> turns.</p>

<p>In each turn, when you select a child, the <strong>happiness value</strong> of all the children that have <strong>not</strong> been selected till now decreases by <code>1</code>. Note that the happiness value <strong>cannot</strong> become negative and gets decremented <strong>only</strong> if it is positive.</p>

<p>Return <em>the <strong>maximum</strong> sum of the happiness values of the selected children you can achieve by selecting </em><code>k</code> <em>children</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> happiness = [1,2,3], k = 2
<strong>Output:</strong> 4
<strong>Explanation:</strong> We can pick 2 children in the following way:
- Pick the child with the happiness value == 3. The happiness value of the remaining children becomes [0,1].
- Pick the child with the happiness value == 1. The happiness value of the remaining child becomes [0]. Note that the happiness value cannot become less than 0.
The sum of the happiness values of the selected children is 3 + 1 = 4.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> happiness = [1,1,1,1], k = 2
<strong>Output:</strong> 1
<strong>Explanation:</strong> We can pick 2 children in the following way:
- Pick any child with the happiness value == 1. The happiness value of the remaining children becomes [0,0,0].
- Pick the child with the happiness value == 0. The happiness value of the remaining child becomes [0,0].
The sum of the happiness values of the selected children is 1 + 0 = 1.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> happiness = [2,3,4,5], k = 1
<strong>Output:</strong> 5
<strong>Explanation:</strong> We can pick 1 child in the following way:
- Pick the child with the happiness value == 5. The happiness value of the remaining children becomes [1,2,3].
The sum of the happiness values of the selected children is 5.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == happiness.length &lt;= 2 * 10<sup>5</sup></code></li>
	<li><code>1 &lt;= happiness[i] &lt;= 10<sup>8</sup></code></li>
	<li><code>1 &lt;= k &lt;= n</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Greedy + Sorting

To maximize the sum of happiness values, we should prioritize selecting children with higher happiness values. Therefore, we can sort the children in descending order by happiness value, and then select $k$ children in sequence. For the current $i$-th child, the happiness value obtained is $\max(\textit{happiness}[i] - i, 0)$. Finally, return the sum of happiness values of these $k$ children.

The time complexity is $O(n \times \log n + k)$, and the space complexity is $O(\log n)$, where $n$ is the length of the array $\textit{happiness}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maximumHappinessSum(self, happiness: List[int], k: int) -> int:
        happiness.sort(reverse=True)
        ans = 0
        for i, x in enumerate(happiness[:k]):
            x -= i
            ans += max(x, 0)
        return ans
```

#### Java

```java
class Solution {
    public long maximumHappinessSum(int[] happiness, int k) {
        Arrays.sort(happiness);
        long ans = 0;
        for (int i = 0, n = happiness.length; i < k; ++i) {
            int x = happiness[n - i - 1] - i;
            ans += Math.max(x, 0);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long maximumHappinessSum(vector<int>& happiness, int k) {
        sort(happiness.rbegin(), happiness.rend());
        long long ans = 0;
        for (int i = 0, n = happiness.size(); i < k; ++i) {
            int x = happiness[i] - i;
            ans += max(x, 0);
        }
        return ans;
    }
};
```

#### Go

```go
func maximumHappinessSum(happiness []int, k int) (ans int64) {
	sort.Ints(happiness)
	for i := 0; i < k; i++ {
		x := happiness[len(happiness)-i-1] - i
		ans += int64(max(x, 0))
	}
	return
}
```

#### TypeScript

```ts
function maximumHappinessSum(happiness: number[], k: number): number {
    happiness.sort((a, b) => b - a);
    let ans = 0;
    for (let i = 0; i < k; ++i) {
        const x = happiness[i] - i;
        ans += Math.max(x, 0);
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn maximum_happiness_sum(mut happiness: Vec<i32>, k: i32) -> i64 {
        happiness.sort_unstable_by(|a, b| b.cmp(a));

        let mut ans: i64 = 0;
        for i in 0..(k as usize) {
            let x = happiness[i] as i64 - i as i64;
            if x > 0 {
                ans += x;
            }
        }
        ans
    }
}
```

#### C#

```cs
public class Solution {
    public long MaximumHappinessSum(int[] happiness, int k) {
        Array.Sort(happiness, (a, b) => b.CompareTo(a));
        long ans = 0;
        for (int i = 0; i < k; i++) {
            int x = happiness[i] - i;
            if (x <= 0) {
                break;
            }
            ans += x;
        }
        return ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
