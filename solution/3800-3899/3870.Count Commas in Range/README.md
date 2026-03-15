---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3800-3899/3870.Count%20Commas%20in%20Range/README.md
---

<!-- problem:start -->

# [3870. 统计范围内的逗号](https://leetcode.cn/problems/count-commas-in-range)

[English Version](/solution/3800-3899/3870.Count%20Commas%20in%20Range/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数 <code>n</code>。</p>

<p>返回将所有从 <code>[1, n]</code>（包含两端）范围内的整数以&nbsp;<strong>标准&nbsp;</strong>数字格式书写时所用到的<strong>&nbsp;逗号总数</strong>。</p>

<p>在<strong>&nbsp;标准&nbsp;</strong>格式中：</p>

<ul>
	<li>从右边开始，每&nbsp;<strong>三位&nbsp;</strong>数字后插入一个逗号。</li>
	<li>位数&nbsp;<strong>少于四位&nbsp;</strong>的数字不包含逗号。</li>
</ul>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">n = 1002</span></p>

<p><strong>输出：</strong> <span class="example-io">3</span></p>

<p><strong>解释：</strong></p>

<p>数字 <code>"1,000"</code>、<code>"1,001"</code> 和 <code>"1,002"</code> 每个都包含一个逗号，总计 3 个逗号。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">n = 998</span></p>

<p><strong>输出：</strong> <span class="example-io">0</span></p>

<p><strong>解释：</strong></p>

<p>从 1 到 998 的所有数字位数都少于四位，因此没有使用逗号。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：脑筋急转弯

从 1 到 999 的数字都不包含逗号，因此当 $n$ 小于等于 999 时，答案为 0。

由于 $n$ 的范围是 $[1, 10^5]$，当 $n$ 大于等于 1000 时，每个数字都包含一个逗号，此时答案为 $n - 999$。

因此，答案为 $\max(0, n - 999)$。

时间复杂度 $O(1)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countCommas(self, n: int) -> int:
        return max(0, n - 999)
```

#### Java

```java
class Solution {
    public int countCommas(int n) {
        return Math.max(0, n - 999);
    }
}
```

#### C++

```cpp
class Solution {
public:
    int countCommas(int n) {
        return max(0, n - 999);
    }
};
```

#### Go

```go
func countCommas(n int) int {
	return max(0, n-999)
}
```

#### TypeScript

```ts
function countCommas(n: number): number {
    return Math.max(0, n - 999);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
