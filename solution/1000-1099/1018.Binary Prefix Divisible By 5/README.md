---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1000-1099/1018.Binary%20Prefix%20Divisible%20By%205/README.md
rating: 1376
source: 第 130 场周赛 Q1
tags:
    - 位运算
    - 数组
---

<!-- problem:start -->

# [1018. 可被 5 整除的二进制前缀](https://leetcode.cn/problems/binary-prefix-divisible-by-5)

[English Version](/solution/1000-1099/1018.Binary%20Prefix%20Divisible%20By%205/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给定一个二进制数组 <code>nums</code> (&nbsp;<strong>索引从0开始&nbsp;</strong>)。</p>

<p>我们将<code>x<sub>i</sub></code>&nbsp;定义为其二进制表示形式为子数组&nbsp;<code>nums[0..i]</code>&nbsp;(从最高有效位到最低有效位)。</p>

<ul>
	<li>例如，如果 <code>nums =[1,0,1]</code> ，那么&nbsp;<code>x<sub>0</sub>&nbsp;= 1</code>,&nbsp;<code>x<sub>1</sub>&nbsp;= 2</code>, 和&nbsp;<code>x<sub>2</sub>&nbsp;= 5</code>。</li>
</ul>

<p>返回布尔值列表&nbsp;<code>answer</code>，只有当&nbsp;<code>x<sub>i</sub></code><em>&nbsp;</em>可以被 <code>5</code>&nbsp;整除时，答案&nbsp;<code>answer[i]</code> 为&nbsp;<code>true</code>，否则为 <code>false</code>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [0,1,1]
<strong>输出：</strong>[true,false,false]
<strong>解释：</strong>
输入数字为 0, 01, 011；也就是十进制中的 0, 1, 3 。只有第一个数可以被 5 整除，因此 answer[0] 为 true 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,1,1]
<strong>输出：</strong>[false,false,false]
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code><sup>&nbsp;</sup></li>
	<li><code>nums[i]</code>&nbsp;仅为&nbsp;<code>0</code>&nbsp;或&nbsp;<code>1</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

我们用一个变量 $x$ 来表示当前的二进制前缀，然后遍历数组 $nums$，对于每个元素 $v$，我们将 $x$ 左移一位，然后加上 $v$，再对 $5$ 取模，判断是否等于 $0$，如果等于 $0$，则说明当前的二进制前缀可以被 $5$ 整除，我们将 $\textit{true}$ 加入答案数组，否则将 $\textit{false}$ 加入答案数组。

时间复杂度 $O(n)$，忽略答案数组的空间消耗，空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def prefixesDivBy5(self, nums: List[int]) -> List[bool]:
        ans = []
        x = 0
        for v in nums:
            x = (x << 1 | v) % 5
            ans.append(x == 0)
        return ans
```

#### Java

```java
class Solution {
    public List<Boolean> prefixesDivBy5(int[] nums) {
        List<Boolean> ans = new ArrayList<>();
        int x = 0;
        for (int v : nums) {
            x = (x << 1 | v) % 5;
            ans.add(x == 0);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<bool> prefixesDivBy5(vector<int>& nums) {
        vector<bool> ans;
        int x = 0;
        for (int v : nums) {
            x = (x << 1 | v) % 5;
            ans.push_back(x == 0);
        }
        return ans;
    }
};
```

#### Go

```go
func prefixesDivBy5(nums []int) (ans []bool) {
	x := 0
	for _, v := range nums {
		x = (x<<1 | v) % 5
		ans = append(ans, x == 0)
	}
	return
}
```

#### TypeScript

```ts
function prefixesDivBy5(nums: number[]): boolean[] {
    const ans: boolean[] = [];
    let x = 0;
    for (const v of nums) {
        x = ((x << 1) | v) % 5;
        ans.push(x === 0);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
