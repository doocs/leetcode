# [2698. 求一个整数的惩罚数](https://leetcode.cn/problems/find-the-punishment-number-of-an-integer)

[English Version](/solution/2600-2699/2698.Find%20the%20Punishment%20Number%20of%20an%20Integer/README_EN.md)

<!-- tags:数学,回溯 -->

<!-- difficulty:中等 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个正整数&nbsp;<code>n</code>&nbsp;，请你返回&nbsp;<code>n</code>&nbsp;的&nbsp;<strong>惩罚数</strong>&nbsp;。</p>

<p><code>n</code>&nbsp;的 <strong>惩罚数</strong>&nbsp;定义为所有满足以下条件 <code>i</code>&nbsp;的数的平方和：</p>

<ul>
	<li><code>1 &lt;= i &lt;= n</code></li>
	<li><code>i * i</code> 的十进制表示的字符串可以分割成若干连续子字符串，且这些子字符串对应的整数值之和等于 <code>i</code> 。</li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>n = 10
<b>输出：</b>182
<b>解释：</b>总共有 3 个整数 i 满足要求：
- 1 ，因为 1 * 1 = 1
- 9 ，因为 9 * 9 = 81 ，且 81 可以分割成 8 + 1 。
- 10 ，因为 10 * 10 = 100 ，且 100 可以分割成 10 + 0 。
因此，10 的惩罚数为 1 + 81 + 100 = 182
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>n = 37
<b>输出：</b>1478
<b>解释：</b>总共有 4 个整数 i 满足要求：
- 1 ，因为 1 * 1 = 1
- 9 ，因为 9 * 9 = 81 ，且 81 可以分割成 8 + 1 。
- 10 ，因为 10 * 10 = 100 ，且 100 可以分割成 10 + 0 。
- 36 ，因为 36 * 36 = 1296 ，且 1296 可以分割成 1 + 29 + 6 。
因此，37 的惩罚数为 1 + 81 + 100 + 1296 = 1478
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 1000</code></li>
</ul>

## 解法

### 方法一：枚举 + DFS

我们枚举 $i$，其中 $1 \leq i \leq n$，对于每个 $i$，我们将 $x = i^2$ 的十进制表示的字符串进行分割，然后判断是否满足题目要求。如果满足，我们就将 $x$ 累加到答案中。

枚举结束，返回答案即可。

时间复杂度 $O(n^{1 + 2 \log_{10}^2})$，空间复杂度 $O(\log n)$。其中 $n$ 为给定的正整数。

<!-- tabs:start -->

```python
class Solution:
    def punishmentNumber(self, n: int) -> int:
        def check(s: str, i: int, x: int) -> bool:
            m = len(s)
            if i >= m:
                return x == 0
            y = 0
            for j in range(i, m):
                y = y * 10 + int(s[j])
                if y > x:
                    break
                if check(s, j + 1, x - y):
                    return True
            return False

        ans = 0
        for i in range(1, n + 1):
            x = i * i
            if check(str(x), 0, i):
                ans += x
        return ans
```

```java
class Solution {
    public int punishmentNumber(int n) {
        int ans = 0;
        for (int i = 1; i <= n; ++i) {
            int x = i * i;
            if (check(x + "", 0, i)) {
                ans += x;
            }
        }
        return ans;
    }

    private boolean check(String s, int i, int x) {
        int m = s.length();
        if (i >= m) {
            return x == 0;
        }
        int y = 0;
        for (int j = i; j < m; ++j) {
            y = y * 10 + (s.charAt(j) - '0');
            if (y > x) {
                break;
            }
            if (check(s, j + 1, x - y)) {
                return true;
            }
        }
        return false;
    }
}
```

```cpp
class Solution {
public:
    int punishmentNumber(int n) {
        int ans = 0;
        for (int i = 1; i <= n; ++i) {
            int x = i * i;
            string s = to_string(x);
            if (check(s, 0, i)) {
                ans += x;
            }
        }
        return ans;
    }

    bool check(const string& s, int i, int x) {
        int m = s.size();
        if (i >= m) {
            return x == 0;
        }
        int y = 0;
        for (int j = i; j < m; ++j) {
            y = y * 10 + s[j] - '0';
            if (y > x) {
                break;
            }
            if (check(s, j + 1, x - y)) {
                return true;
            }
        }
        return false;
    }
};
```

```go
func punishmentNumber(n int) (ans int) {
	var check func(string, int, int) bool
	check = func(s string, i, x int) bool {
		m := len(s)
		if i >= m {
			return x == 0
		}
		y := 0
		for j := i; j < m; j++ {
			y = y*10 + int(s[j]-'0')
			if y > x {
				break
			}
			if check(s, j+1, x-y) {
				return true
			}
		}
		return false
	}
	for i := 1; i <= n; i++ {
		x := i * i
		s := strconv.Itoa(x)
		if check(s, 0, i) {
			ans += x
		}
	}
	return
}
```

```ts
function punishmentNumber(n: number): number {
    const check = (s: string, i: number, x: number): boolean => {
        const m = s.length;
        if (i >= m) {
            return x === 0;
        }
        let y = 0;
        for (let j = i; j < m; ++j) {
            y = y * 10 + Number(s[j]);
            if (y > x) {
                break;
            }
            if (check(s, j + 1, x - y)) {
                return true;
            }
        }
        return false;
    };
    let ans = 0;
    for (let i = 1; i <= n; ++i) {
        const x = i * i;
        const s = x.toString();
        if (check(s, 0, i)) {
            ans += x;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
