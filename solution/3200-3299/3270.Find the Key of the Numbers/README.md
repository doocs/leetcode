---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3200-3299/3270.Find%20the%20Key%20of%20the%20Numbers/README.md
rating: 1205
source: 第 138 场双周赛 Q1
tags:
    - 数学
---

<!-- problem:start -->

# [3270. 求出数字答案](https://leetcode.cn/problems/find-the-key-of-the-numbers)

[English Version](/solution/3200-3299/3270.Find%20the%20Key%20of%20the%20Numbers/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你三个 <strong>正</strong>&nbsp;整数&nbsp;<code>num1</code>&nbsp;，<code>num2</code>&nbsp;和&nbsp;<code>num3</code>&nbsp;。</p>

<p>数字 <code>num1</code>&nbsp;，<code>num2</code>&nbsp;和 <code>num3</code>&nbsp;的数字答案 <code>key</code>&nbsp;是一个四位数，定义如下：</p>

<ul>
	<li>一开始，如果有数字 <strong>少于</strong>&nbsp;四位数，给它补 <strong>前导 0 </strong>。</li>
	<li>答案 <code>key</code>&nbsp;的第&nbsp;<code>i</code>&nbsp;个数位（<code>1 &lt;= i &lt;= 4</code>）为&nbsp;<code>num1</code>&nbsp;，<code>num2</code>&nbsp;和&nbsp;<code>num3</code>&nbsp;第&nbsp;<code>i</code>&nbsp;个数位中的&nbsp;<strong>最小</strong>&nbsp;值。</li>
</ul>

<p>请你返回三个数字 <strong>没有</strong>&nbsp;前导 0 的数字答案。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>num1 = 1, num2 = 10, num3 = 1000</span></p>

<p><span class="example-io"><b>输出：</b>0</span></p>

<p><b>解释：</b></p>

<p>补前导 0 后，<code>num1</code>&nbsp;变为&nbsp;<code>"0001"</code>&nbsp;，<code>num2</code> 变为&nbsp;<code>"0010"</code>&nbsp;，<code>num3</code>&nbsp;保持不变，为&nbsp;<code>"1000"</code>&nbsp;。</p>

<ul>
	<li>数字答案 <code>key</code>&nbsp;的第&nbsp;<code>1</code>&nbsp;个数位为&nbsp;<code>min(0, 0, 1)</code>&nbsp;。</li>
	<li>数字答案 <code>key</code>&nbsp;的第&nbsp;<code>2</code>&nbsp;个数位为&nbsp;<code>min(0, 0, 0)</code>&nbsp;。</li>
	<li>数字答案 <code>key</code>&nbsp;的第 <code>3</code> 个数位为&nbsp;<code>min(0, 1, 0)</code>&nbsp;。</li>
	<li>数字答案 <code>key</code>&nbsp;的第 <code>4</code> 个数位为&nbsp;<code>min(1, 0, 0)</code>&nbsp;。</li>
</ul>

<p>所以数字答案为&nbsp;<code>"0000"</code>&nbsp;，也就是 0 。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">num1 = 987, num2 = 879, num3 = 798</span></p>

<p><span class="example-io"><b>输出：</b>777</span></p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>num1 = 1, num2 = 2, num3 = 3</span></p>

<p><span class="example-io"><b>输出：</b>1</span></p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= num1, num2, num3 &lt;= 9999</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

我们可以直接模拟这个过程，定义一个变量 $\textit{ans}$ 用于存储答案，定义一个变量 $\textit{k}$ 用于表示当前位数，其中 $\textit{k} = 1$ 表示个位数，而 $\textit{k} = 10$ 表示十位数，以此类推。

我们从个位数开始，对于每一位，我们分别计算 $\textit{num1}$, $\textit{num2}$ 和 $\textit{num3}$ 的当前位数，取三者的最小值，然后将这个最小值乘以 $\textit{k}$ 加到答案上。然后将 $\textit{k}$ 乘以 10，继续计算下一位。

最后返回答案即可。

时间复杂度 $O(1)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def generateKey(self, num1: int, num2: int, num3: int) -> int:
        ans, k = 0, 1
        for _ in range(4):
            x = min(num1 // k % 10, num2 // k % 10, num3 // k % 10)
            ans += x * k
            k *= 10
        return ans
```

#### Java

```java
class Solution {
    public int generateKey(int num1, int num2, int num3) {
        int ans = 0, k = 1;
        for (int i = 0; i < 4; ++i) {
            int x = Math.min(Math.min(num1 / k % 10, num2 / k % 10), num3 / k % 10);
            ans += x * k;
            k *= 10;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int generateKey(int num1, int num2, int num3) {
        int ans = 0, k = 1;
        for (int i = 0; i < 4; ++i) {
            int x = min({num1 / k % 10, num2 / k % 10, num3 / k % 10});
            ans += x * k;
            k *= 10;
        }
        return ans;
    }
};
```

#### Go

```go
func generateKey(num1 int, num2 int, num3 int) (ans int) {
	k := 1
	for i := 0; i < 4; i++ {
		x := min(min(num1/k%10, num2/k%10), num3/k%10)
		ans += x * k
		k *= 10
	}
	return
}
```

#### TypeScript

```ts
function generateKey(num1: number, num2: number, num3: number): number {
    let [ans, k] = [0, 1];
    for (let i = 0; i < 4; ++i) {
        const x = Math.min(((num1 / k) | 0) % 10, ((num2 / k) | 0) % 10, ((num3 / k) | 0) % 10);
        ans += x * k;
        k *= 10;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
