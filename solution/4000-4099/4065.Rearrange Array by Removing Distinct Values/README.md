---
comments: true
difficulty: 简单
rating: 1172
source: 第 521 场周赛 Q1
tags:
    - 数组
    - 哈希表
    - 计数
    - 有序集合
    - 排序
    - 模拟
    - 堆（优先队列）
---

<!-- problem:start -->

# [4065. 移除不同值重排数组](https://leetcode.cn/problems/rearrange-array-by-removing-distinct-values)

[English Version](/solution/4000-4099/4065.Rearrange%20Array%20by%20Removing%20Distinct%20Values/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>nums</code>。</p>

<p>初始时，你有一个&nbsp;<strong>空</strong>&nbsp;数组 <code>ans</code>。重复执行以下操作，直到 <code>nums</code> 变为&nbsp;<strong>空&nbsp;</strong>：</p>

<ul>
	<li>找出当前 <code>nums</code> 中<strong>&nbsp;所有不同&nbsp;</strong>的值。</li>
	<li>将当前 <code>nums</code> 中每个&nbsp;<strong>不同</strong>&nbsp;的值各移除<strong>一个</strong>，并按<strong>&nbsp;升序&nbsp;</strong>将这些值依次添加到 <code>ans</code> 中。</li>
</ul>

<p>返回数组 <code>ans</code>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [3,1,3,2,1,3]</span></p>

<p><strong>输出：</strong> <span class="example-io">[1,2,3,1,3,3]</span></p>

<p><strong>解释：</strong></p>

<table border="1" bordercolor="#ccc" cellpadding="5" cellspacing="0" style="border-collapse:collapse;">
	<thead>
		<tr>
			<th scope="col" style="text-align:center;">操作</th>
			<th scope="col" style="text-align:center;">添加到 <code>ans</code> 的值</th>
			<th scope="col" style="text-align:center;">操作后的 <code>nums</code></th>
			<th scope="col" style="text-align:center;">操作后的 <code>ans</code></th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td style="text-align:center;">1</td>
			<td style="text-align:center;">1, 2, 3</td>
			<td style="text-align:center;"><code>[3, 1, 3]</code></td>
			<td style="text-align:center;"><code>[1, 2, 3]</code></td>
		</tr>
		<tr>
			<td style="text-align:center;">2</td>
			<td style="text-align:center;">1, 3</td>
			<td style="text-align:center;"><code>[3]</code></td>
			<td style="text-align:center;"><code>[1, 2, 3, 1, 3]</code></td>
		</tr>
		<tr>
			<td style="text-align:center;">3</td>
			<td style="text-align:center;">3</td>
			<td style="text-align:center;"><code>[]</code></td>
			<td style="text-align:center;"><code>[1, 2, 3, 1, 3, 3]</code></td>
		</tr>
	</tbody>
</table>

<p>此时 <code>nums</code> 已为空，因此答案为 <code>[1, 2, 3, 1, 3, 3]</code>。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [7,7,4,4,4]</span></p>

<p><strong>输出：</strong> <span class="example-io">[4,7,4,7,4]</span></p>

<p><strong>解释：</strong></p>

<table border="1" bordercolor="#ccc" cellpadding="5" cellspacing="0" style="border-collapse:collapse;">
	<thead>
		<tr>
			<th scope="col" style="text-align:center;">操作</th>
			<th scope="col" style="text-align:center;">添加到 <code>ans</code> 的值</th>
			<th scope="col" style="text-align:center;">操作后的 <code>nums</code></th>
			<th scope="col" style="text-align:center;">操作后的 <code>ans</code></th>
		</tr>
	</thead>
	<tbody>
		<tr>
			<td style="text-align:center;">1</td>
			<td style="text-align:center;">4, 7</td>
			<td style="text-align:center;"><code>[7, 4, 4]</code></td>
			<td style="text-align:center;"><code>[4, 7]</code></td>
		</tr>
		<tr>
			<td style="text-align:center;">2</td>
			<td style="text-align:center;">4, 7</td>
			<td style="text-align:center;"><code>[4]</code></td>
			<td style="text-align:center;"><code>[4, 7, 4, 7]</code></td>
		</tr>
		<tr>
			<td style="text-align:center;">3</td>
			<td style="text-align:center;">4</td>
			<td style="text-align:center;"><code>[]</code></td>
			<td style="text-align:center;"><code>[4, 7, 4, 7, 4]</code></td>
		</tr>
	</tbody>
</table>

<p>此时 <code>nums</code> 已为空，因此答案为 <code>[4, 7, 4, 7, 4]</code>。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 100</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：计数

<!-- thinking:start -->

> **思考**
>
> $n$ 和每个元素都不超过 $100$，按题意一轮轮取出剩余的不同值是来得及的。每一轮都要收集当前还在的值并按升序各删一个，若直接在原数组里查找再删除，下标会不断挪动。
>
> 一个值被取走的次数就是它的出现次数，每一轮内部的先后只由数值大小决定，与原来的下标无关。
>
> 因此先按值计数。值域是 $[1,m]$，从小到大扫描，次数仍为正就写入答案并减一。外层重复到答案长度等于 $n$，每一遍扫描对应一轮操作。

<!-- thinking:end -->

设 $m=\max(\textit{nums})$， $\textit{cnt}[x]$ 为值 $x$ 的出现次数。第 $k$ 轮（从 $0$ 计）按升序取走所有当时仍有剩余的值，也就是最初出现次数大于 $k$ 的那些值。

用长度为 $m+1$ 的数组记下次数。答案还不满 $n$ 个元素时，把 $x$ 从 $1$ 扫到 $m$： $\textit{cnt}[x]>0$ 就把 $x$ 追加进答案，并把次数减一。一轮扫描对应一次操作，追加的顺序就是升序。

时间复杂度 $O(nm)$，空间复杂度 $O(m)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def rearrangeArray(self, nums: list[int]) -> list[int]:
        mx = max(nums)
        cnt = [0] * (mx + 1)
        for x in nums:
            cnt[x] += 1

        ans = []
        while len(ans) < len(nums):
            for x in range(1, mx + 1):
                if cnt[x]:
                    ans.append(x)
                    cnt[x] -= 1
        return ans
```

#### Java

```java
class Solution {
    public int[] rearrangeArray(int[] nums) {
        int mx = 0;
        for (int x : nums) {
            mx = Math.max(mx, x);
        }
        int[] cnt = new int[mx + 1];
        for (int x : nums) {
            cnt[x]++;
        }

        int[] ans = new int[nums.length];
        int idx = 0;
        while (idx < nums.length) {
            for (int x = 1; x <= mx; x++) {
                if (cnt[x] > 0) {
                    ans[idx++] = x;
                    cnt[x]--;
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
    vector<int> rearrangeArray(vector<int>& nums) {
        int mx = ranges::max(nums);
        vector<int> cnt(mx + 1);
        for (int x : nums) {
            cnt[x]++;
        }

        vector<int> ans;
        while (ans.size() < nums.size()) {
            for (int x = 1; x <= mx; x++) {
                if (cnt[x]) {
                    ans.push_back(x);
                    cnt[x]--;
                }
            }
        }
        return ans;
    }
};
```

#### Go

```go
func rearrangeArray(nums []int) []int {
	mx := slices.Max(nums)

	cnt := make([]int, mx+1)
	for _, x := range nums {
		cnt[x]++
	}

	ans := make([]int, 0, len(nums))
	for len(ans) < len(nums) {
		for x := 1; x <= mx; x++ {
			if cnt[x] > 0 {
				ans = append(ans, x)
				cnt[x]--
			}
		}
	}
	return ans
}
```

#### TypeScript

```ts
function rearrangeArray(nums: number[]): number[] {
    const mx = Math.max(...nums);
    const cnt = new Array(mx + 1).fill(0);

    for (const x of nums) {
        cnt[x]++;
    }

    const ans: number[] = [];
    while (ans.length < nums.length) {
        for (let x = 1; x <= mx; x++) {
            if (cnt[x]) {
                ans.push(x);
                cnt[x]--;
            }
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
