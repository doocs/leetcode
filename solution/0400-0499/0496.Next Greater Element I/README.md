---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0400-0499/0496.Next%20Greater%20Element%20I/README.md
tags:
    - 栈
    - 数组
    - 哈希表
    - 单调栈
---

<!-- problem:start -->

# [496. 下一个更大元素 I](https://leetcode.cn/problems/next-greater-element-i)

[English Version](/solution/0400-0499/0496.Next%20Greater%20Element%20I/README_EN.md)

## 题目描述

<!-- description:start -->

<p><code>nums1</code>&nbsp;中数字&nbsp;<code>x</code>&nbsp;的 <strong>下一个更大元素</strong> 是指&nbsp;<code>x</code>&nbsp;在&nbsp;<code>nums2</code> 中对应位置 <strong>右侧</strong> 的 <strong>第一个</strong> 比&nbsp;<code>x</code><strong>&nbsp;</strong>大的元素。</p>

<p>给你两个<strong> 没有重复元素</strong> 的数组&nbsp;<code>nums1</code> 和&nbsp;<code>nums2</code> ，下标从 <strong>0</strong> 开始计数，其中<code>nums1</code>&nbsp;是&nbsp;<code>nums2</code>&nbsp;的子集。</p>

<p>对于每个 <code>0 &lt;= i &lt; nums1.length</code> ，找出满足 <code>nums1[i] == nums2[j]</code> 的下标 <code>j</code> ，并且在 <code>nums2</code> 确定 <code>nums2[j]</code> 的 <strong>下一个更大元素</strong> 。如果不存在下一个更大元素，那么本次查询的答案是 <code>-1</code> 。</p>

<p>返回一个长度为&nbsp;<code>nums1.length</code> 的数组<em> </em><code>ans</code><em> </em>作为答案，满足<em> </em><code>ans[i]</code><em> </em>是如上所述的 <strong>下一个更大元素</strong> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums1 = [4,1,2], nums2 = [1,3,4,2].
<strong>输出：</strong>[-1,3,-1]
<strong>解释：</strong>nums1 中每个值的下一个更大元素如下所述：
- 4 ，用加粗斜体标识，nums2 = [1,3,<strong>4</strong>,2]。不存在下一个更大元素，所以答案是 -1 。
- 1 ，用加粗斜体标识，nums2 = [<em><strong>1</strong></em>,3,4,2]。下一个更大元素是 3 。
- 2 ，用加粗斜体标识，nums2 = [1,3,4,<em><strong>2</strong></em>]。不存在下一个更大元素，所以答案是 -1 。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums1 = [2,4], nums2 = [1,2,3,4].
<strong>输出：</strong>[3,-1]
<strong>解释：</strong>nums1 中每个值的下一个更大元素如下所述：
- 2 ，用加粗斜体标识，nums2 = [1,<em><strong>2</strong></em>,3,4]。下一个更大元素是 3 。
- 4 ，用加粗斜体标识，nums2 = [1,2,3,<em><strong>4</strong></em>]。不存在下一个更大元素，所以答案是 -1 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums1.length &lt;= nums2.length &lt;= 1000</code></li>
	<li><code>0 &lt;= nums1[i], nums2[i] &lt;= 10<sup>4</sup></code></li>
	<li><code>nums1</code>和<code>nums2</code>中所有整数 <strong>互不相同</strong></li>
	<li><code>nums1</code> 中的所有整数同样出现在 <code>nums2</code> 中</li>
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong>你可以设计一个时间复杂度为 <code>O(nums1.length + nums2.length)</code> 的解决方案吗？</p>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：单调栈

我们可以从右往左遍历数组 $\textit{nums2}$，维护一个从栈顶到栈底单调递增的栈 $\textit{stk}$，并且用哈希表 $\textit{d}$ 记录每个元素的下一个更大元素。

遍历到元素 $x$ 时，如果栈不为空且栈顶元素小于 $x$，我们就不断弹出栈顶元素，直到栈为空或者栈顶元素大于等于 $x$。此时，如果栈不为空，栈顶元素就是 $x$ 的下一个更大元素，否则 $x$ 没有下一个更大元素。

