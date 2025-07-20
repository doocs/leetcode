---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3600-3699/3602.Hexadecimal%20and%20Hexatrigesimal%20Conversion/README.md
tags:
    - 数学
    - 字符串
---

<!-- problem:start -->

# [3602. 十六进制和三十六进制转化](https://leetcode.cn/problems/hexadecimal-and-hexatrigesimal-conversion)

[English Version](/solution/3600-3699/3602.Hexadecimal%20and%20Hexatrigesimal%20Conversion/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数 <code>n</code>。</p>

<p>返回 <code>n<sup>2</sup></code> 的&nbsp;<strong>十六进制表示</strong> 和 <code>n<sup>3</sup></code> 的&nbsp;<strong>三十六进制表示</strong> 拼接成的字符串。</p>

<p><strong>十六进制&nbsp;</strong>数定义为使用数字 <code>0 – 9</code> 和大写字母 <code>A - F</code> 表示 0 到 15 的值。</p>

<p><strong>三十六进制&nbsp;</strong>数定义为使用数字 <code>0 – 9</code> 和大写字母 <code>A - Z</code> 表示 0 到 35 的值。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">n = 13</span></p>

<p><strong>输出：&nbsp;</strong><span class="example-io">"A91P1"</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li><code>n<sup>2</sup> = 13 * 13 = 169</code>。在十六进制中，它转换为 <code>(10 * 16) + 9 = 169</code>，对应于 <code>"A9"</code>。</li>
	<li><code>n<sup>3</sup> = 13 * 13 * 13 = 2197</code>。在三十六进制中，它转换为 <code>(1 * 36<sup>2</sup>) + (25 * 36) + 1 = 2197</code>，对应于 <code>"1P1"</code>。</li>
	<li>连接两个结果得到 <code>"A9" + "1P1" = "A91P1"</code>。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong><span class="example-io">n = 36</span></p>

<p><strong>输出：</strong><span class="example-io">"5101000"</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li><code>n<sup>2</sup> = 36 * 36 = 1296</code>。在十六进制中，它转换为 <code>(5 * 16<sup>2</sup>) + (1 * 16) + 0 = 1296</code>，对应于 <code>"510"</code>。</li>
	<li><code>n<sup>3</sup> = 36 * 36 * 36 = 46656</code>。在三十六进制中，它转换为 <code>(1 * 36<sup>3</sup>) + (0 * 36<sup>2</sup>) + (0 * 36) + 0 = 46656</code>，对应于 <code>"1000"</code>。</li>
	<li>连接两个结果得到 <code>"510" + "1000" = "5101000"</code>。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

我们定义一个函数 $\textit{f}(x, k)$，它将整数 $x$ 转换为以 $k$ 进制表示的字符串。该函数通过不断取模和整除来构建结果字符串。

对于给定的整数 $n$，我们计算 $n^2$ 和 $n^3$，然后分别将它们转换为十六进制和三十六进制字符串。最后，将这两个字符串连接起来返回。

时间复杂度 $O(\log n)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def concatHex36(self, n: int) -> str:
        def f(x: int, k: int) -> str:
            res = []
            while x:
                v = x % k
                if v <= 9:
                    res.append(str(v))
                else:
                    res.append(chr(ord("A") + v - 10))
                x //= k
            return "".join(res[::-1])

        x, y = n**2, n**3
        return f(x, 16) + f(y, 36)
```

#### Java

```java
class Solution {
    public String concatHex36(int n) {
        int x = n * n;
        int y = n * n * n;
        return f(x, 16) + f(y, 36);
    }

    private String f(int x, int k) {
        StringBuilder res = new StringBuilder();
        while (x > 0) {
            int v = x % k;
            if (v <= 9) {
                res.append((char) ('0' + v));
            } else {
                res.append((char) ('A' + v - 10));
            }
            x /= k;
        }
        return res.reverse().toString();
    }
}
```

#### C++

```cpp
class Solution {
public:
    string concatHex36(int n) {
        int x = n * n;
        int y = n * n * n;
        return f(x, 16) + f(y, 36);
    }

private:
    string f(int x, int k) {
        string res;
        while (x > 0) {
            int v = x % k;
            if (v <= 9) {
                res += char('0' + v);
            } else {
                res += char('A' + v - 10);
            }
            x /= k;
        }
        reverse(res.begin(), res.end());
        return res;
    }
};
```

#### Go

```go
func concatHex36(n int) string {
	x := n * n
	y := n * n * n
	return f(x, 16) + f(y, 36)
}

func f(x, k int) string {
	res := []byte{}
	for x > 0 {
		v := x % k
		if v <= 9 {
			res = append(res, byte('0'+v))
		} else {
			res = append(res, byte('A'+v-10))
		}
		x /= k
	}
	for i, j := 0, len(res)-1; i < j; i, j = i+1, j-1 {
		res[i], res[j] = res[j], res[i]
	}
	return string(res)
}
```

#### TypeScript

```ts
function concatHex36(n: number): string {
    function f(x: number, k: number): string {
        const digits = '0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ';
        let res = '';
        while (x > 0) {
            const v = x % k;
            res = digits[v] + res;
            x = Math.floor(x / k);
        }
        return res;
    }

    const x = n * n;
    const y = n * n * n;
    return f(x, 16) + f(y, 36);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
