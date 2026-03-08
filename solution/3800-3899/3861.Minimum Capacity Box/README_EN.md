---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3800-3899/3861.Minimum%20Capacity%20Box/README_EN.md
---

<!-- problem:start -->

# [3861. Minimum Capacity Box](https://leetcode.com/problems/minimum-capacity-box)

[中文文档](/solution/3800-3899/3861.Minimum%20Capacity%20Box/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>capacity</code>, where <code>capacity[i]</code> represents the capacity of the <code>i<sup>th</sup></code> box, and an integer <code>itemSize</code> representing the size of an item.</p>

<p>The <code>i<sup>th</sup></code> box can store the item if <code>capacity[i] &gt;= itemSize</code>.</p>

<p>Return an integer denoting the index of the box with the <strong>minimum</strong> capacity that can store the item. If multiple such boxes exist, return the <strong>smallest index</strong>.</p>

<p>If no box can store the item, return -1.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">capacity = [1,5,3,7], itemSize = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>The box at index 2 has a capacity of 3, which is the minimum capacity that can store the item. Thus, the answer is 2.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">capacity = [3,5,4,3], itemSize = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p>The minimum capacity that can store the item is 3, and it appears at indices 0 and 3. Thus, the answer is 0.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">capacity = [4], itemSize = 5</span></p>

<p><strong>Output:</strong> <span class="example-io">-1</span></p>

<p><strong>Explanation:</strong></p>

<p>No box has enough capacity to store the item, so the answer is -1.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= capacity.length &lt;= 100</code></li>
	<li><code>1 &lt;= capacity[i] &lt;= 100</code></li>
	<li><code>1 &lt;= itemSize &lt;= 100</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Single Pass

We initialize a variable $\textit{ans}$ to represent the index of the box with the smallest capacity that can hold the item, with an initial value of $-1$. We iterate over the array $\textit{capacity}$, and for each box, if its capacity is greater than or equal to $\textit{itemSize}$, it can hold the item. At this point, we check whether it is the smallest-capacity box found so far; if so, we update $\textit{ans}$. Finally, we return $\textit{ans}$.

The time complexity is $O(n)$, where $n$ is the length of the array $\textit{capacity}$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minimumIndex(self, capacity: list[int], itemSize: int) -> int:
        ans = -1
        for i, x in enumerate(capacity):
            if x >= itemSize and (ans == -1 or x < capacity[ans]):
                ans = i
        return ans
```

#### Java

```java
class Solution {
    public int minimumIndex(int[] capacity, int itemSize) {
        int ans = -1;
        for (int i = 0; i < capacity.length; ++i) {
            int x = capacity[i];
            if (x >= itemSize && (ans == -1 || x < capacity[ans])) {
                ans = i;
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
    int minimumIndex(vector<int>& capacity, int itemSize) {
        int ans = -1;
        for (int i = 0; i < capacity.size(); ++i) {
            int x = capacity[i];
            if (x >= itemSize && (ans == -1 || x < capacity[ans])) {
                ans = i;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func minimumIndex(capacity []int, itemSize int) int {
	ans := -1
	for i, x := range capacity {
		if x >= itemSize && (ans == -1 || x < capacity[ans]) {
			ans = i
		}
	}
	return ans
}
```

#### TypeScript

```ts
function minimumIndex(capacity: number[], itemSize: number): number {
    let ans = -1;
    for (let i = 0; i < capacity.length; ++i) {
        const x = capacity[i];
        if (x >= itemSize && (ans === -1 || x < capacity[ans])) {
            ans = i;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
