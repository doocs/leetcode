---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1000-1099/1006.Clumsy%20Factorial/README.md
rating: 1407
source: 第 127 场周赛 Q2
tags:
    - 栈
    - 数学
    - 模拟
---

<!-- problem:start -->

# [1006. 笨阶乘](https://leetcode.cn/problems/clumsy-factorial)

[English Version](/solution/1000-1099/1006.Clumsy%20Factorial/README_EN.md)

## 题目描述

<!-- description:start -->

<p>通常，正整数 <code>n</code> 的阶乘是所有小于或等于 <code>n</code> 的正整数的乘积。例如，<code>factorial(10) = 10 * 9 * 8 * 7 * 6 * 5 * 4 * 3 * 2 * 1</code>。</p>

<p>相反，我们设计了一个笨阶乘 <code>clumsy</code>：在整数的递减序列中，我们以一个固定顺序的操作符序列来依次替换原有的乘法操作符：乘法(*)，除法(/)，加法(+)和减法(-)。</p>

<p>例如，<code>clumsy(10) = 10 * 9 / 8 + 7 - 6 * 5 / 4 + 3 - 2 * 1</code>。然而，这些运算仍然使用通常的算术运算顺序：我们在任何加、减步骤之前执行所有的乘法和除法步骤，并且按从左到右处理乘法和除法步骤。</p>

<p>另外，我们使用的除法是地板除法（<em>floor division</em>），所以&nbsp;<code>10 * 9 / 8</code>&nbsp;等于&nbsp;<code>11</code>。这保证结果是一个整数。</p>

<p>实现上面定义的笨函数：给定一个整数 <code>N</code>，它返回 <code>N</code> 的笨阶乘。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>4
<strong>输出：</strong>7
<strong>解释：</strong>7 = 4 * 3 / 2 + 1
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>10
<strong>输出：</strong>12
<strong>解释：</strong>12 = 10 * 9 / 8 + 7 - 6 * 5 / 4 + 3 - 2 * 1
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li><code>1 &lt;= N &lt;= 10000</code></li>
	<li><code>-2^31 &lt;= answer &lt;= 2^31 - 1</code>&nbsp; （答案保证符合 32 位整数。）</li>
</ol>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：栈 + 模拟

笨阶乘的计算过程可以看作是一个栈的模拟过程。

我们定义一个栈 `stk`，初始时我们将 $n$ 入栈，定义一个变量 $k$，表示当前的操作符，初始时 $k = 0$。

然后我们从 $n-1$ 开始，枚举 $x$，根据当前的 $k$ 的值，决定如何处理 $x$：

-   当 $k = 0$ 时，表示乘法操作，我们将栈顶元素出栈，与 $x$ 相乘后再入栈；
-   当 $k = 1$ 时，表示除法操作，我们将栈顶元素出栈，与 $x$ 相除后取整数部分再入栈；
-   当 $k = 2$ 时，表示加法操作，我们直接将 $x$ 入栈；
-   当 $k = 3$ 时，表示减法操作，我们将 $-x$ 入栈。

接着我们更新 $k = (k + 1) \mod 4$。

最后，我们将栈中的元素累加即为答案。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为题目给定的整数 $N$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def clumsy(self, n: int) -> int:
        k = 0
        stk = [n]
        for x in range(n - 1, 0, -1):
            if k == 0:
                stk.append(stk.pop() * x)
            elif k == 1:
                stk.append(int(stk.pop() / x))
            elif k == 2:
                stk.append(x)
            else:
                stk.append(-x)
            k = (k + 1) % 4
        return sum(stk)
```

#### Java

```java
class Solution {
    public int clumsy(int n) {
        Deque<Integer> stk = new ArrayDeque<>();
        stk.push(n);
        int k = 0;
        for (int x = n - 1; x > 0; --x) {
            if (k == 0) {
                stk.push(stk.pop() * x);
            } else if (k == 1) {
                stk.push(stk.pop() / x);
            } else if (k == 2) {
                stk.push(x);
            } else {
                stk.push(-x);
            }
            k = (k + 1) % 4;
        }
        return stk.stream().mapToInt(Integer::intValue).sum();
    }
}
```

#### C++

```cpp
class Solution {
public:
    int clumsy(int n) {
        stack<int> stk;
        stk.push(n);
        int k = 0;
        for (int x = n - 1; x; --x) {
            if (k == 0) {
                stk.top() *= x;
            } else if (k == 1) {
                stk.top() /= x;
            } else if (k == 2) {
                stk.push(x);
            } else {
                stk.push(-x);
            }
            k = (k + 1) % 4;
        }
        int ans = 0;
        while (!stk.empty()) {
            ans += stk.top();
            stk.pop();
        }
        return ans;
    }
};
```

#### Go

```go
func clumsy(n int) (ans int) {
	stk := []int{n}
	k := 0
	for x := n - 1; x > 0; x-- {
		switch k {
		case 0:
			stk[len(stk)-1] *= x
		case 1:
			stk[len(stk)-1] /= x
		case 2:
			stk = append(stk, x)
		case 3:
			stk = append(stk, -x)
		}
		k = (k + 1) % 4
	}
	for _, x := range stk {
		ans += x
	}
	return
}
```

#### TypeScript

```ts
function clumsy(n: number): number {
    const stk: number[] = [n];
    let k = 0;
    for (let x = n - 1; x; --x) {
        if (k === 0) {
            stk.push(stk.pop()! * x);
        } else if (k === 1) {
            stk.push((stk.pop()! / x) | 0);
        } else if (k === 2) {
            stk.push(x);
        } else {
            stk.push(-x);
        }
        k = (k + 1) % 4;
    }
    return stk.reduce((acc, cur) => acc + cur, 0);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
