---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1300-1399/1363.Largest%20Multiple%20of%20Three/README.md
rating: 1822
source: 第 177 场周赛 Q4
tags:
    - 贪心
    - 数组
    - 数学
    - 动态规划
    - 排序
---

<!-- problem:start -->

# [1363. 形成三的最大倍数](https://leetcode.cn/problems/largest-multiple-of-three)

[English Version](/solution/1300-1399/1363.Largest%20Multiple%20of%20Three/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组&nbsp;<code>digits</code>，你可以通过按 <strong>任意顺序</strong> 连接其中某些数字来形成 <strong>3</strong> 的倍数，请你返回所能得到的最大的 3 的倍数。</p>

<p>由于答案可能不在整数数据类型范围内，请以字符串形式返回答案。如果无法得到答案，请返回一个空字符串。返回的结果不应包含不必要的前导零。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>digits = [8,1,9]
<strong>输出：</strong>"981"
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>digits = [8,6,7,1,0]
<strong>输出：</strong>"8760"
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>digits = [1]
<strong>输出：</strong>""
</pre>

<p><strong>示例 4：</strong></p>

<pre>
<strong>输入：</strong>digits = [0,0,0,0,0,0]
<strong>输出：</strong>"0"
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= digits.length &lt;= 10^4</code></li>
	<li><code>0 &lt;= digits[i] &lt;= 9</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：贪心 + 动态规划 + 逆推

我们定义 $f[i][j]$ 表示在前 $i$ 个数中选取若干个数，使得选取的数的和模 $3$ 为 $j$ 的最大长度。为了使得选取的数最大，我们需要尽可能选取更多的数，因此我们需要使得 $f[i][j]$ 尽可能大。我们初始化 $f[0][0] = 0$，其余的 $f[0][j] = -\infty$。

考虑 $f[i][j]$ 如何进行状态转移。我们可以不选取第 $i$ 个数，此时 $f[i][j] = f[i - 1][j]$；我们也可以选取第 $i$ 个数，此时 $f[i][j] = f[i - 1][(j - x_i \bmod 3 + 3) \bmod 3] + 1$，其中 $x_i$ 表示第 $i$ 个数的值。因此我们有如下的状态转移方程：

$$
f[i][j] = \max \{ f[i - 1][j], f[i - 1][(j - x_i \bmod 3 + 3) \bmod 3] + 1 \}
$$

如果 $f[n][0] \le 0$，那么我们无法选取任何数，因此答案字符串为空。否则我们可以通过 $f$ 数组进行逆推，找出选取的数。

定义 $i = n$, $j = 0$，从 $f[i][j]$ 开始逆推，记 $k = (j - x_i \bmod 3 + 3) \bmod 3$，如果 $f[i - 1][k] + 1 = f[i][j]$，那么我们选取了第 $i$ 个数，否则我们没有选取第 $i$ 个数。如果我们选取了第 $i$ 个数，那么我们将 $j$ 更新为 $k$，否则我们保持 $j$ 不变。为了使得同等长度的数最大，我们应该优先选取较大的数，因此，我们在前面首先对数组进行排序。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 为数组的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def largestMultipleOfThree(self, digits: List[int]) -> str:
        digits.sort()
        n = len(digits)
        f = [[-inf] * 3 for _ in range(n + 1)]
        f[0][0] = 0
        for i, x in enumerate(digits, 1):
            for j in range(3):
                f[i][j] = max(f[i - 1][j], f[i - 1][(j - x % 3 + 3) % 3] + 1)
        if f[n][0] <= 0:
            return ""
        arr = []
        j = 0
        for i in range(n, 0, -1):
            k = (j - digits[i - 1] % 3 + 3) % 3
            if f[i - 1][k] + 1 == f[i][j]:
                arr.append(digits[i - 1])
                j = k
        i = 0
        while i < len(arr) - 1 and arr[i] == 0:
            i += 1
        return "".join(map(str, arr[i:]))
```

#### Java

```java
class Solution {
    public String largestMultipleOfThree(int[] digits) {
        Arrays.sort(digits);
        int n = digits.length;
        int[][] f = new int[n + 1][3];
        final int inf = 1 << 30;
        for (var g : f) {
            Arrays.fill(g, -inf);
        }
        f[0][0] = 0;
        for (int i = 1; i <= n; ++i) {
            for (int j = 0; j < 3; ++j) {
                f[i][j] = Math.max(f[i - 1][j], f[i - 1][(j - digits[i - 1] % 3 + 3) % 3] + 1);
            }
        }
        if (f[n][0] <= 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = n, j = 0; i > 0; --i) {
            int k = (j - digits[i - 1] % 3 + 3) % 3;
            if (f[i - 1][k] + 1 == f[i][j]) {
                sb.append(digits[i - 1]);
                j = k;
            }
        }
        int i = 0;
        while (i < sb.length() - 1 && sb.charAt(i) == '0') {
            ++i;
        }
        return sb.substring(i);
    }
}
```

#### C++

```cpp
class Solution {
public:
    string largestMultipleOfThree(vector<int>& digits) {
        sort(digits.begin(), digits.end());
        int n = digits.size();
        int f[n + 1][3];
        memset(f, -0x3f, sizeof(f));
        f[0][0] = 0;
        for (int i = 1; i <= n; ++i) {
            for (int j = 0; j < 3; ++j) {
                f[i][j] = max(f[i - 1][j], f[i - 1][(j - digits[i - 1] % 3 + 3) % 3] + 1);
            }
        }
        if (f[n][0] <= 0) {
            return "";
        }
        string ans;
        for (int i = n, j = 0; i; --i) {
            int k = (j - digits[i - 1] % 3 + 3) % 3;
            if (f[i - 1][k] + 1 == f[i][j]) {
                ans += digits[i - 1] + '0';
                j = k;
            }
        }
        int i = 0;
        while (i < ans.size() - 1 && ans[i] == '0') {
            ++i;
        }
        return ans.substr(i);
    }
};
```

#### Go

```go
func largestMultipleOfThree(digits []int) string {
	sort.Ints(digits)
	n := len(digits)
	const inf = 1 << 30
	f := make([][]int, n+1)
	for i := range f {
		f[i] = make([]int, 3)
		for j := range f[i] {
			f[i][j] = -inf
		}
	}
	f[0][0] = 0
	for i := 1; i <= n; i++ {
		for j := 0; j < 3; j++ {
			f[i][j] = max(f[i-1][j], f[i-1][(j-digits[i-1]%3+3)%3]+1)
		}
	}
	if f[n][0] <= 0 {
		return ""
	}
	ans := []byte{}
	for i, j := n, 0; i > 0; i-- {
		k := (j - digits[i-1]%3 + 3) % 3
		if f[i][j] == f[i-1][k]+1 {
			ans = append(ans, byte('0'+digits[i-1]))
			j = k
		}
	}
	i := 0
	for i < len(ans)-1 && ans[i] == '0' {
		i++
	}
	return string(ans[i:])
}
```

#### TypeScript

```ts
function largestMultipleOfThree(digits: number[]): string {
    digits.sort((a, b) => a - b);
    const n = digits.length;
    const f: number[][] = new Array(n + 1).fill(0).map(() => new Array(3).fill(-Infinity));
    f[0][0] = 0;
    for (let i = 1; i <= n; ++i) {
        for (let j = 0; j < 3; ++j) {
            f[i][j] = Math.max(f[i - 1][j], f[i - 1][(j - (digits[i - 1] % 3) + 3) % 3] + 1);
        }
    }
    if (f[n][0] <= 0) {
        return '';
    }
    const arr: number[] = [];
    for (let i = n, j = 0; i; --i) {
        const k = (j - (digits[i - 1] % 3) + 3) % 3;
        if (f[i - 1][k] + 1 === f[i][j]) {
            arr.push(digits[i - 1]);
            j = k;
        }
    }
    let i = 0;
    while (i < arr.length - 1 && arr[i] === 0) {
        ++i;
    }
    return arr.slice(i).join('');
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
