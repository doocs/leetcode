---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2200-2299/2279.Maximum%20Bags%20With%20Full%20Capacity%20of%20Rocks/README_EN.md
rating: 1249
source: Weekly Contest 294 Q2
tags:
    - Greedy
    - Array
    - Sorting
---

<!-- problem:start -->

# [2279. Maximum Bags With Full Capacity of Rocks](https://leetcode.com/problems/maximum-bags-with-full-capacity-of-rocks)

[中文文档](/solution/2200-2299/2279.Maximum%20Bags%20With%20Full%20Capacity%20of%20Rocks/README.md)

## Description

<!-- description:start -->

<p>You have <code>n</code> bags numbered from <code>0</code> to <code>n - 1</code>. You are given two <strong>0-indexed</strong> integer arrays <code>capacity</code> and <code>rocks</code>. The <code>i<sup>th</sup></code> bag can hold a maximum of <code>capacity[i]</code> rocks and currently contains <code>rocks[i]</code> rocks. You are also given an integer <code>additionalRocks</code>, the number of additional rocks you can place in <strong>any</strong> of the bags.</p>

<p>Return<em> the <strong>maximum</strong> number of bags that could have full capacity after placing the additional rocks in some bags.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> capacity = [2,3,4,5], rocks = [1,2,4,4], additionalRocks = 2
<strong>Output:</strong> 3
<strong>Explanation:</strong>
Place 1 rock in bag 0 and 1 rock in bag 1.
The number of rocks in each bag are now [2,3,4,4].
Bags 0, 1, and 2 have full capacity.
There are 3 bags at full capacity, so we return 3.
It can be shown that it is not possible to have more than 3 bags at full capacity.
Note that there may be other ways of placing the rocks that result in an answer of 3.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> capacity = [10,2,2], rocks = [2,2,0], additionalRocks = 100
<strong>Output:</strong> 3
<strong>Explanation:</strong>
Place 8 rocks in bag 0 and 2 rocks in bag 2.
The number of rocks in each bag are now [10,2,2].
Bags 0, 1, and 2 have full capacity.
There are 3 bags at full capacity, so we return 3.
It can be shown that it is not possible to have more than 3 bags at full capacity.
Note that we did not use all of the additional rocks.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == capacity.length == rocks.length</code></li>
	<li><code>1 &lt;= n &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= capacity[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= rocks[i] &lt;= capacity[i]</code></li>
	<li><code>1 &lt;= additionalRocks &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Sorting + Greedy

First, we calculate the remaining capacity of each bag, then sort the remaining capacities. Next, we traverse the remaining capacities from smallest to largest, putting the extra stones into the bags until the extra stones are used up or the remaining capacities of the bags are exhausted. Finally, we return the number of bags at this point.

Time complexity is $O(n \times \log n)$, and space complexity is $O(\log n)$. Here, $n$ is the number of bags.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maximumBags(
        self, capacity: List[int], rocks: List[int], additionalRocks: int
    ) -> int:
        for i, x in enumerate(rocks):
            capacity[i] -= x
        capacity.sort()
        for i, x in enumerate(capacity):
            additionalRocks -= x
            if additionalRocks < 0:
                return i
        return len(capacity)
```

#### Java

```java
class Solution {
    public int maximumBags(int[] capacity, int[] rocks, int additionalRocks) {
        int n = rocks.length;
        for (int i = 0; i < n; ++i) {
            capacity[i] -= rocks[i];
        }
        Arrays.sort(capacity);
        for (int i = 0; i < n; ++i) {
            additionalRocks -= capacity[i];
            if (additionalRocks < 0) {
                return i;
            }
        }
        return n;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maximumBags(vector<int>& capacity, vector<int>& rocks, int additionalRocks) {
        int n = rocks.size();
        for (int i = 0; i < n; ++i) {
            capacity[i] -= rocks[i];
        }
        ranges::sort(capacity);
        for (int i = 0; i < n; ++i) {
            additionalRocks -= capacity[i];
            if (additionalRocks < 0) {
                return i;
            }
        }
        return n;
    }
};
```

#### Go

```go
func maximumBags(capacity []int, rocks []int, additionalRocks int) int {
	for i, x := range rocks {
		capacity[i] -= x
	}
	sort.Ints(capacity)
	for i, x := range capacity {
		additionalRocks -= x
		if additionalRocks < 0 {
			return i
		}
	}
	return len(capacity)
}
```

#### TypeScript

```ts
function maximumBags(capacity: number[], rocks: number[], additionalRocks: number): number {
    const n = rocks.length;
    for (let i = 0; i < n; ++i) {
        capacity[i] -= rocks[i];
    }
    capacity.sort((a, b) => a - b);
    for (let i = 0; i < n; ++i) {
        additionalRocks -= capacity[i];
        if (additionalRocks < 0) {
            return i;
        }
    }
    return n;
}
```

#### Rust

```rust
impl Solution {
    pub fn maximum_bags(mut capacity: Vec<i32>, rocks: Vec<i32>, mut additional_rocks: i32) -> i32 {
        for i in 0..rocks.len() {
            capacity[i] -= rocks[i];
        }
        capacity.sort();
        for i in 0..capacity.len() {
            additional_rocks -= capacity[i];
            if additional_rocks < 0 {
                return i as i32;
            }
        }
        capacity.len() as i32
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
