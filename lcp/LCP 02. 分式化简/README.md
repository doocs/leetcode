---
comment: true
edit_url: https://github.com/doocs/leetcode/edit/main/lcp/LCP%2002.%20%E5%88%86%E5%BC%8F%E5%8C%96%E7%AE%80/README.md
---

# [LCP 02. 分式化简](https://leetcode.cn/problems/deep-dark-fraction)

## 题目描述

<!-- 这里写题目描述 -->

<p>有一个同学在学习分式。他需要将一个连分数化成最简分数，你能帮助他吗？</p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/lcp/LCP%2002.%20分式化简/images/fraction_example_1.jpg" style="height: 195px; width: 480px;" /></p>

<p>连分数是形如上图的分式。在本题中，所有系数都是大于等于0的整数。</p>

<p> </p>

<p>输入的<code>cont</code>代表连分数的系数（<code>cont[0]</code>代表上图的<code>a<sub>0</sub></code>，以此类推）。返回一个长度为2的数组<code>[n, m]</code>，使得连分数的值等于<code>n / m</code>，且<code>n, m</code>最大公约数为1。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>cont = [3, 2, 0, 2]
<strong>输出：</strong>[13, 4]
<strong>解释：</strong>原连分数等价于3 + (1 / (2 + (1 / (0 + 1 / 2))))。注意[26, 8], [-13, -4]都不是正确答案。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>cont = [0, 0, 3]
<strong>输出：</strong>[3, 1]
<strong>解释：</strong>如果答案是整数，令分母为1即可。</pre>

<p> </p>

<p><strong>限制：</strong></p>

<ol>
	<li><code>cont[i] >= 0</code></li>
	<li><code>1 <= cont的长度 <= 10</code></li>
	<li><code>cont</code>最后一个元素不等于0</li>
	<li>答案的<code>n, m</code>的取值都能被32位int整型存下（即不超过<code>2 ^ 31 - 1</code>）。</li>
</ol>

## 解法

### 方法一：DFS + 数学

我们设计一个函数 $dfs(i)$，表示从下标 $i$ 开始到最后一个元素的连分数的值，那么答案就是 $dfs(0)$。

函数 $dfs(i)$ 的执行逻辑如下：

如果 $i = n - 1$，只有一个元素，那么它的值就是 $cont[i]$，分母为 $1$，返回 $[cont[i], 1]$。

否则，我们递归调用 $dfs(i + 1)$，记返回值为 $[a, b]$，那么 $dfs(i)= 1 + \frac{1}{\frac{a}{b}}$，即 $dfs(i) = \frac{a \times cont[i] + b}{a}$，分子为 $x = a \times cont[i] + b$，分母为 $y = a$，我们求出 $x$ 和 $y$ 的最大公约数 $g$，最终返回 $[\frac{x}{g}, \frac{y}{g}]$。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为 $cont$ 的长度。

<!-- tabs:start -->

```python
class Solution:
    def fraction(self, cont: List[int]) -> List[int]:
        def dfs(i: int) -> List[int]:
            if i == len(cont) - 1:
                return [cont[i], 1]
            a, b = dfs(i + 1)
            x, y = a * cont[i] + b, a
            g = gcd(x, y)
            return [x // g, y // g]

        return dfs(0)
```

```java
class Solution {
    private int[] cont;

    public int[] fraction(int[] cont) {
        this.cont = cont;
        return dfs(0);
    }

    private int[] dfs(int i) {
        if (i == cont.length - 1) {
            return new int[] {cont[i], 1};
        }
        int[] next = dfs(i + 1);
        int a = next[0], b = next[1];
        int x = a * cont[i] + b, y = a;
        int g = gcd(x, y);
        return new int[] {x / g, y / g};
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
```

```cpp
class Solution {
public:
    vector<int> fraction(vector<int>& cont) {
        function<vector<int>(int)> dfs = [&](int i) {
            if (i == cont.size() - 1) {
                return vector<int>{cont[i], 1};
            }
            vector<int> next = dfs(i + 1);
            int a = next[0], b = next[1];
            int x = a * cont[i] + b;
            int y = a;
            int g = __gcd(x, y);
            return vector<int>{x / g, y / g};
        };
        return dfs(0);
    }
};
```

```go
func fraction(cont []int) []int {
	var dfs func(i int) []int
	dfs = func(i int) []int {
		if i == len(cont)-1 {
			return []int{cont[i], 1}
		}
		ans := dfs(i + 1)
		a, b := ans[0], ans[1]
		return []int{a*cont[i] + b, a}
	}
	return dfs(0)
}
```

```ts
function fraction(cont: number[]): number[] {
    const dfs = (i: number): number[] => {
        if (i === cont.length - 1) {
            return [cont[i], 1];
        }
        const [a, b] = dfs(i + 1);
        const [x, y] = [a * cont[i] + b, a];
        const g = gcd(x, y);
        return [x / g, y / g];
    };
    const gcd = (a: number, b: number): number => {
        return b === 0 ? a : gcd(b, a % b);
    };
    return dfs(0);
}
```

```js
/**
 * @param {number[]} cont
 * @return {number[]}
 */
var fraction = function (cont) {
    const dfs = i => {
        if (i === cont.length - 1) {
            return [cont[i], 1];
        }
        const [a, b] = dfs(i + 1);
        const [x, y] = [a * cont[i] + b, a];
        const g = gcd(x, y);
        return [Math.floor(x / g), Math.floor(y / g)];
    };
    const gcd = (a, b) => {
        return b === 0 ? a : gcd(b, a % b);
    };
    return dfs(0);
};
```

<!-- tabs:end -->

<!-- end -->
