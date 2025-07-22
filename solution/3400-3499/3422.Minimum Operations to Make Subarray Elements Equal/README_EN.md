---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3422.Minimum%20Operations%20to%20Make%20Subarray%20Elements%20Equal/README_EN.md
tags:
    - Array
    - Hash Table
    - Math
    - Sliding Window
    - Heap (Priority Queue)
---

<!-- problem:start -->

# [3422. Minimum Operations to Make Subarray Elements Equal 🔒](https://leetcode.com/problems/minimum-operations-to-make-subarray-elements-equal)

[中文文档](/solution/3400-3499/3422.Minimum%20Operations%20to%20Make%20Subarray%20Elements%20Equal/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> and an integer <code>k</code>. You can perform the following operation any number of times:</p>

<ul>
	<li>Increase or decrease any element of <code>nums</code> by 1.</li>
</ul>

<p>Return the <strong>minimum</strong> number of operations required to ensure that <strong>at least</strong> one <span data-keyword="subarray">subarray</span> of size <code>k</code> in <code>nums</code> has all elements equal.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [4,-3,2,1,-4,6], k = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">5</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Use 4 operations to add 4 to <code>nums[1]</code>. The resulting array is <span class="example-io"><code>[4, 1, 2, 1, -4, 6]</code>.</span></li>
	<li><span class="example-io">Use 1 operation to subtract 1 from <code>nums[2]</code>. The resulting array is <code>[4, 1, 1, 1, -4, 6]</code>.</span></li>
	<li><span class="example-io">The array now contains a subarray <code>[1, 1, 1]</code> of size <code>k = 3</code> with all elements equal. Hence, the answer is 5.</span></li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [-2,-2,3,1,4], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>
	<p>The subarray <code>[-2, -2]</code> of size <code>k = 2</code> already contains all equal elements, so no operations are needed. Hence, the answer is 0.</p>
	</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>6</sup> &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
	<li><code>2 &lt;= k &lt;= nums.length</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Ordered Set

According to the problem description, we need to find a subarray of length $k$ and make all elements in the subarray equal with the minimum number of operations. That is, we need to find a subarray of length $k$ such that the minimum number of operations required to make all elements in the subarray equal to the median of these $k$ elements is minimized.

We can use two ordered sets $l$ and $r$ to maintain the left and right parts of the $k$ elements, respectively. $l$ is used to store the smaller part of the $k$ elements, and $r$ is used to store the larger part of the $k$ elements. The number of elements in $l$ is either equal to the number of elements in $r$ or one less than the number of elements in $r$, so the minimum value in $r$ is the median of the $k$ elements.

The time complexity is $O(n \times \log k)$, and the space complexity is $O(k)$. Here, $n$ is the length of the array $\textit{nums}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minOperations(self, nums: List[int], k: int) -> int:
        l = SortedList()
        r = SortedList()
        s1 = s2 = 0
        ans = inf
        for i, x in enumerate(nums):
            l.add(x)
            s1 += x
            y = l.pop()
            s1 -= y
            r.add(y)
            s2 += y
            if len(r) - len(l) > 1:
                y = r.pop(0)
                s2 -= y
                l.add(y)
                s1 += y
            if i >= k - 1:
                ans = min(ans, s2 - r[0] * len(r) + r[0] * len(l) - s1)
                j = i - k + 1
                if nums[j] in r:
                    r.remove(nums[j])
                    s2 -= nums[j]
                else:
                    l.remove(nums[j])
                    s1 -= nums[j]
        return ans
```

#### Java

```java
class Solution {
    public long minOperations(int[] nums, int k) {
        TreeMap<Integer, Integer> l = new TreeMap<>();
        TreeMap<Integer, Integer> r = new TreeMap<>();
        long s1 = 0, s2 = 0;
        int sz1 = 0, sz2 = 0;
        long ans = Long.MAX_VALUE;
        for (int i = 0; i < nums.length; ++i) {
            l.merge(nums[i], 1, Integer::sum);
            s1 += nums[i];
            ++sz1;
            int y = l.lastKey();
            if (l.merge(y, -1, Integer::sum) == 0) {
                l.remove(y);
            }
            s1 -= y;
            --sz1;
            r.merge(y, 1, Integer::sum);
            s2 += y;
            ++sz2;
            if (sz2 - sz1 > 1) {
                y = r.firstKey();
                if (r.merge(y, -1, Integer::sum) == 0) {
                    r.remove(y);
                }
                s2 -= y;
                --sz2;
                l.merge(y, 1, Integer::sum);
                s1 += y;
                ++sz1;
            }
            if (i >= k - 1) {
                ans = Math.min(ans, s2 - r.firstKey() * sz2 + r.firstKey() * sz1 - s1);
                int j = i - k + 1;
                if (r.containsKey(nums[j])) {
                    if (r.merge(nums[j], -1, Integer::sum) == 0) {
                        r.remove(nums[j]);
                    }
                    s2 -= nums[j];
                    --sz2;
                } else {
                    if (l.merge(nums[j], -1, Integer::sum) == 0) {
                        l.remove(nums[j]);
                    }
                    s1 -= nums[j];
                    --sz1;
                }
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
    long long minOperations(vector<int>& nums, int k) {
        multiset<int> l, r;
        long long s1 = 0, s2 = 0, ans = 1e18;
        for (int i = 0; i < nums.size(); ++i) {
            l.insert(nums[i]);
            s1 += nums[i];
            int y = *l.rbegin();
            l.erase(l.find(y));
            s1 -= y;
            r.insert(y);
            s2 += y;
            if (r.size() - l.size() > 1) {
                y = *r.begin();
                r.erase(r.find(y));
                s2 -= y;
                l.insert(y);
                s1 += y;
            }
            if (i >= k - 1) {
                long long x = *r.begin();
                ans = min(ans, s2 - x * (int) r.size() + x * (int) l.size() - s1);
                int j = i - k + 1;
                if (r.contains(nums[j])) {
                    r.erase(r.find(nums[j]));
                    s2 -= nums[j];
                } else {
                    l.erase(l.find(nums[j]));
                    s1 -= nums[j];
                }
            }
        }
        return ans;
    }
};
```

#### Go

```go
func minOperations(nums []int, k int) int64 {
	l := redblacktree.New[int, int]()
	r := redblacktree.New[int, int]()
	merge := func(st *redblacktree.Tree[int, int], x, v int) {
		c, _ := st.Get(x)
		if c+v == 0 {
			st.Remove(x)
		} else {
			st.Put(x, c+v)
		}
	}
	var s1, s2, sz1, sz2 int
	ans := math.MaxInt64
	for i, x := range nums {
		merge(l, x, 1)
		s1 += x
		y := l.Right().Key
		merge(l, y, -1)
		s1 -= y
		merge(r, y, 1)
		s2 += y
		sz2++
		if sz2-sz1 > 1 {
			y = r.Left().Key
			merge(r, y, -1)
			s2 -= y
			sz2--
			merge(l, y, 1)
			s1 += y
			sz1++
		}
		if j := i - k + 1; j >= 0 {
			ans = min(ans, s2-r.Left().Key*sz2+r.Left().Key*sz1-s1)
			if _, ok := r.Get(nums[j]); ok {
				merge(r, nums[j], -1)
				s2 -= nums[j]
				sz2--
			} else {
				merge(l, nums[j], -1)
				s1 -= nums[j]
				sz1--
			}
		}
	}
	return int64(ans)
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
