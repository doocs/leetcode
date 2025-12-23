---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3000-3099/3074.Apple%20Redistribution%20into%20Boxes/README_EN.md
rating: 1197
source: Weekly Contest 388 Q1
tags:
    - Greedy
    - Array
    - Sorting
---

<!-- problem:start -->

# [3074. Apple Redistribution into Boxes](https://leetcode.com/problems/apple-redistribution-into-boxes)

[中文文档](/solution/3000-3099/3074.Apple%20Redistribution%20into%20Boxes/README.md)

## Description

<!-- description:start -->

<p>You are given an array <code>apple</code> of size <code>n</code> and an array <code>capacity</code> of size <code>m</code>.</p>

<p>There are <code>n</code> packs where the <code>i<sup>th</sup></code> pack contains <code>apple[i]</code> apples. There are <code>m</code> boxes as well, and the <code>i<sup>th</sup></code> box has a capacity of <code>capacity[i]</code> apples.</p>

<p>Return <em>the <strong>minimum</strong> number of boxes you need to select to redistribute these </em><code>n</code><em> packs of apples into boxes</em>.</p>

<p><strong>Note</strong> that, apples from the same pack can be distributed into different boxes.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> apple = [1,3,2], capacity = [4,3,1,5,2]
<strong>Output:</strong> 2
<strong>Explanation:</strong> We will use boxes with capacities 4 and 5.
It is possible to distribute the apples as the total capacity is greater than or equal to the total number of apples.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> apple = [5,5,5], capacity = [2,4,2,7]
<strong>Output:</strong> 4
<strong>Explanation:</strong> We will need to use all the boxes.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == apple.length &lt;= 50</code></li>
	<li><code>1 &lt;= m == capacity.length &lt;= 50</code></li>
	<li><code>1 &lt;= apple[i], capacity[i] &lt;= 50</code></li>
	<li>The input is generated such that it&#39;s possible to redistribute packs of apples into boxes.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Greedy + Sorting

To minimize the number of boxes needed, we should prioritize using boxes with larger capacities. Therefore, we can sort the boxes in descending order of capacity, and then use the boxes one by one until all the apples are packed. We return the number of boxes used at this point.

The time complexity is $O(m \times \log m + n)$ and the space complexity is $O(\log m)$, where $m$ and $n$ are the lengths of the arrays $\textit{capacity}$ and $\textit{apple}$ respectively.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minimumBoxes(self, apple: List[int], capacity: List[int]) -> int:
        capacity.sort(reverse=True)
        s = sum(apple)
        for i, c in enumerate(capacity, 1):
            s -= c
            if s <= 0:
                return i
```

#### Java

```java
class Solution {
    public int minimumBoxes(int[] apple, int[] capacity) {
        Arrays.sort(capacity);
        int s = 0;
        for (int x : apple) {
            s += x;
        }
        for (int i = 1, n = capacity.length;; ++i) {
            s -= capacity[n - i];
            if (s <= 0) {
                return i;
            }
        }
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minimumBoxes(vector<int>& apple, vector<int>& capacity) {
        sort(capacity.rbegin(), capacity.rend());
        int s = accumulate(apple.begin(), apple.end(), 0);
        for (int i = 1;; ++i) {
            s -= capacity[i - 1];
            if (s <= 0) {
                return i;
            }
        }
    }
};
```

#### Go

```go
func minimumBoxes(apple []int, capacity []int) int {
	sort.Ints(capacity)
	s := 0
	for _, x := range apple {
		s += x
	}
	for i := 1; ; i++ {
		s -= capacity[len(capacity)-i]
		if s <= 0 {
			return i
		}
	}
}
```

#### TypeScript

```ts
function minimumBoxes(apple: number[], capacity: number[]): number {
    capacity.sort((a, b) => b - a);
    let s = apple.reduce((acc, cur) => acc + cur, 0);
    for (let i = 1; ; ++i) {
        s -= capacity[i - 1];
        if (s <= 0) {
            return i;
        }
    }
}
```

#### Rust

```rust
impl Solution {
    pub fn minimum_boxes(apple: Vec<i32>, mut capacity: Vec<i32>) -> i32 {
        capacity.sort();

        let mut s: i32 = apple.iter().sum();

        let n = capacity.len();
        let mut i = 1;
        loop {
            s -= capacity[n - i];
            if s <= 0 {
                return i as i32;
            }
            i += 1;
        }
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
