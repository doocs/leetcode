# [面试题 43. 1 ～ n 整数中 1 出现的次数](https://leetcode.cn/problems/1nzheng-shu-zhong-1chu-xian-de-ci-shu-lcof/)

## 题目描述

<!-- 这里写题目描述 -->

<p>输入一个整数 <code>n</code> ，求1～n这n个整数的十进制表示中1出现的次数。</p>

<p>例如，输入12，1～12这些整数中包含1 的数字有1、10、11和12，1一共出现了5次。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 12
<strong>输出：</strong>5
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 13
<strong>输出：</strong>6</pre>

<p> </p>

<p><strong>限制：</strong></p>

<ul>
	<li><code>1 <= n < 2^31</code></li>
</ul>

<p>注意：本题与主站 233 题相同：<a href="https://leetcode.cn/problems/number-of-digit-one/">https://leetcode.cn/problems/number-of-digit-one/</a></p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：数位 DP**

这道题实际上是求在给定区间 $[l,..r]$ 中，数字中出现 $1$ 个数。个数与数的位数以及每一位上的数字有关。我们可以用数位 DP 的思路来解决这道题。数位 DP 中，数的大小对复杂度的影响很小。

对于区间 $[l,..r]$ 问题，我们一般会将其转化为 $[1,..r]$ 然后再减去 $[1,..l - 1]$ 的问题，即：

$$
ans = \sum_{i=1}^{r} ans_i -  \sum_{i=1}^{l-1} ans_i
$$

不过对于本题而言，我们只需要求出区间 $[1,..r]$ 的值即可。

这里我们用记忆化搜索来实现数位 DP。从起点向下搜索，到最底层得到方案数，一层层向上返回答案并累加，最后从搜索起点得到最终的答案。

基本步骤如下：

1. 将数字 $n$ 转为 int 数组 $a$，其中 $a[0]$ 为最低位，而 $a[i]$ 为最高位；
1. 根据题目信息，设计函数 $dfs()$，对于本题，我们定义 $dfs(pos, cnt, limit)$，其中：

-   `pos` 表示数字的位数，从末位或者第一位开始，一般根据题目的数字构造性质来选择顺序。对于本题，我们选择从高位开始，因此，`pos` 的初始值为 `len`；
-   `cnt` 表示当前数字中包含的 $1$ 的个数。
-   `limit` 表示可填的数字的限制，如果无限制，那么可以选择 $[0,1,..9]$，否则，只能选择 $[0,..a[pos]]$。如果 `limit` 为 `true` 且已经取到了能取到的最大值，那么下一个 `limit` 同样为 `true`；如果 `limit` 为 `true` 但是还没有取到最大值，或者 `limit` 为 `false`，那么下一个 `limit` 为 `false`。

那么答案为 $dfs(i, 0, true)$。

关于函数的实现细节，可以参考下面的代码。

时间复杂度 $O(\log n)$。

相似题目：

-   [357. 统计各位数字都不同的数字个数](/solution/0300-0399/0357.Count%20Numbers%20with%20Unique%20Digits/README.md)
-   [600. 不含连续 1 的非负整数](/solution/0600-0699/0600.Non-negative%20Integers%20without%20Consecutive%20Ones/README.md)
-   [788. 旋转数字](/solution/0700-0799/0788.Rotated%20Digits/README.md)
-   [902. 最大为 N 的数字组合](/solution/0900-0999/0902.Numbers%20At%20Most%20N%20Given%20Digit%20Set/README.md)
-   [1012. 至少有 1 位重复的数字](/solution/1000-1099/1012.Numbers%20With%20Repeated%20Digits/README.md)
-   [2376. 统计特殊整数](/solution/2300-2399/2376.Count%20Special%20Integers/README.md)

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def countDigitOne(self, n: int) -> int:
        @cache
        def dfs(pos, cnt, limit):
            if pos < 0:
                return cnt
            up = a[pos] if limit else 9
            ans = 0
            for i in range(up + 1):
                ans += dfs(pos - 1, cnt + (i == 1), limit and i == up)
            return ans

        a = []
        while n:
            a.append(n % 10)
            n //= 10
        return dfs(len(a) - 1, 0, True)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private int[] a = new int[12];
    private Integer[][] f = new Integer[12][12];

    public int countDigitOne(int n) {
        int i = -1;
        for (; n > 0; n /= 10) {
            a[++i] = n % 10;
        }
        return dfs(i, 0, true);
    }

    private int dfs(int pos, int cnt, boolean limit) {
        if (pos < 0) {
            return cnt;
        }
        if (!limit && f[pos][cnt] != null) {
            return f[pos][cnt];
        }
        int up = limit ? a[pos] : 9;
        int ans = 0;
        for (int i = 0; i <= up; ++i) {
            ans += dfs(pos - 1, cnt + (i == 1 ? 1 : 0), limit && i == up);
        }
        return f[pos][cnt] = ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int countDigitOne(int n) {
        int a[12]{};
        int f[12][12];
        memset(f, -1, sizeof f);
        int i = -1;
        for (; n; n /= 10) {
            a[++i] = n % 10;
        }
        function<int(int, int, bool)> dfs = [&](int pos, int cnt, bool limit) -> int {
            if (pos < 0) {
                return cnt;
            }
            if (!limit && f[pos][cnt] != -1) {
                return f[pos][cnt];
            }
            int up = limit ? a[pos] : 9;
            int ans = 0;
            for (int i = 0; i <= up; ++i) {
                ans += dfs(pos - 1, cnt + (i == 1), limit && i == up);
            }
            return f[pos][cnt] = ans;
        };
        return dfs(i, 0, true);
    }
};
```

```go
func countDigitOne(n int) int {
	a := [12]int{}
	f := [12][12]int{}
	for i := range f {
		for j := range f[i] {
			f[i][j] = -1
		}
	}
	i := -1
	for ; n > 0; n /= 10 {
		i++
		a[i] = n % 10
	}
	var dfs func(int, int, bool) int
	dfs = func(pos, cnt int, limit bool) int {
		if pos < 0 {
			return cnt
		}
		if !limit && f[pos][cnt] != -1 {
			return f[pos][cnt]
		}
		up := 9
		if limit {
			up = a[pos]
		}
		ans := 0
		for i := 0; i <= up; i++ {
			t := 0
			if i == 1 {
				t++
			}
			ans += dfs(pos-1, cnt+t, limit && i == up)
		}
		f[pos][cnt] = ans
		return ans
	}
	return dfs(i, 0, true)
}
```

### **JavaScript**

```js
/**
 * @param {number} n
 * @return {number}
 */
var countDigitOne = function (n) {
    let res = 0;
    let i = 1;
    while (i <= n) {
        let high = ~~(n / i / 10);
        let cur = ~~(n / i) % 10;
        let low = n - ~~(n / i) * i;
        switch (cur) {
            case 0:
                res += high * i;
                break;
            case 1:
                res += high * i + low + 1;
                break;
            default:
                res += (high + 1) * i;
        }
        i *= 10;
    }
    return res;
};
```

### **C#**

```cs
public class Solution {
    public int CountDigitOne(int n) {
        long mulk = 1;
        int ans = 0;
        for (int k = 0; n >= mulk; ++k) {
            ans += (int) (n / (mulk * 10) * mulk) + (int) Math.Min(Math.Max(n % (mulk * 10) - mulk + 1, 0), mulk);
            mulk *= 10;
        }
        return ans;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
