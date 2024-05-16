---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1000-1099/1088.Confusing%20Number%20II/README.md
rating: 2076
source: 第 2 场双周赛 Q4
tags:
    - 数学
    - 回溯
---

# [1088. 易混淆数 II 🔒](https://leetcode.cn/problems/confusing-number-ii)

[English Version](/solution/1000-1099/1088.Confusing%20Number%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p><strong>易混淆数</strong>（Confusing Number）指的是一个数字在整体旋转 <code>180°</code> 以后，能够得到一个和原来&nbsp;<strong>不同&nbsp;</strong>的数，且 <strong>新数字的每一位都应该是有效的</strong>。</p>

<p>本题我们会将数字旋转 <code>180°</code> 来生成一个新的数字。</p>

<ul>
	<li>当 <code>0、1、6、8、9</code> 旋转 <code>180°</code> 以后，我们得到的新数字分别为&nbsp;0、1、9、8、6。</li>
	<li>当&nbsp;<code>2、3、4、5、7</code> 旋转 <code>180°</code> 后，是 <strong>无法</strong> 得到任何数字的。</li>
</ul>

<p>请注意，在旋转一个数字之后，我们可以忽略前导零。</p>

<ul>
	<li>例如，在旋转 <code>8000</code> 之后，我们有 <code>0008</code> ，它被认为只是 <code>8</code> 。</li>
</ul>

<p>给出正整数&nbsp;<code>n</code>，请你返回&nbsp;&nbsp;<em><code>[1, n]</code>&nbsp;范围内的 <strong>易混淆数</strong> 的数量&nbsp;</em>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 20
<strong>输出：</strong>6
<strong>解释：</strong>易混淆数为 [6,9,10,16,18,19]。
6 转换为 9
9 转换为 6
10 转换为 01 也就是 1
16 转换为 91
18 转换为 81
19 转换为 61
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 100
<strong>输出：</strong>19
<strong>解释：</strong>易混淆数为 [6,9,10,16,18,19,60,61,66,68,80,81,86,89,90,91,98,99,100]。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

### 方法一：数位 DP

我们先将数字 $n$ 转成字符串 $s$。

接下来，我们定义一个函数 $check(x)$，用来判断 $x$ 在旋转 $180^\circ$ 之后是否变成了一个不同的数。如果 $x$ 在旋转 $180^\circ$ 之后变成了一个不同的数，那么我们就称 $x$ 是一个易混淆数。

然后，我们定义另一个函数 $dfs(pos, limit, x)$，用于搜索从高位到低位的每一位。其中：

-   参数 $pos$ 表示当前搜索到的位置，初始时为 $0$；
-   参数 $limit$ 表示当前搜索的数是否受到上界的限制，初始时为 $true$；
-   参数 $x$ 表示当前搜索的数，初始时为 $0$。

在 $dfs(pos, limit, x)$ 中，如果 $pos \geq len(s)$，那么我们就判断 $x$ 是否是一个易混淆数，如果是则返回 $1$，否则返回 $0$。

否则，我们计算出当前位置上的数字的上界 $up$，然后枚举当前位置上的数字 $i$，如果 $i$ 在旋转 $180^\circ$ 之后不是一个数字，那么我们就直接跳过这个数字。否则，我们将 $x$ 更新为 $x \times 10 + i$，并根据 $limit$ 的值决定下一步搜索的时候是否受到上界的限制，最后将答案返回。

最终的答案即为 $dfs(0, true, 0)$。

时间复杂度 $O(5^{\log_{10}n})$，空间复杂度 $O(\log_{10}n)$。其中 $5^{\log_{10}n}$ 表示 $n$ 的位数。

<!-- tabs:start -->

```python
class Solution:
    def confusingNumberII(self, n: int) -> int:
        def check(x: int) -> bool:
            y, t = 0, x
            while t:
                t, v = divmod(t, 10)
                y = y * 10 + d[v]
            return x != y

        def dfs(pos: int, limit: bool, x: int) -> int:
            if pos >= len(s):
                return int(check(x))
            up = int(s[pos]) if limit else 9
            ans = 0
            for i in range(up + 1):
                if d[i] != -1:
                    ans += dfs(pos + 1, limit and i == up, x * 10 + i)
            return ans

        d = [0, 1, -1, -1, -1, -1, 9, -1, 8, 6]
        s = str(n)
        return dfs(0, True, 0)
```

```java
class Solution {
    private final int[] d = {0, 1, -1, -1, -1, -1, 9, -1, 8, 6};
    private String s;

    public int confusingNumberII(int n) {
        s = String.valueOf(n);
        return dfs(0, 1, 0);
    }

    private int dfs(int pos, int limit, int x) {
        if (pos >= s.length()) {
            return check(x) ? 1 : 0;
        }
        int up = limit == 1 ? s.charAt(pos) - '0' : 9;
        int ans = 0;
        for (int i = 0; i <= up; ++i) {
            if (d[i] != -1) {
                ans += dfs(pos + 1, limit == 1 && i == up ? 1 : 0, x * 10 + i);
            }
        }
        return ans;
    }

    private boolean check(int x) {
        int y = 0;
        for (int t = x; t > 0; t /= 10) {
            int v = t % 10;
            y = y * 10 + d[v];
        }
        return x != y;
    }
}
```

```cpp
class Solution {
public:
    int confusingNumberII(int n) {
        string s = to_string(n);
        int d[10] = {0, 1, -1, -1, -1, -1, 9, -1, 8, 6};
        auto check = [&](int x) -> bool {
            int y = 0;
            for (int t = x; t; t /= 10) {
                int v = t % 10;
                y = y * 10 + d[v];
            }
            return x != y;
        };
        function<int(int, int, int)> dfs = [&](int pos, int limit, int x) -> int {
            if (pos >= s.size()) {
                return check(x);
            }
            int up = limit ? s[pos] - '0' : 9;
            int ans = 0;
            for (int i = 0; i <= up; ++i) {
                if (d[i] != -1) {
                    ans += dfs(pos + 1, limit && i == up, x * 10 + i);
                }
            }
            return ans;
        };
        return dfs(0, 1, 0);
    }
};
```

```go
func confusingNumberII(n int) int {
	d := [10]int{0, 1, -1, -1, -1, -1, 9, -1, 8, 6}
	s := strconv.Itoa(n)
	check := func(x int) bool {
		y := 0
		for t := x; t > 0; t /= 10 {
			v := t % 10
			y = y*10 + d[v]
		}
		return x != y
	}
	var dfs func(pos int, limit bool, x int) int
	dfs = func(pos int, limit bool, x int) (ans int) {
		if pos >= len(s) {
			if check(x) {
				return 1
			}
			return 0
		}
		up := 9
		if limit {
			up = int(s[pos] - '0')
		}
		for i := 0; i <= up; i++ {
			if d[i] != -1 {
				ans += dfs(pos+1, limit && i == up, x*10+i)
			}
		}
		return
	}
	return dfs(0, true, 0)
}
```

```ts
function confusingNumberII(n: number): number {
    const s = n.toString();
    const d: number[] = [0, 1, -1, -1, -1, -1, 9, -1, 8, 6];
    const check = (x: number) => {
        let y = 0;
        for (let t = x; t > 0; t = Math.floor(t / 10)) {
            const v = t % 10;
            y = y * 10 + d[v];
        }
        return x !== y;
    };
    const dfs = (pos: number, limit: boolean, x: number): number => {
        if (pos >= s.length) {
            return check(x) ? 1 : 0;
        }
        const up = limit ? parseInt(s[pos]) : 9;
        let ans = 0;
        for (let i = 0; i <= up; ++i) {
            if (d[i] !== -1) {
                ans += dfs(pos + 1, limit && i === up, x * 10 + i);
            }
        }
        return ans;
    };
    return dfs(0, true, 0);
}
```

<!-- tabs:end -->

<!-- end -->
