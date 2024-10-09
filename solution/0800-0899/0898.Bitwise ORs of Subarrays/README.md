---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0800-0899/0898.Bitwise%20ORs%20of%20Subarrays/README.md
tags:
    - 位运算
    - 数组
    - 动态规划
---

<!-- problem:start -->

# [898. 子数组按位或操作](https://leetcode.cn/problems/bitwise-ors-of-subarrays)

[English Version](/solution/0800-0899/0898.Bitwise%20ORs%20of%20Subarrays/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个整数数组<meta charset="UTF-8" />&nbsp;<code>arr</code>，返回所有&nbsp;<code>arr</code>&nbsp;的非空子数组的不同按位或的数量。</p>

<p>子数组的按位或是子数组中每个整数的按位或。含有一个整数的子数组的按位或就是该整数。</p>

<p><strong>子数组</strong> 是数组内连续的非空元素序列。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>arr = [0]
<strong>输出：</strong>1
<strong>解释：</strong>
只有一个可能的结果 0 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>arr = [1,1,2]
<strong>输出：</strong>3
<strong>解释：</strong>
可能的子数组为 [1]，[1]，[2]，[1, 1]，[1, 2]，[1, 1, 2]。
产生的结果为 1，1，2，1，3，3 。
有三个唯一值，所以答案是 3 。
</pre>

<p><strong>示例&nbsp;3：</strong></p>

<pre>
<strong>输入：</strong>arr = [1,2,4]
<strong>输出：</strong>6
<strong>解释：</strong>
可能的结果是 1，2，3，4，6，以及 7 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong><meta charset="UTF-8" /></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>0 &lt;= nums[i]&nbsp;&lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表

题目求的是子数组按位或操作的结果的数量，如果我们枚举子数组的结束位置 $i$，那么以 $i-1$ 结尾的子数组按位或操作的结果的数量最多不超过 $32$ 个。这是因为，按位或是一个单调递增的操作。

因此，我们用一个哈希表 $ans$ 记录所有子数组按位或操作的结果，用一个哈希表 $s$ 记录以当前元素结尾的子数组按位或操作的结果，初始时 $s$ 只包含一个元素 $0$。

接下来，我们枚举子数组的结束位置 $i$，那么以 $i$ 结尾的子数组按位或操作的结果，是以 $i-1$ 结尾的子数组按位或操作的结果与 $a[i]$ 进行按位或操作的结果的集合，再加上 $a[i]$ 本身。我们用一个哈希表 $t$ 记录以 $i$ 结尾的子数组按位或操作的结果，然后我们更新 $s = t$，并将 $t$ 中的所有元素加入 $ans$。

最终，我们返回哈希表 $ans$ 中元素的数量即可。

时间复杂度 $O(n \times \log M)$，空间复杂度 $O(n \times \log M)$。其中 $n$ 和 $M$ 分别为数组长度和数组中元素的最大值。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def subarrayBitwiseORs(self, arr: List[int]) -> int:
        ans = set()
        s = set()
        for x in arr:
            s = {x | y for y in s} | {x}
            ans |= s
        return len(ans)
```

#### Java

```java
class Solution {
    public int subarrayBitwiseORs(int[] arr) {
        Set<Integer> ans = new HashSet<>();
        Set<Integer> s = new HashSet<>();
        for (int x : arr) {
            Set<Integer> t = new HashSet<>();
            for (int y : s) {
                t.add(x | y);
            }
            t.add(x);
            ans.addAll(t);
            s = t;
        }
        return ans.size();
    }
}
```

#### C++

```cpp
class Solution {
public:
    int subarrayBitwiseORs(vector<int>& arr) {
        unordered_set<int> ans;
        unordered_set<int> s;
        for (int x : arr) {
            unordered_set<int> t;
            for (int y : s) {
                t.insert(x | y);
            }
            t.insert(x);
            ans.insert(t.begin(), t.end());
            s = move(t);
        }
        return ans.size();
    }
};
```

#### Go

```go
func subarrayBitwiseORs(arr []int) int {
	ans := map[int]bool{}
	s := map[int]bool{}
	for _, x := range arr {
		t := map[int]bool{x: true}
		for y := range s {
			t[x|y] = true
		}
		for y := range t {
			ans[y] = true
		}
		s = t
	}
	return len(ans)
}
```

#### TypeScript

```ts
function subarrayBitwiseORs(arr: number[]): number {
    const ans: Set<number> = new Set();
    const s: Set<number> = new Set();
    for (const x of arr) {
        const t: Set<number> = new Set([x]);
        for (const y of s) {
            t.add(x | y);
        }
        s.clear();
        for (const y of t) {
            ans.add(y);
            s.add(y);
        }
    }
    return ans.size;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