最后我们遍历数组 $\textit{nums1}$，根据哈希表 $\textit{d}$ 得到答案。

时间复杂度 $O(m + n)$，空间复杂度 $O(n)$。其中 $m$ 和 $n$ 分别为数组 $\textit{nums1}$ 和 $\textit{nums2}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def nextGreaterElement(self, nums1: List[int], nums2: List[int]) -> List[int]:
        stk = []
        d = {}
        for x in nums2[::-1]:
            while stk and stk[-1] < x:
                stk.pop()
            if stk:
                d[x] = stk[-1]
            stk.append(x)
        return [d.get(x, -1) for x in nums1]
```

#### Java

```java
class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Deque<Integer> stk = new ArrayDeque<>();
        int m = nums1.length, n = nums2.length;
        Map<Integer, Integer> d = new HashMap(n);
        for (int i = n - 1; i >= 0; --i) {
            int x = nums2[i];
            while (!stk.isEmpty() && stk.peek() < x) {
                stk.pop();
            }
            if (!stk.isEmpty()) {
                d.put(x, stk.peek());
            }
            stk.push(x);
        }
        int[] ans = new int[m];
        for (int i = 0; i < m; ++i) {
            ans[i] = d.getOrDefault(nums1[i], -1);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> nextGreaterElement(vector<int>& nums1, vector<int>& nums2) {
        stack<int> stk;
        unordered_map<int, int> d;
        ranges::reverse(nums2);
        for (int x : nums2) {
            while (!stk.empty() && stk.top() < x) {
                stk.pop();
            }
            if (!stk.empty()) {
                d[x] = stk.top();
            }
            stk.push(x);
        }
        vector<int> ans;
        for (int x : nums1) {
            ans.push_back(d.contains(x) ? d[x] : -1);
        }
        return ans;
    }
};
```

#### Go

```go
func nextGreaterElement(nums1 []int, nums2 []int) (ans []int) {
	stk := []int{}
	d := map[int]int{}
	for i := len(nums2) - 1; i >= 0; i-- {
		x := nums2[i]
		for len(stk) > 0 && stk[len(stk)-1] < x {
			stk = stk[:len(stk)-1]
		}
		if len(stk) > 0 {
			d[x] = stk[len(stk)-1]
		}
		stk = append(stk, x)
	}
	for _, x := range nums1 {
		if v, ok := d[x]; ok {
			ans = append(ans, v)
		} else {
			ans = append(ans, -1)
		}
	}
	return
}
```

#### TypeScript

```ts
function nextGreaterElement(nums1: number[], nums2: number[]): number[] {
    const stk: number[] = [];
    const d: Record<number, number> = {};
    for (const x of nums2.reverse()) {
        while (stk.length && stk.at(-1)! < x) {
            stk.pop();
        }
        d[x] = stk.length ? stk.at(-1)! : -1;
        stk.push(x);
    }
    return nums1.map(x => d[x]);
}
```

#### Rust

```rust
use std::collections::HashMap;

impl Solution {
    pub fn next_greater_element(nums1: Vec<i32>, nums2: Vec<i32>) -> Vec<i32> {
        let mut stk = Vec::new();
        let mut d = HashMap::new();
        for &x in nums2.iter().rev() {
            while let Some(&top) = stk.last() {
                if top <= x {
                    stk.pop();
                } else {
                    break;
                }
            }
            if let Some(&top) = stk.last() {
                d.insert(x, top);
            }
            stk.push(x);
        }

        nums1
            .into_iter()
            .map(|x| *d.get(&x).unwrap_or(&-1))
            .collect()
    }
}
```

#### JavaScript

```js
/**
 * @param {number[]} nums1
 * @param {number[]} nums2
 * @return {number[]}
 */
var nextGreaterElement = function (nums1, nums2) {
    const stk = [];
    const d = {};
    for (const x of nums2.reverse()) {
        while (stk.length && stk.at(-1) < x) {
            stk.pop();
        }
        d[x] = stk.length ? stk.at(-1) : -1;
        stk.push(x);
    }
    return nums1.map(x => d[x]);
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
