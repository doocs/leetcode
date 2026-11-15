---
comments: true
difficulty: 困难
---

<!-- problem:start -->

# [4114. 统计一致子数组数目 II](https://leetcode.cn/problems/count-consistent-subarrays-ii)

[English Version](/solution/4100-4199/4114.Count%20Consistent%20Subarrays%20II/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>nums</code>。</p>

<p>如果一个子数组中所有元素的&nbsp;<strong>按位与&nbsp;</strong>运算结果等于该子数组的&nbsp;<strong>最小值&nbsp;</strong>，则称该子数组为&nbsp;<strong>一致子数组&nbsp;</strong>。</p>

<p>返回 <code>nums</code> 中&nbsp;<strong>一致子数组&nbsp;</strong>的个数。</p>

<p><strong>子数组&nbsp;</strong>是数组中一个连续、非空的元素序列。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [5,3,1,2]</span></p>

<p><strong>输出：</strong> <span class="example-io">6</span></p>

<p><strong>解释：</strong></p>

<p>以下子数组是一致子数组：</p>

<table style="border: 1px solid black; text-align: center;">
	<tbody>
		<tr>
			<th style="border: 1px solid black;">子数组</th>
			<th style="border: 1px solid black;">包含的值</th>
			<th style="border: 1px solid black;">按位与</th>
			<th style="border: 1px solid black;">最小值</th>
		</tr>
		<tr>
			<td style="border: 1px solid black;">nums[0..0]</td>
			<td style="border: 1px solid black;">[5]</td>
			<td style="border: 1px solid black;">5</td>
			<td style="border: 1px solid black;">5</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">nums[1..1]</td>
			<td style="border: 1px solid black;">[3]</td>
			<td style="border: 1px solid black;">3</td>
			<td style="border: 1px solid black;">3</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">nums[2..2]</td>
			<td style="border: 1px solid black;">[1]</td>
			<td style="border: 1px solid black;">1</td>
			<td style="border: 1px solid black;">1</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">nums[3..3]</td>
			<td style="border: 1px solid black;">[2]</td>
			<td style="border: 1px solid black;">2</td>
			<td style="border: 1px solid black;">2</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">nums[1..2]</td>
			<td style="border: 1px solid black;">[3, 1]</td>
			<td style="border: 1px solid black;">3 & 1 = 1</td>
			<td style="border: 1px solid black;">1</td>
		</tr>
		<tr>
			<td style="border: 1px solid black;">nums[0..2]</td>
			<td style="border: 1px solid black;">[5, 3, 1]</td>
			<td style="border: 1px solid black;">5 & 3 & 1 = 1</td>
			<td style="border: 1px solid black;">1</td>
		</tr>
	</tbody>
</table>

<p>因此，一致子数组的个数为 6。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [1,1,1]</span></p>

<p><strong>输出：</strong> <span class="example-io">6</span></p>

<p><strong>解释：</strong></p>

<p>对于每个子数组，其按位与运算结果和最小值均等于 1。因此，答案为 6。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [0]</span></p>

<p><strong>输出：</strong> <span class="example-io">1</span></p>

<p><strong>解释：</strong></p>

<p>唯一的子数组是 <code>[0]</code>，其按位与运算结果和最小值均为 0。因此，答案为 1。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 2 * 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：单调栈

<!-- thinking:start -->

> **思考**
>
> $n$ 达到 $2\times 10^5$，逐个枚举子数组并比较按位与与最小值，复杂度至少是 $O(n^2)$，无法通过。
>
> 按位与是每个元素的子掩码，因此不会大于最小值。二者相等，当且仅当区间中每个值都按位包含这个最小值。每个一致子数组的最小值都有唯一的最右下标。
>
> 按位包含可以传递：若 $a$ 包含 $b$ 且 $b$ 包含 $c$，则 $a$ 包含 $c$。位置 $i$ 向左延伸到最近一个不包含 $\textit{nums}[i]$ 的下标为止，向右则停在下一个相等值或不包含 $\textit{nums}[i]$ 的下标之前。
>
> 两次单调栈按这个顺序弹出已被当前值按位包含的下标，得到左右边界。每个下标进出栈一次，贡献是两端可选位置个数的乘积。

<!-- thinking:end -->

若 $(a \mathrel{\&} b)=b$，称 $a$ 按位包含 $b$，此时 $a\ge b$。子数组的按位与是每个元素的子掩码，因而不会大于最小值。按位与等于最小值，当且仅当区间中每个元素都按位包含该最小值。

我们把每个一致子数组记在它的最小值最靠右的位置 $i$ 上，并令 $x=\textit{nums}[i]$。

从左向右扫描，用栈保存下标。栈顶按位包含 $x$ 时持续弹出，再把 $i$ 入栈。弹出后若栈不空，栈顶就是 $\textit{left}[i]$，即 $i$ 左侧最近一个不按位包含 $x$ 的下标；栈空则 $\textit{left}[i]=-1$。按位包含可以传递，被更早弹出的下标不会挡住更右侧的元素，因此 $(\textit{left}[i], i)$ 中每个值都按位包含 $x$。

再从右向左扫描。栈顶不等于 $x$ 且按位包含 $x$ 时持续弹出。栈空则令 $\textit{right}=n$，否则令 $\textit{right}$ 为栈顶，然后把 $i$ 入栈。 $\textit{right}$ 是 $i$ 右侧最近一个等于 $x$ 或不按位包含 $x$ 的下标。于是 $(i, \textit{right})$ 中每个值都按位包含 $x$，并且都不等于 $x$。

左端点可以取 $(\textit{left}[i], i]$ 中的任意下标，右端点可以取 $[i, \textit{right})$ 中的任意下标，共 $(i-\textit{left}[i])\times(\textit{right}-i)$ 个子数组。这些子数组中每个元素都按位包含 $x$，最小值与按位与都等于 $x$，并且 $i$ 是该最小值的最右位置。把这份贡献加入答案。

每个下标在两次扫描中各进出栈至多一次。时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是数组 $\textit{nums}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countConsistent(self, nums: list[int]) -> int:
        n = len(nums)
        left = [-1] * n
        stk = []
        for i, x in enumerate(nums):
            while stk and (nums[stk[-1]] & x) == x:
                stk.pop()
            if stk:
                left[i] = stk[-1]
            stk.append(i)

        stk = []
        ans = 0
        for i in range(n - 1, -1, -1):
            x = nums[i]
            while stk and nums[stk[-1]] != x and (nums[stk[-1]] & x) == x:
                stk.pop()
            right = stk[-1] if stk else n
            stk.append(i)
            ans += (i - left[i]) * (right - i)
        return ans
```

#### Java

```java
class Solution {
    public long countConsistent(int[] nums) {
        int n = nums.length;
        int[] left = new int[n];
        Arrays.fill(left, -1);
        Deque<Integer> stk = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            while (!stk.isEmpty() && (nums[stk.peek()] & x) == x) {
                stk.pop();
            }
            if (!stk.isEmpty()) {
                left[i] = stk.peek();
            }
            stk.push(i);
        }

        stk.clear();
        long ans = 0;
        for (int i = n - 1; i >= 0; i--) {
            int x = nums[i];
            while (!stk.isEmpty() && nums[stk.peek()] != x && (nums[stk.peek()] & x) == x) {
                stk.pop();
            }
            int right = stk.isEmpty() ? n : stk.peek();
            stk.push(i);
            ans += (long) (i - left[i]) * (right - i);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long countConsistent(vector<int>& nums) {
        int n = nums.size();
        vector<int> left(n, -1);
        vector<int> stk;
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            while (!stk.empty() && (nums[stk.back()] & x) == x) {
                stk.pop_back();
            }
            if (!stk.empty()) {
                left[i] = stk.back();
            }
            stk.push_back(i);
        }

        stk.clear();
        long long ans = 0;
        for (int i = n - 1; i >= 0; i--) {
            int x = nums[i];
            while (!stk.empty() && nums[stk.back()] != x && (nums[stk.back()] & x) == x) {
                stk.pop_back();
            }
            int right = stk.empty() ? n : stk.back();
            stk.push_back(i);
            ans += 1LL * (i - left[i]) * (right - i);
        }
        return ans;
    }
};
```

#### Go

```go
func countConsistent(nums []int) int64 {
	n := len(nums)
	left := make([]int, n)
	for i := range left {
		left[i] = -1
	}
	stk := []int{}
	for i, x := range nums {
		for len(stk) > 0 && nums[stk[len(stk)-1]]&x == x {
			stk = stk[:len(stk)-1]
		}
		if len(stk) > 0 {
			left[i] = stk[len(stk)-1]
		}
		stk = append(stk, i)
	}

	stk = []int{}
	var ans int64
	for i := n - 1; i >= 0; i-- {
		x := nums[i]
		for len(stk) > 0 && nums[stk[len(stk)-1]] != x && nums[stk[len(stk)-1]]&x == x {
			stk = stk[:len(stk)-1]
		}
		right := n
		if len(stk) > 0 {
			right = stk[len(stk)-1]
		}
		stk = append(stk, i)
		ans += int64(i-left[i]) * int64(right-i)
	}
	return ans
}
```

#### TypeScript

```ts
function countConsistent(nums: number[]): number {
    const n = nums.length;
    const left = new Array(n).fill(-1);
    const stk: number[] = [];
    for (let i = 0; i < n; i++) {
        const x = nums[i];
        while (stk.length && (nums[stk[stk.length - 1]] & x) === x) {
            stk.pop();
        }
        if (stk.length) {
            left[i] = stk[stk.length - 1];
        }
        stk.push(i);
    }

    stk.length = 0;
    let ans = 0;
    for (let i = n - 1; i >= 0; i--) {
        const x = nums[i];
        while (
            stk.length &&
            nums[stk[stk.length - 1]] !== x &&
            (nums[stk[stk.length - 1]] & x) === x
        ) {
            stk.pop();
        }
        const right = stk.length ? stk[stk.length - 1] : n;
        stk.push(i);
        ans += (i - left[i]) * (right - i);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
