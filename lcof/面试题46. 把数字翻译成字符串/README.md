---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/lcof/%E9%9D%A2%E8%AF%95%E9%A2%9846.%20%E6%8A%8A%E6%95%B0%E5%AD%97%E7%BF%BB%E8%AF%91%E6%88%90%E5%AD%97%E7%AC%A6%E4%B8%B2/README.md
---

<!-- problem:start -->

# [面试题 46. 把数字翻译成字符串](https://leetcode.cn/problems/ba-shu-zi-fan-yi-cheng-zi-fu-chuan-lcof/)

## 题目描述

<!-- description:start -->

<p>给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 &ldquo;a&rdquo; ，1 翻译成 &ldquo;b&rdquo;，&hellip;&hellip;，11 翻译成 &ldquo;l&rdquo;，&hellip;&hellip;，25 翻译成 &ldquo;z&rdquo;。一个数字可能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre><strong>输入:</strong> 12258
<strong>输出:</strong> <code>5
</code><strong>解释:</strong> 12258有5种不同的翻译，分别是&quot;bccfi&quot;, &quot;bwfi&quot;, &quot;bczi&quot;, &quot;mcfi&quot;和&quot;mzi&quot;</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= num &lt; 2<sup>31</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：记忆化搜索

我们先将数字 `num` 转为字符串 $s$，字符串 $s$ 的长度记为 $n$。

然后我们设计一个函数 $dfs(i)$，表示从索引为 $i$ 的数字开始的不同翻译的数目。那么答案就是 $dfs(0)$。

函数 $dfs(i)$ 的计算如下：

-   如果 $i \ge n - 1$，说明已经翻译到最后一个数字或者越过最后一个字符，均只有一种翻译方法，返回 $1$；
-   否则，我们可以选择翻译索引为 $i$ 的数字，此时翻译方法数目为 $dfs(i + 1)$；如果索引为 $i$ 的数字和索引为 $i + 1$ 的数字可以组成一个有效的字符（即 $s[i] == 1$ 或者 $s[i] == 2$ 且 $s[i + 1] \lt 6$），那么我们还可以选择翻译索引为 $i$ 和索引为 $i + 1$ 的数字，此时翻译方法数目为 $dfs(i + 2)$。因此 $dfs(i) = dfs(i+1) + dfs(i+2)$。

过程中我们可以使用记忆化搜索，将已经计算过的 $dfs(i)$ 的值存储起来，避免重复计算。

时间复杂度 $O(\log num)$，空间复杂度 $O(\log num)$。其中 $num$ 为给定的数字。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def translateNum(self, num: int) -> int:
        @cache
        def dfs(i):
            if i >= n - 1:
                return 1
            ans = dfs(i + 1)
            if s[i] == "1" or (s[i] == "2" and s[i + 1] < "6"):
                ans += dfs(i + 2)
            return ans

        s = str(num)
        n = len(s)
        return dfs(0)
```

#### Java

```java
class Solution {
    private int n;
    private char[] s;
    private Integer[] f;

    public int translateNum(int num) {
        s = String.valueOf(num).toCharArray();
        n = s.length;
        f = new Integer[n];
        return dfs(0);
    }

    private int dfs(int i) {
        if (i >= n - 1) {
            return 1;
        }
        if (f[i] != null) {
            return f[i];
        }
        int ans = dfs(i + 1);
        if (s[i] == '1' || (s[i] == '2' && s[i + 1] < '6')) {
            ans += dfs(i + 2);
        }
        return f[i] = ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int translateNum(int num) {
        string s = to_string(num);
        int n = s.size();
        int f[12]{};
        function<int(int)> dfs = [&](int i) -> int {
            if (i >= n - 1) {
                return 1;
            }
            if (f[i]) {
                return f[i];
            }
            int ans = dfs(i + 1);
            if (s[i] == '1' || (s[i] == '2' && s[i + 1] < '6')) {
                ans += dfs(i + 2);
            }
            return f[i] = ans;
        };
        return dfs(0);
    }
};
```

#### Go

```go
func translateNum(num int) int {
	s := strconv.Itoa(num)
	n := len(s)
	f := [12]int{}
	var dfs func(int) int
	dfs = func(i int) int {
		if i >= n-1 {
			return 1
		}
		if f[i] != 0 {
			return f[i]
		}
		ans := dfs(i + 1)
		if s[i] == '1' || (s[i] == '2' && s[i+1] < '6') {
			ans += dfs(i + 2)
		}
		f[i] = ans
		return ans
	}
	return dfs(0)
}
```

#### TypeScript

```ts
function translateNum(num: number): number {
    const s = num.toString();
    const n = s.length;
    const f = new Array(n).fill(0);
    const dfs = (i: number): number => {
        if (i >= n - 1) {
            return 1;
        }
        if (f[i]) {
            return f[i];
        }
        let ans = dfs(i + 1);
        if (s[i] === '1' || (s[i] === '2' && s[i + 1] < '6')) {
            ans += dfs(i + 2);
        }
        f[i] = ans;
        return ans;
    };
    return dfs(0);
}
```

#### Rust

```rust
impl Solution {
    pub fn translate_num(num: i32) -> i32 {
        let mut a = 1;
        let mut b = 1;
        let str = num.to_string();
        for i in 0..str.len() - 1 {
            let c = a + b;
            a = b;
            let num = str[i..i + 2].parse::<i32>().unwrap();
            if num >= 10 && num < 26 {
                b = c;
            }
        }
        b
    }
}
```

#### JavaScript

```js
/**
 * @param {number} num
 * @return {number}
 */
var translateNum = function (num) {
    const s = num.toString();
    const n = s.length;
    const f = new Array(n).fill(0);
    const dfs = i => {
        if (i >= n - 1) {
            return 1;
        }
        if (f[i]) {
            return f[i];
        }
        let ans = dfs(i + 1);
        if (s[i] === '1' || (s[i] === '2' && s[i + 1] < '6')) {
            ans += dfs(i + 2);
        }
        f[i] = ans;
        return ans;
    };
    return dfs(0);
};
```

#### C#

```cs
public class Solution {
    public int TranslateNum(int num) {
        var s = num.ToString();
        int n = s.Length;
        int a = 1, b = 1;
        for (int i = 1; i < n; ++i) {
            int c = b;
            if (s[i - 1] == '1' || (s[i - 1] == '2' && s[i] < '6')) {
                c += a;
            }
            a = b;
            b = c;
        }
        return b;
    }
}
```

#### Swift

```swift
class Solution {
    private var n: Int = 0
    private var s: [Character] = []
    private var memo: [Int?] = []

