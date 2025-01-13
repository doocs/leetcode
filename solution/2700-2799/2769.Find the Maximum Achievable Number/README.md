---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2700-2799/2769.Find%20the%20Maximum%20Achievable%20Number/README.md
rating: 1191
source: 第 353 场周赛 Q1
tags:
    - 数学
---

<!-- problem:start -->

# [2769. 找出最大的可达成数字](https://leetcode.cn/problems/find-the-maximum-achievable-number)

[English Version](/solution/2700-2799/2769.Find%20the%20Maximum%20Achievable%20Number/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你两个整数 <code>num</code> 和 <code>t</code> 。如果整数 <code>x</code> 可以在执行下述操作 <strong>不超过</strong> <code>t</code> 次的情况下变为与 <code>num</code> 相等，则称其为 <strong>可达成数字</strong> ：</p>

<ul>
	<li>每次操作将 <code>x</code> 的值增加或减少 <code>1</code> ，同时可以选择将 <code>num</code> 的值增加或减少 <code>1</code> 。</li>
</ul>

<p>返回所有可达成数字中的 <strong>最大</strong> 值 <code>x</code>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong>num = 4, t = 1</p>

<p><strong>输出：</strong>6</p>

<p><strong>解释：</strong></p>

<p>执行下述操作可以使最大可达成数字等于 <code>num</code> ：</p>

<ul>
	<li>最大可达成数字减少 1 ，同时 <code>num</code> 增加 1 。</li>
</ul>
</div>

<p><strong>示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong>num = 3, t = 2</p>

<p><strong>输出：</strong>7</p>

<p><strong>解释：</strong></p>

<p>执行两次下述操作可以使最大可达成数字等于 num ：</p>

<ul>
	<li>最大可达成数字减少 1 ，同时 <code>num</code> 增加 1。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= num, t&nbsp;&lt;= 50</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：数学

我们注意到，每次操作可以将 $x$ 减少 $1$，同时将 $num$ 增加 $1$，这样 $x$ 和 $num$ 的差值就会减少 $2$，而最多可以操作 $t$ 次，所以最大可达成数字为 $num + t \times 2$。

时间复杂度 $O(1)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def theMaximumAchievableX(self, num: int, t: int) -> int:
        return num + t * 2
```

#### Java

```java
class Solution {
    public int theMaximumAchievableX(int num, int t) {
        return num + t * 2;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int theMaximumAchievableX(int num, int t) {
        return num + t * 2;
    }
};
```

#### Go

```go
func theMaximumAchievableX(num int, t int) int {
	return num + t*2
}
```

#### TypeScript

```ts
function theMaximumAchievableX(num: number, t: number): number {
    return num + t * 2;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
