# [2802. 找出第 K 个幸运数字](https://leetcode.cn/problems/find-the-k-th-lucky-number)

[English Version](/solution/2800-2899/2802.Find%20The%20K-th%20Lucky%20Number/README_EN.md)

<!-- tags:位运算,数学,字符串 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>我们知道 <code>4</code> 和 <code>7</code> 是 <strong>幸运</strong> 数字。同时，如果一个数字只包含幸运数字，那么它被称为幸运数字。</p>

<p>给定一个整数 <code>k</code>，返回第 <code>k</code> 个幸运数字，并将其表示为一个 <strong>字符串</strong> 。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>k = 4
<b>输出：</b>"47"
<b>解释：</b>第一个幸运数字是 4，第二个是 7，第三个是 44，第四个是 47。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>k = 10
<b>输出：</b>"477"
<b>解释：</b>按递增顺序列出的幸运数字为：
4, 7, 44, 47, 74, 77, 444, 447, 474, 477。 因此第10个幸运数字是477。</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<b>输入：</b>k = 1000
<b>输出：</b>"777747447"
<b>解释：</b>第 1000 个幸运数字是 777747447。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

### 方法一：数学

根据题目描述，一个幸运数只包含数字 $4$ 和 $7$，因此 $n$ 位幸运数的个数为 $2^n$。

我们初始化 $n=1$，接下来循环判断 $k$ 是否大于 $2^n$，如果大于则将 $k$ 减去 $2^n$，同时 $n$ 加一，直到 $k$ 小于等于 $2^n$。此时，我们只需要找出 $n$ 位幸运数中的第 $k$ 个即可。

如果 $k$ 小于等于 $2^{n-1}$，则第 $k$ 个幸运数的第一位为 $4$，否则第一位为 $7$，然后我们将 $k$ 减去 $2^{n-1}$，继续判断第二位，直到 $n$ 位幸运数的所有位都判断完毕。

时间复杂度 $O(\log k)$，空间复杂度 $O(\log k)$。

<!-- tabs:start -->

```python
class Solution:
    def kthLuckyNumber(self, k: int) -> str:
        n = 1
        while k > 1 << n:
            k -= 1 << n
            n += 1
        ans = []
        while n:
            n -= 1
            if k <= 1 << n:
                ans.append("4")
            else:
                ans.append("7")
                k -= 1 << n
        return "".join(ans)
```

```java
class Solution {
    public String kthLuckyNumber(int k) {
        int n = 1;
        while (k > 1 << n) {
            k -= 1 << n;
            ++n;
        }
        StringBuilder ans = new StringBuilder();
        while (n-- > 0) {
            if (k <= 1 << n) {
                ans.append('4');
            } else {
                ans.append('7');
                k -= 1 << n;
            }
        }
        return ans.toString();
    }
}
```

```cpp
class Solution {
public:
    string kthLuckyNumber(int k) {
        int n = 1;
        while (k > 1 << n) {
            k -= 1 << n;
            ++n;
        }
        string ans;
        while (n--) {
            if (k <= 1 << n) {
                ans.push_back('4');
            } else {
                ans.push_back('7');
                k -= 1 << n;
            }
        }
        return ans;
    }
};
```

```go
func kthLuckyNumber(k int) string {
	n := 1
	for k > 1<<n {
		k -= 1 << n
		n++
	}
	ans := []byte{}
	for n > 0 {
		n--
		if k <= 1<<n {
			ans = append(ans, '4')
		} else {
			ans = append(ans, '7')
			k -= 1 << n
		}
	}
	return string(ans)
}
```

```ts
function kthLuckyNumber(k: number): string {
    let n = 1;
    while (k > 1 << n) {
        k -= 1 << n;
        ++n;
    }
    const ans: string[] = [];
    while (n-- > 0) {
        if (k <= 1 << n) {
            ans.push('4');
        } else {
            ans.push('7');
            k -= 1 << n;
        }
    }
    return ans.join('');
}
```

<!-- tabs:end -->

<!-- end -->
