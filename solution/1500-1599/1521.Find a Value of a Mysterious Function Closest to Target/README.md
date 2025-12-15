---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1500-1599/1521.Find%20a%20Value%20of%20a%20Mysterious%20Function%20Closest%20to%20Target/README.md
rating: 2383
source: 第 198 场周赛 Q4
tags:
    - 位运算
    - 线段树
    - 数组
    - 二分查找
---

<!-- problem:start -->

# [1521. 找到最接近目标值的函数值](https://leetcode.cn/problems/find-a-value-of-a-mysterious-function-closest-to-target)

[English Version](/solution/1500-1599/1521.Find%20a%20Value%20of%20a%20Mysterious%20Function%20Closest%20to%20Target/README_EN.md)

## 题目描述

<!-- description:start -->

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1521.Find%20a%20Value%20of%20a%20Mysterious%20Function%20Closest%20to%20Target/images/change.png" style="height: 312px; width: 635px;"></p>

<p>Winston 构造了一个如上所示的函数&nbsp;<code>func</code>&nbsp;。他有一个整数数组&nbsp;<code>arr</code>&nbsp;和一个整数&nbsp;<code>target</code>&nbsp;，他想找到让&nbsp;<code>|func(arr, l, r) - target|</code>&nbsp;最小的 <code>l</code>&nbsp;和 <code>r</code>&nbsp;。</p>

<p>请你返回&nbsp;<code>|func(arr, l, r) - target|</code>&nbsp;的最小值。</p>

<p>请注意，&nbsp;<code>func</code> 的输入参数&nbsp;<code>l</code> 和&nbsp;<code>r</code>&nbsp;需要满足&nbsp;<code>0 &lt;= l, r &lt; arr.length</code>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>arr = [9,12,3,7,15], target = 5
<strong>输出：</strong>2
<strong>解释：</strong>所有可能的 [l,r] 数对包括 [[0,0],[1,1],[2,2],[3,3],[4,4],[0,1],[1,2],[2,3],[3,4],[0,2],[1,3],[2,4],[0,3],[1,4],[0,4]]， Winston 得到的相应结果为 [9,12,3,7,15,8,0,3,7,0,0,3,0,0,0] 。最接近 5 的值是 7 和 3，所以最小差值为 2 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>arr = [1000000,1000000,1000000], target = 1
<strong>输出：</strong>999999
<strong>解释：</strong>Winston 输入函数的所有可能 [l,r] 数对得到的函数值都为 1000000 ，所以最小差值为 999999 。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>arr = [1,2,4,8,16], target = 0
<strong>输出：</strong>0
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= arr.length &lt;= 10^5</code></li>
	<li><code>1 &lt;= arr[i] &lt;= 10^6</code></li>
	<li><code>0 &lt;= target &lt;= 10^7</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表 + 枚举

根据题目描述，我们知道，函数 $func(arr, l, r)$ 实际上就是数组 $arr$ 下标 $l$ 到 $r$ 的元素的按位与运算的结果，即 $arr[l] \& arr[l + 1] \& \cdots \& arr[r]$。

如果我们每次固定右端点 $r$，那么左端点 $l$ 的范围是 $[0, r]$。由于按位与之和随着 $l$ 的减小而单调递减，并且 $arr[i]$ 的值不超过 $10^6$，因此区间 $[0, r]$ 最多只有 $20$ 种不同的值。因此，我们可以用一个集合来维护所有的 $arr[l] \& arr[l + 1] \& \cdots \& arr[r]$ 的值。当我们从 $r$ 遍历到 $r+1$ 时，以 $r+1$ 为右端点的值，就是集合中每个值与 $arr[r + 1]$ 进行按位与运算得到的值，再加上 $arr[r + 1]$ 本身。因此，我们只需要枚举集合中的每个值，与 $arr[r]$ 进行按位与运算，就可以得到以 $r$ 为右端点的所有值，将每个值与 $target$ 相减后取绝对值，就可以得到以 $r$ 为右端点的所有值与 $target$ 的差的绝对值，其中的最小值就是答案。

时间复杂度 $O(n \times \log M)$，空间复杂度 $O(\log M)$。其中 $n$ 和 $M$ 分别是数组 $arr$ 的长度和数组 $arr$ 中的最大值。

相似题目：

- [3171. 找到按位与最接近 K 的子数组](https://github.com/doocs/leetcode/blob/main/solution/3100-3199/3171.Find%20Subarray%20With%20Bitwise%20AND%20Closest%20to%20K/README.md)

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def closestToTarget(self, arr: List[int], target: int) -> int:
        ans = abs(arr[0] - target)
        s = {arr[0]}
        for x in arr:
            s = {x & y for y in s} | {x}
            ans = min(ans, min(abs(y - target) for y in s))
        return ans
```

#### Java

```java
class Solution {
    public int closestToTarget(int[] arr, int target) {
        int ans = Math.abs(arr[0] - target);
        Set<Integer> pre = new HashSet<>();
        pre.add(arr[0]);
        for (int x : arr) {
            Set<Integer> cur = new HashSet<>();
            for (int y : pre) {
                cur.add(x & y);
            }
            cur.add(x);
            for (int y : cur) {
                ans = Math.min(ans, Math.abs(y - target));
            }
            pre = cur;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int closestToTarget(vector<int>& arr, int target) {
        int ans = abs(arr[0] - target);
        unordered_set<int> pre;
        pre.insert(arr[0]);
        for (int x : arr) {
            unordered_set<int> cur;
            cur.insert(x);
            for (int y : pre) {
                cur.insert(x & y);
            }
            for (int y : cur) {
                ans = min(ans, abs(y - target));
            }
            pre = move(cur);
        }
        return ans;
    }
};
```

#### Go

```go
func closestToTarget(arr []int, target int) int {
	ans := abs(arr[0] - target)
	pre := map[int]bool{arr[0]: true}
	for _, x := range arr {
		cur := map[int]bool{x: true}
		for y := range pre {
			cur[x&y] = true
		}
		for y := range cur {
			ans = min(ans, abs(y-target))
		}
		pre = cur
	}
	return ans
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

#### TypeScript

```ts
function closestToTarget(arr: number[], target: number): number {
    let ans = Math.abs(arr[0] - target);
    let pre = new Set<number>();
    pre.add(arr[0]);
    for (const x of arr) {
        const cur = new Set<number>();
        cur.add(x);
        for (const y of pre) {
            cur.add(x & y);
        }
        for (const y of cur) {
            ans = Math.min(ans, Math.abs(y - target));
        }
        pre = cur;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
