---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3231.Minimum%20Number%20of%20Increasing%20Subsequence%20to%20Be%20Removed/README.md
---

<!-- problem:start -->

# [3231. Minimum Number of Increasing Subsequence to Be Removed 🔒](https://leetcode.cn/problems/minimum-number-of-increasing-subsequence-to-be-removed)

[English Version](/solution/3200-3299/3231.Minimum%20Number%20of%20Increasing%20Subsequence%20to%20Be%20Removed/README_EN.md)

## 题目描述

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

## 解法

<!-- solution:start -->

### 方法一：贪心 + 二分查找

我们从左到右遍历数组 $\textit{nums}$，对于每个元素 $x$，我们需要贪心地将其追加到前面序列中最后一个元素小于 $x$ 的最大值后面。如果找不到这样的元素，则说明当前元素 $x$ 比前面序列中的所有元素都小，我们需要新开辟一个序列，将 $x$ 放入其中。

这样分析下来，我们可以发现，前面序列中的最后一个元素呈单调递减的状态。因此，我们可以使用二分查找来找到前面序列中最后一个元素小于 $x$ 的第一个元素位置，然后将 $x$ 放入该位置。

最终，我们返回序列的个数即可。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $\textit{nums}$ 的长度。

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
