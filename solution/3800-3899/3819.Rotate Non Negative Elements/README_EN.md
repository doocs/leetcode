---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3800-3899/3819.Rotate%20Non%20Negative%20Elements/README_EN.md
---

<!-- problem:start -->

# [3819. Rotate Non Negative Elements](https://leetcode.com/problems/rotate-non-negative-elements)

[中文文档](/solution/3800-3899/3819.Rotate%20Non%20Negative%20Elements/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> and an integer <code>k</code>.</p>

<p>Rotate only the <strong>non-negative</strong> elements of the array to the <strong>left</strong> by <code>k</code> positions, in a cyclic manner.</p>

<p>All <strong>negative</strong> elements must stay in their original positions and must not move.</p>

<p>After rotation, place the <strong>non-negative</strong> elements back into the array in the new order, filling only the positions that originally contained <strong>non-negative</strong> values and <strong>skipping all negative</strong> positions.</p>

<p>Return the resulting array.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,-2,3,-4], k = 3</span></p>

<p><strong>Output:</strong> <span class="example-io">[3,-2,1,-4]</span></p>

<p><strong>Explanation:</strong>​​​​​​​</p>

<ul>
	<li>The non-negative elements, in order, are <code>[1, 3]</code>.</li>
	<li>Left rotation with <code>k = 3</code> results in:
	<ul>
		<li><code>[1, 3] -&gt; [3, 1] -&gt; [1, 3] -&gt; [3, 1]</code></li>
	</ul>
	</li>
	<li>Placing them back into the non-negative indices results in <code>[3, -2, 1, -4]</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [-3,-2,7], k = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">[-3,-2,7]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The non-negative elements, in order, are <code>[7]</code>.</li>
	<li>Left rotation with <code>k = 1</code> results in <code>[7]</code>.</li>
	<li>Placing them back into the non-negative indices results in <code>[-3, -2, 7]</code>.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [5,4,-9,6], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">[6,5,-9,4]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The non-negative elements, in order, are <code>[5, 4, 6]</code>.</li>
	<li>Left rotation with <code>k = 2</code> results in <code>[6, 5, 4]</code>.</li>
	<li>Placing them back into the non-negative indices results in <code>[6, 5, -9, 4]</code>.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>5</sup> &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= k &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

We first extract all non-negative elements from the array and store them in a new array $t$.

Then, we create an array $d$ of the same size as $t$ to store the rotated non-negative elements. For each element $t[i]$ in $t$, we place it in $d$ at position $((i - k) \bmod m + m) \bmod m$, where $m$ is the number of non-negative elements.

Next, we iterate through the original array $\textit{nums}$. For each position containing a non-negative element, we replace it with the element from the corresponding position in $d$.

The time complexity is $O(n)$, where $n$ is the length of the array. The space complexity is $O(m)$, where $m$ is the number of non-negative elements.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def rotateElements(self, nums: List[int], k: int) -> List[int]:
        t = [x for x in nums if x >= 0]
        m = len(t)
        d = [0] * m
        for i, x in enumerate(t):
            d[((i - k) % m + m) % m] = x
        j = 0
        for i, x in enumerate(nums):
            if x >= 0:
                nums[i] = d[j]
                j += 1
        return nums
```

#### Java

```java
class Solution {
    public int[] rotateElements(int[] nums, int k) {
        int m = 0;
        for (int x : nums) {
            if (x >= 0) {
                m++;
            }
        }
        int[] t = new int[m];
        int p = 0;
        for (int x : nums) {
            if (x >= 0) {
                t[p++] = x;
            }
        }
        int[] d = new int[m];
        for (int i = 0; i < m; i++) {
            d[((i - k) % m + m) % m] = t[i];
        }
        int j = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= 0) {
                nums[i] = d[j++];
            }
        }
        return nums;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> rotateElements(vector<int>& nums, int k) {
        vector<int> t;
        for (int x : nums) {
            if (x >= 0) {
                t.push_back(x);
            }
        }
        int m = t.size();
        vector<int> d(m);
        for (int i = 0; i < m; i++) {
            d[((i - k) % m + m) % m] = t[i];
        }
        int j = 0;
        for (int i = 0; i < nums.size(); i++) {
            if (nums[i] >= 0) {
                nums[i] = d[j++];
            }
        }
        return nums;
    }
};
```

#### Go

```go
func rotateElements(nums []int, k int) []int {
	t := make([]int, 0)
	for _, x := range nums {
		if x >= 0 {
			t = append(t, x)
		}
	}
	m := len(t)
	d := make([]int, m)
	for i, x := range t {
		d[((i-k)%m+m)%m] = x
	}
	j := 0
	for i, x := range nums {
		if x >= 0 {
			nums[i] = d[j]
			j++
		}
	}
	return nums
}
```

#### TypeScript

```ts
function rotateElements(nums: number[], k: number): number[] {
    const t: number[] = nums.filter(x => x >= 0);
    const m = t.length;
    const d = new Array<number>(m);
    for (let i = 0; i < m; i++) {
        d[(((i - k) % m) + m) % m] = t[i];
    }
    let j = 0;
    for (let i = 0; i < nums.length; i++) {
        if (nums[i] >= 0) {
            nums[i] = d[j++];
        }
    }
    return nums;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