    func translateNum(_ num: Int) -> Int {
        s = Array(String(num))
        n = s.count
        memo = [Int?](repeating: nil, count: n)
        return dfs(0)
    }

    private func dfs(_ i: Int) -> Int {
        if i >= n - 1 {
            return 1
        }
        if let cachedResult = memo[i] {
            return cachedResult
        }
        var ans = dfs(i + 1)
        if s[i] == "1" || (s[i] == "2" && s[i + 1] < "6") {
            ans += dfs(i + 2)
        }
        memo[i] = ans
        return ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start-->

### 方法二：动态规划

我们可以将方法一中的记忆化搜索改为动态规划。

定义 $f[i]$ 表示前 $i$ 个数字的不同翻译的数目，那么答案就是 $f[n]$。初始化 $f[0] = 1$, $f[1] = 1$。

我们可以从前往后计算 $f[i]$ 的值，对于每个 $i$，我们可以选择翻译第 $i$ 个数字，此时翻译方法数目为 $f[i - 1]$；如果第 $i-1$ 个数字和第 $i$ 个数字可以组成一个有效的字符（即 $s[i - 1] == 1$ 或者 $s[i - 1] == 2$ 且 $s[i] \lt 6$），那么我们还可以选择翻译第 $i - 1$ 和第 $i$ 个数字，此时翻译方法数目为 $f[i - 2]$。因此 $f[i] = f[i-1] + f[i-2]$。

由于 $f[i]$ 只与 $f[i - 1]$ 和 $f[i - 2]$ 有关，因此我们可以只用两个变量来存储 $f[i - 1]$ 和 $f[i - 2]$ 的值，从而省去数组 $f$ 的空间。

时间复杂度 $O(\log num)$，空间复杂度 $O(\log num)$。其中 $num$ 为给定的数字。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def translateNum(self, num: int) -> int:
        s = str(num)
        n = len(s)
        a = b = 1
        for i in range(1, n):
            c = b
            if s[i - 1] == '1' or (s[i - 1] == '2' and s[i] < '6'):
                c += a
            a, b = b, c
        return b
```

#### Java

```java
class Solution {
    public int translateNum(int num) {
        char[] s = String.valueOf(num).toCharArray();
        int n = s.length;
        int a = 1, b = 1;
        for (int i = 1; i < n; ++i) {
            int c = b;
            if (s[i - 1] == '1' || (s[i - 1] == '2' && s[i] < '6')) {
                c += a;
            }
            a = b;
            b = c;
        }
        return b;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int translateNum(int num) {
        string s = to_string(num);
        int n = s.size();
        int a = 1, b = 1;
        for (int i = 1; i < n; ++i) {
            int c = b;
            if (s[i - 1] == '1' || (s[i - 1] == '2' && s[i] < '6')) {
                c += a;
            }
            a = b;
            b = c;
        }
        return b;
    }
};
```

#### Go

```go
func translateNum(num int) int {
	s := strconv.Itoa(num)
	n := len(s)
	a, b := 1, 1
	for i := 1; i < n; i++ {
		c := b
		if s[i-1] == '1' || (s[i-1] == '2' && s[i] < '6') {
			c += a
		}
		a, b = b, c
	}
	return b
}
```

#### TypeScript

```ts
function translateNum(num: number): number {
    const s = num.toString();
    const n = s.length;
    let a = 1;
    let b = 1;
    for (let i = 1; i < n; ++i) {
        let c = b;
        if (s[i - 1] === '1' || (s[i - 1] === '2' && s[i] < '6')) {
            c += a;
        }
        a = b;
        b = c;
    }
    return b;
}
```

#### Rust

```rust
impl Solution {
    fn dfs(s: &String, i: usize, res: &mut i32) {
        if i >= s.len() {
            return;
        }
        let val = s[i - 1..=i].parse::<i32>().unwrap();
        if val >= 10 && val <= 25 {
            *res += 1;
            Self::dfs(s, i + 2, res);
        }
        Self::dfs(s, i + 1, res);
    }

    pub fn translate_num(num: i32) -> i32 {
        let s = num.to_string();
        let mut res = 1;
        Self::dfs(&s, 1, &mut res);
        res
    }
}
```

#### JavaScript

```js
/**
 * @param {number} num
 * @return {number}
 */
var translateNum = function (num) {
    const s = num.toString();
    const n = s.length;
    let a = 1;
    let b = 1;
    for (let i = 1; i < n; ++i) {
        let c = b;
        if (s[i - 1] === '1' || (s[i - 1] === '2' && s[i] < '6')) {
            c += a;
        }
        a = b;
        b = c;
    }
    return b;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
