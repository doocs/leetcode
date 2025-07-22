---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3492.Maximum%20Containers%20on%20a%20Ship/README_EN.md
rating: 1140
source: Weekly Contest 442 Q1
tags:
    - Math
---

<!-- problem:start -->

# [3492. Maximum Containers on a Ship](https://leetcode.com/problems/maximum-containers-on-a-ship)

[中文文档](/solution/3400-3499/3492.Maximum%20Containers%20on%20a%20Ship/README.md)

## Description

<!-- description:start -->

<p>You are given a positive integer <code>n</code> representing an <code>n x n</code> cargo deck on a ship. Each cell on the deck can hold one container with a weight of <strong>exactly</strong> <code>w</code>.</p>

<p>However, the total weight of all containers, if loaded onto the deck, must not exceed the ship&#39;s maximum weight capacity, <code>maxWeight</code>.</p>

<p>Return the <strong>maximum</strong> number of containers that can be loaded onto the ship.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 2, w = 3, maxWeight = 15</span></p>

<p><strong>Output:</strong> 4</p>

<p><strong>Explanation: </strong></p>

<p>The deck has 4 cells, and each container weighs 3. The total weight of loading all containers is 12, which does not exceed <code>maxWeight</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">n = 3, w = 5, maxWeight = 20</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation: </strong></p>

<p>The deck has 9 cells, and each container weighs 5. The maximum number of containers that can be loaded without exceeding <code>maxWeight</code> is 4.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 1000</code></li>
	<li><code>1 &lt;= w &lt;= 1000</code></li>
	<li><code>1 &lt;= maxWeight &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Mathematics

First, we calculate the maximum weight the boat can carry, which is $n \times n \times w$. Then, we take the minimum of this value and $\text{maxWeight}$, and divide it by $w$.

The time complexity is $O(1)$, and the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxContainers(self, n: int, w: int, maxWeight: int) -> int:
        return min(n * n * w, maxWeight) // w
```

#### Java

```java
class Solution {
    public int maxContainers(int n, int w, int maxWeight) {
        return Math.min(n * n * w, maxWeight) / w;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxContainers(int n, int w, int maxWeight) {
        return min(n * n * w, maxWeight) / w;
    }
};
```

#### Go

```go
func maxContainers(n int, w int, maxWeight int) int {
	return min(n*n*w, maxWeight) / w
}
```

#### TypeScript

```ts
function maxContainers(n: number, w: number, maxWeight: number): number {
    return (Math.min(n * n * w, maxWeight) / w) | 0;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
