---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3640.Trionic%20Array%20II/README.md
rating: 2277
source: 第 461 场周赛 Q4
tags:
    - 数组
    - 动态规划
---

<!-- problem:start -->

# [3640. 三段式数组 II](https://leetcode.cn/problems/trionic-array-ii)

[English Version](/solution/3600-3699/3640.Trionic%20Array%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p data-end="191" data-start="0">给你一个长度为 <code data-end="75" data-start="72">n</code> 的整数数组 <code data-end="61" data-start="55">nums</code>。</p>

<p data-end="191" data-start="0"><strong data-end="99" data-is-only-node="" data-start="79">三段式子数组</strong> 是一个连续子数组 <code data-end="136" data-start="125">nums[l...r]</code>（满足 <code data-end="158" data-start="143">0 &lt;= l &lt; r &lt; n</code>），并且存在下标&nbsp;<code>l &lt; p &lt; q &lt; r</code>，使得：</p>

<ul>
	<li data-end="267" data-start="230"><code data-end="241" data-start="230">nums[l...p]</code> <strong>严格</strong> 递增，</li>
	<li data-end="307" data-start="270"><code data-end="281" data-start="270">nums[p...q]</code> <strong>严格</strong> 递减，</li>
	<li data-end="347" data-start="310"><code data-end="321" data-start="310">nums[q...r]</code> <strong>严格</strong> 递增。</li>
</ul>

<p data-end="609" data-is-last-node="" data-is-only-node="" data-start="349">请你从数组 <code data-end="417" data-start="411">nums</code>&nbsp;的所有三段式子数组中找出和最大的那个，并返回其&nbsp;<strong>最大&nbsp;</strong>和。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">nums = [0,-2,-1,-3,0,2,-1]</span></p>

<p><strong>输出：</strong><span class="example-io">-4</span></p>

<p><strong>解释：</strong></p>

<p data-end="129" data-start="72">选择 <code data-end="99" data-start="92">l = 1</code>, <code data-end="108" data-start="101">p = 2</code>, <code data-end="117" data-start="110">q = 3</code>, <code data-end="126" data-start="119">r = 5</code>：</p>

<ul>
	<li data-end="203" data-start="132"><code data-end="166" data-start="132">nums[l...p] = nums[1...2] = [-2, -1]</code> 严格递增&nbsp;(<code data-end="200" data-start="191">-2 &lt; -1</code>)。</li>
	<li data-end="277" data-start="206"><code data-end="240" data-start="206">nums[p...q] = nums[2...3] = [-1, -3]</code> 严格递减&nbsp;(<code data-end="274" data-start="265">-1 &gt; -3</code>)。</li>
	<li data-end="396" data-start="280"><code data-end="316" data-start="280">nums[q...r] = nums[3...5] = [-3, 0, 2]</code> 严格递增&nbsp;(<code data-end="353" data-start="341">-3 &lt; 0 &lt; 2</code>)。</li>
	<li data-end="396" data-start="280">和 = <code>(-2) + (-1) + (-3) + 0 + 2 = -4</code>。</li>
</ul>
</div>

<p><strong class="example">示例 2:</strong></p>

<div class="example-block">
<p><strong>输入:</strong> <span class="example-io">nums = [1,4,2,7]</span></p>

<p><strong>输出:</strong> <span class="example-io">14</span></p>

<p><strong>解释:</strong></p>

<p data-end="519" data-start="462">选择 <code data-end="489" data-start="482">l = 0</code>, <code data-end="498" data-start="491">p = 1</code>, <code data-end="507" data-start="500">q = 2</code>, <code data-end="516" data-start="509">r = 3</code>：</p>

<ul>
	<li data-end="589" data-start="522"><code data-end="554" data-start="522">nums[l...p] = nums[0...1] = [1, 4]</code> 严格递增&nbsp;(<code data-end="586" data-start="579">1 &lt; 4</code>)。</li>
	<li data-end="659" data-start="592"><code data-end="624" data-start="592">nums[p...q] = nums[1...2] = [4, 2]</code> 严格递减&nbsp;(<code data-end="656" data-start="649">4 &gt; 2</code>)。</li>
	<li data-end="754" data-is-last-node="" data-start="662"><code data-end="694" data-start="662">nums[q...r] = nums[2...3] = [2, 7]</code> 严格递增&nbsp;(<code data-end="726" data-start="719">2 &lt; 7</code>)。</li>
	<li data-end="754" data-is-last-node="" data-start="662">和 = <code>1 + 4 + 2 + 7 = 14</code>。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li data-end="883" data-start="851"><code data-end="881" data-start="851">4 &lt;= n = nums.length &lt;= 10<sup>5</sup></code></li>
	<li data-end="914" data-start="886"><code data-end="912" data-start="886">-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li data-end="978" data-is-last-node="" data-start="917">保证至少存在一个三段式子数组。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：分组循环

我们可以遍历数组，寻找所有可能的极大三段式子数组，从而计算其和并更新最大值。

我们定义一个指针 $i$，初始时 $i = 0$，表示当前指向数组的第一个元素。我们将 $i$ 向右移动，直到找到第一个不满足严格递增的元素，即 $nums[i-1] \geq nums[i]$。如果此时 $i = l + 1$，说明这一段只有一个元素，无法形成递增序列，因此继续下一个循环。

接下来，我们定义指针 $p$，表示当前递增段的结束位置。然后找出第二段严格递减的部分，如果这一段只有一个元素或者到达数组末尾，或者出现相等的元素，则继续下一个循环。

然后我们定义指针 $q$，表示当前递减段的结束位置。接着找出第三段严格递增的部分，这时，我们就找到了一个极大的三段式子数组。那么这个三段式子数组的最大和，由以下几个部分组成：

- 下标范围 $[p-2,..,q+1]$ 的元素之和
- 从 $p-3$ 向左扩展的最大递增子数组之和，如果不存在则为 0
- 从 $q+2$ 向右扩展的最大递增子数组之和，如果不存在则为 0。

我们计算出这个三段式子数组的和后，更新答案。然后将指针 $i$ 移动到 $q$ 位置，这是因为第三段的递增部分可以作为下一次循环的第一段递增部分。

遍历结束后，返回答案即可。

时间复杂度 $O(n)$，其中 $n$ 是数组的长度。空间复杂度 $O(1)$，只使用了常数级别的额外空间。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxSumTrionic(self, nums: List[int]) -> int:
        n = len(nums)
        i = 0
        ans = -inf
        while i < n:
            l = i
            i += 1
            while i < n and nums[i - 1] < nums[i]:
                i += 1
            if i == l + 1:
                continue

            p = i - 1
            s = nums[p - 1] + nums[p]
            while i < n and nums[i - 1] > nums[i]:
                s += nums[i]
                i += 1
            if i == p + 1 or i == n or nums[i - 1] == nums[i]:
                continue

            q = i - 1
            s += nums[i]
            i += 1
            mx = t = 0
            while i < n and nums[i - 1] < nums[i]:
                t += nums[i]
                i += 1
                mx = max(mx, t)
            s += mx

            mx = t = 0
            for j in range(p - 2, l - 1, -1):
                t += nums[j]
                mx = max(mx, t)
            s += mx

            ans = max(ans, s)
            i = q
        return ans
```

#### Java

```java
class Solution {
    public long maxSumTrionic(int[] nums) {
        int n = nums.length;
        int i = 0;
        long ans = Long.MIN_VALUE;
        while (i < n) {
            int l = i;
            i += 1;
            while (i < n && nums[i - 1] < nums[i]) {
                i += 1;
            }
            if (i == l + 1) {
                continue;
            }

            int p = i - 1;
            long s = nums[p - 1] + nums[p];
            while (i < n && nums[i - 1] > nums[i]) {
                s += nums[i];
                i += 1;
            }
            if (i == p + 1 || i == n || nums[i - 1] == nums[i]) {
                continue;
            }

            int q = i - 1;
            s += nums[i];
            i += 1;
            long mx = 0, t = 0;
            while (i < n && nums[i - 1] < nums[i]) {
                t += nums[i];
                i += 1;
                mx = Math.max(mx, t);
            }
            s += mx;

            mx = 0;
            t = 0;
            for (int j = p - 2; j >= l; j--) {
                t += nums[j];
                mx = Math.max(mx, t);
            }
            s += mx;

            ans = Math.max(ans, s);
            i = q;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long maxSumTrionic(vector<int>& nums) {
        int n = nums.size();
        int i = 0;
        long long ans = LLONG_MIN;
        while (i < n) {
            int l = i;
            i += 1;
            while (i < n && nums[i - 1] < nums[i]) {
                i += 1;
            }
            if (i == l + 1) {
                continue;
            }

            int p = i - 1;
            long long s = nums[p - 1] + nums[p];
            while (i < n && nums[i - 1] > nums[i]) {
                s += nums[i];
                i += 1;
            }
            if (i == p + 1 || i == n || nums[i - 1] == nums[i]) {
                continue;
            }

            int q = i - 1;
            s += nums[i];
            i += 1;
            long long mx = 0, t = 0;
            while (i < n && nums[i - 1] < nums[i]) {
                t += nums[i];
                i += 1;
                mx = max(mx, t);
            }
            s += mx;

            mx = 0, t = 0;
            for (int j = p - 2; j >= l; j--) {
                t += nums[j];
                mx = max(mx, t);
            }
            s += mx;

            ans = max(ans, s);
            i = q;
        }
        return ans;
    }
};
```

#### Go

```go
func maxSumTrionic(nums []int) int64 {
	n := len(nums)
	i := 0
	ans := int64(math.MinInt64)
	for i < n {
		l := i
		for i++; i < n && nums[i-1] < nums[i]; {
			i++
		}
		if i == l+1 {
			continue
		}

		p := i - 1
		s := int64(nums[p-1]) + int64(nums[p])
		for i < n && nums[i-1] > nums[i] {
			s += int64(nums[i])
			i++
		}
		if i == p+1 || i == n || nums[i-1] == nums[i] {
			continue
		}

		q := i - 1
		s += int64(nums[i])
		i++
		var mx, t int64
		for i < n && nums[i-1] < nums[i] {
			t += int64(nums[i])
			i++
			mx = max(mx, t)
		}
		s += mx

		mx, t = 0, 0
		for j := p - 2; j >= l; j-- {
			t += int64(nums[j])
			mx = max(mx, t)
		}
		s += mx

		ans = max(ans, s)
		i = q
	}
	return ans
}
```

#### TypeScript

```ts
function maxSumTrionic(nums: number[]): number {
    const n = nums.length;
    let i = 0;
    let ans = -Infinity;

    while (i < n) {
        const l = i;
        i += 1;

        while (i < n && nums[i - 1] < nums[i]) i += 1;
        if (i === l + 1) continue;

        const p = i - 1;
        let s = nums[p - 1] + nums[p];

        while (i < n && nums[i - 1] > nums[i]) {
            s += nums[i];
            i += 1;
        }
        if (i === p + 1 || i === n || nums[i - 1] === nums[i]) continue;

        const q = i - 1;
        s += nums[i];
        i += 1;

        let mx = 0,
            t = 0;
        while (i < n && nums[i - 1] < nums[i]) {
            t += nums[i];
            i += 1;
            mx = Math.max(mx, t);
        }
        s += mx;

        mx = 0;
        t = 0;
        for (let j = p - 2; j >= l; j--) {
            t += nums[j];
            mx = Math.max(mx, t);
        }
        s += mx;

        ans = Math.max(ans, s);
        i = q;
    }

    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn max_sum_trionic(nums: Vec<i32>) -> i64 {
        let n = nums.len();
        let mut i = 0;
        let mut ans = i64::MIN;
        while i < n {
            let l = i;
            i += 1;
            while i < n && nums[i - 1] < nums[i] {
                i += 1;
            }
            if i == l + 1 {
                continue;
            }

            let p = i - 1;
            let mut s = nums[p - 1] as i64 + nums[p] as i64;
            while i < n && nums[i - 1] > nums[i] {
                s += nums[i] as i64;
                i += 1;
            }
            if i == p + 1 || i == n || nums[i - 1] == nums[i] {
                continue;
            }

            let q = i - 1;
            s += nums[i] as i64;
            i += 1;
            let mut mx = 0i64;
            let mut t = 0i64;
            while i < n && nums[i - 1] < nums[i] {
                t += nums[i] as i64;
                i += 1;
                mx = mx.max(t);
            }
            s += mx;

            mx = 0;
            t = 0;
            let mut j = p as isize - 2;
            while j >= l as isize {
                t += nums[j as usize] as i64;
                mx = mx.max(t);
                j -= 1;
            }
            s += mx;

            ans = ans.max(s);
            i = q;
        }
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
