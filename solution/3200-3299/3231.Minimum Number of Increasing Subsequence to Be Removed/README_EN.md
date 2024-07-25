---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3231.Minimum%20Number%20of%20Increasing%20Subsequence%20to%20Be%20Removed/README_EN.md
---

<!-- problem:start -->

# [3231. Minimum Number of Increasing Subsequence to Be Removed 🔒](https://leetcode.com/problems/minimum-number-of-increasing-subsequence-to-be-removed)

[中文文档](/solution/3200-3299/3231.Minimum%20Number%20of%20Increasing%20Subsequence%20to%20Be%20Removed/README.md)

## Description

<!-- description:start -->

<p>Given an array of integers <code>nums</code>, you are allowed to perform the following operation any number of times:</p>

<ul>
	<li>Remove a <strong>strictly increasing</strong> <span data-keyword="subsequence-array">subsequence</span> from the array.</li>
</ul>

<p>Your task is to find the <strong>minimum</strong> number of operations required to make the array <strong>empty</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [5,3,1,4,2]</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p>We remove subsequences <code>[1, 2]</code>, <code>[3, 4]</code>, <code>[5]</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,3,4,5]</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [5,4,3,2,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">5</span></p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Greedy + Binary Search

We traverse the array $\textit{nums}$ from left to right. For each element $x$, we need to greedily append it after the last element of the preceding sequence that is smaller than $x$. If no such element is found, it means the current element $x$ is smaller than all elements in the preceding sequences, and we need to start a new sequence with $x$.

From this analysis, we can observe that the last elements of the preceding sequences are in a monotonically decreasing order. Therefore, we can use binary search to find the position of the first element in the preceding sequences that is smaller than $x$, and then place $x$ in that position.

Finally, we return the number of sequences.

The time complexity is $O(n \log n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the array $\textit{nums}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minOperations(self, nums: List[int]) -> int:
        g = []
        for x in nums:
            l, r = 0, len(g)
            while l < r:
                mid = (l + r) >> 1
                if g[mid] < x:
                    r = mid
                else:
                    l = mid + 1
            if l == len(g):
                g.append(x)
            else:
                g[l] = x
        return len(g)
```

#### Java

```java
class Solution {
    public int minOperations(int[] nums) {
        List<Integer> g = new ArrayList<>();
        for (int x : nums) {
            int l = 0, r = g.size();
            while (l < r) {
                int mid = (l + r) >> 1;
                if (g.get(mid) < x) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }
            if (l == g.size()) {
                g.add(x);
            } else {
                g.set(l, x);
            }
        }
        return g.size();
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minOperations(vector<int>& nums) {
        vector<int> g;
        for (int x : nums) {
            int l = 0, r = g.size();
            while (l < r) {
                int mid = (l + r) >> 1;
                if (g[mid] < x) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }
            if (l == g.size()) {
                g.push_back(x);
            } else {
                g[l] = x;
            }
        }
        return g.size();
    }
};
```

#### Go

```go
func minOperations(nums []int) int {
	g := []int{}
	for _, x := range nums {
		l, r := 0, len(g)
		for l < r {
			mid := (l + r) >> 1
			if g[mid] < x {
				r = mid
			} else {
				l = mid + 1
			}
		}
		if l == len(g) {
			g = append(g, x)
		} else {
			g[l] = x
		}
	}
	return len(g)
}
```

#### TypeScript

```ts
function minOperations(nums: number[]): number {
    const g: number[] = [];
    for (const x of nums) {
        let [l, r] = [0, g.length];
        while (l < r) {
            const mid = (l + r) >> 1;
            if (g[mid] < x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        if (l === g.length) {
            g.push(x);
        } else {
            g[l] = x;
        }
    }
    return g.length;
}
```

#### Rust

```rust
impl Solution {
    pub fn min_operations(nums: Vec<i32>) -> i32 {
        let mut g = Vec::new();
        for &x in nums.iter() {
            let mut l = 0;
            let mut r = g.len();
            while l < r {
                let mid = (l + r) / 2;
                if g[mid] < x {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }
            if l == g.len() {
                g.push(x);
            } else {
                g[l] = x;
            }
        }
        g.len() as i32
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
