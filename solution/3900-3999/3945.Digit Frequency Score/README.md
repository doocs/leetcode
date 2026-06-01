---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3945.Digit%20Frequency%20Score/README.md
---

<!-- problem:start -->

# [3945. 计算数字频率得分](https://leetcode.cn/problems/digit-frequency-score)

[English Version](/solution/3900-3999/3945.Digit%20Frequency%20Score/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数 <code>n</code>。</p>

<p><code>n</code> 的<strong>&nbsp;得分&nbsp;</strong>定义为：对所有&nbsp;<strong>不同</strong>&nbsp;数字 <code>d</code>，计算 <code>d * freq(d)</code> 的总和，其中 <code>freq(d)</code> 表示数字 <code>d</code> 在 <code>n</code> 中出现的次数。</p>

<p>返回一个整数，表示 <code>n</code> 的得分。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">n = 122</span></p>

<p><strong>输出：</strong> <span class="example-io">5</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>数字 1 出现 1 次，贡献为 <code>1 * 1 = 1</code>。</li>
	<li>数字 2 出现 2 次，贡献为 <code>2 * 2 = 4</code>。</li>
	<li>因此，<code>n</code> 的得分为 <code>1 + 4 = 5</code>。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">n = 101</span></p>

<p><strong>输出：</strong> <span class="example-io">2</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>数字 0 出现 1 次，贡献为 <code>0 * 1 = 0</code>。</li>
	<li>数字 1 出现 2 次，贡献为 <code>1 * 2 = 2</code>。</li>
	<li>因此，<code>n</code> 的得分为 2。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

题目相当于求一个数的各个位数字之和。我们可以通过不断取模和除以 10 来获取每一位的数字，并累加到答案中。

时间复杂度 $O(\log n)$，其中 $\log n$ 是数字 $n$ 的位数。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def digitFrequencyScore(self, n: int) -> int:
        ans = 0
        while n:
            n, x = divmod(n, 10)
            ans += x
        return ans
```

#### Java

```java
class Solution {
    public int digitFrequencyScore(int n) {
        int ans = 0;
        for (; n > 0; n /= 10) {
            ans += n % 10;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int digitFrequencyScore(int n) {
        int ans = 0;
        for (; n > 0; n /= 10) {
            ans += n % 10;
        }
        return ans;
    }
};
```

#### Go

```go
func digitFrequencyScore(n int) (ans int) {
	for ; n > 0; n /= 10 {
		ans += n % 10
	}
	return
}
```

#### TypeScript

```ts
function digitFrequencyScore(n: number): number {
    let ans = 0;
    for (; n; n = Math.floor(n / 10)) {
        ans += n % 10;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
