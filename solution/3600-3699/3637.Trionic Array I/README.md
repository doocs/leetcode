---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3637.Trionic%20Array%20I/README.md
rating: 1263
source: 第 461 场周赛 Q1
tags:
    - 数组
---

<!-- problem:start -->

# [3637. 三段式数组 I](https://leetcode.cn/problems/trionic-array-i)

[English Version](/solution/3600-3699/3637.Trionic%20Array%20I/README_EN.md)

## 题目描述

<!-- description:start -->

<p data-end="128" data-start="0">给你一个长度为 <code data-end="51" data-start="48">n</code> 的整数数组 <code data-end="37" data-start="31">nums</code>。</p>

<p data-end="128" data-start="0">如果存在索引 <code data-end="117" data-start="100">0 &lt; p &lt; q &lt; n − 1</code>，使得数组满足以下条件，则称其为 <strong data-end="76" data-start="65">三段式数组（trionic）</strong>：</p>

<ul>
	<li data-end="170" data-start="132"><code data-end="144" data-start="132">nums[0...p]</code>&nbsp;<strong>严格</strong> 递增，</li>
	<li data-end="211" data-start="173"><code data-end="185" data-start="173">nums[p...q]</code>&nbsp;<strong>严格</strong> 递减，</li>
	<li data-end="252" data-start="214"><code data-end="228" data-start="214">nums[q...n − 1]</code>&nbsp;<strong>严格</strong> 递增。</li>
</ul>

<p data-end="315" data-is-last-node="" data-is-only-node="" data-start="254">如果 <code data-end="277" data-start="271">nums</code> 是三段式数组，返回 <code data-end="267" data-start="261">true</code>；否则，返回 <code data-end="314" data-start="307">false</code>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1:</strong></p>

<div class="example-block">
<p><strong>输入:</strong> <span class="example-io">nums = [1,3,5,4,2,6]</span></p>

<p><strong>输出:</strong> <span class="example-io">true</span></p>

<p><strong>解释:</strong></p>

<p>选择 <code data-end="91" data-start="84">p = 2</code>, <code data-end="100" data-start="93">q = 4</code>：</p>

<ul>
	<li><code data-end="130" data-start="108">nums[0...2] = [1, 3, 5]</code> 严格递增&nbsp;(<code data-end="166" data-start="155">1 &lt; 3 &lt; 5</code>)。</li>
	<li><code data-end="197" data-start="175">nums[2...4] = [5, 4, 2]</code> 严格递减&nbsp;(<code data-end="233" data-start="222">5 &gt; 4 &gt; 2</code>)。</li>
	<li><code data-end="262" data-start="242">nums[4...5] = [2, 6]</code> 严格递增&nbsp;(<code data-end="294" data-start="287">2 &lt; 6</code>)。</li>
</ul>
</div>

<p><strong class="example">示例 2:</strong></p>

<div class="example-block">
<p><strong>输入:</strong> <span class="example-io">nums = [2,1,3]</span></p>

<p><strong>输出:</strong> <span class="example-io">false</span></p>

<p><strong>解释:</strong></p>

<p>无法选出能使数组满足三段式要求的&nbsp;<code>p</code> 和 <code>q</code> 。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li data-end="41" data-start="26"><code data-end="39" data-start="26">3 &lt;= n &lt;= 100</code></li>
	<li data-end="70" data-start="44"><code data-end="70" data-start="44">-1000 &lt;= nums[i] &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：一次遍历

我们首先定义一个指针 $p$，初始时 $p = 0$，表示当前指向数组的第一个元素。我们将 $p$ 向右移动，直到找到第一个不满足严格递增的元素，即 $nums[p] \geq nums[p + 1]$。如果此时 $p = 0$，说明数组的前半部分没有严格递增的部分，因此直接返回 $\text{false}$。

接下来，我们定义另一个指针 $q$，初始时 $q = p$，表示当前指向数组的第二个部分的第一个元素。我们将 $q$ 向右移动，直到找到第一个不满足严格递减的元素，即 $nums[q] \leq nums[q + 1]$。如果此时 $q = p$ 或者 $q = n - 1$，说明数组的第二部分没有严格递减的部分或者没有第三部分，因此直接返回 $\text{false}$。

如果以上条件都满足，说明数组是三段式的，返回 $\text{true}$。

时间复杂度 $O(n)$，其中 $n$ 是数组的长度。空间复杂度 $O(1)$，只使用了常数级别的额外空间。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def isTrionic(self, nums: List[int]) -> bool:
        n = len(nums)
        p = 0
        while p < n - 2 and nums[p] < nums[p + 1]:
            p += 1
        if p == 0:
            return False
        q = p
        while q < n - 1 and nums[q] > nums[q + 1]:
            q += 1
        if q == p or q == n - 1:
            return False
        while q < n - 1 and nums[q] < nums[q + 1]:
            q += 1
        return q == n - 1
```

#### Java

```java
class Solution {
    public boolean isTrionic(int[] nums) {
        int n = nums.length;
        int p = 0;
        while (p < n - 2 && nums[p] < nums[p + 1]) {
            p++;
        }
        if (p == 0) {
            return false;
        }
        int q = p;
        while (q < n - 1 && nums[q] > nums[q + 1]) {
            q++;
        }
        if (q == p || q == n - 1) {
            return false;
        }
        while (q < n - 1 && nums[q] < nums[q + 1]) {
            q++;
        }
        return q == n - 1;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool isTrionic(vector<int>& nums) {
        int n = nums.size();
        int p = 0;
        while (p < n - 2 && nums[p] < nums[p + 1]) {
            p++;
        }
        if (p == 0) {
            return false;
        }
        int q = p;
        while (q < n - 1 && nums[q] > nums[q + 1]) {
            q++;
        }
        if (q == p || q == n - 1) {
            return false;
        }
        while (q < n - 1 && nums[q] < nums[q + 1]) {
            q++;
        }
        return q == n - 1;
    }
};
```

#### Go

```go
func isTrionic(nums []int) bool {
	n := len(nums)
	p := 0
	for p < n-2 && nums[p] < nums[p+1] {
		p++
	}
	if p == 0 {
		return false
	}
	q := p
	for q < n-1 && nums[q] > nums[q+1] {
		q++
	}
	if q == p || q == n-1 {
		return false
	}
	for q < n-1 && nums[q] < nums[q+1] {
		q++
	}
	return q == n-1
}
```

#### TypeScript

```ts
function isTrionic(nums: number[]): boolean {
    const n = nums.length;
    let p = 0;
    while (p < n - 2 && nums[p] < nums[p + 1]) {
        p++;
    }
    if (p === 0) {
        return false;
    }
    let q = p;
    while (q < n - 1 && nums[q] > nums[q + 1]) {
        q++;
    }
    if (q === p || q === n - 1) {
        return false;
    }
    while (q < n - 1 && nums[q] < nums[q + 1]) {
        q++;
    }
    return q === n - 1;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
