# [2802. Find The K-th Lucky Number](https://leetcode.cn/problems/find-the-k-th-lucky-number)

[English Version](/solution/2800-2899/2802.Find%20The%20K-th%20Lucky%20Number/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>We know that <code>4</code> and <code>7</code> are <strong>lucky</strong> digits. Also, a number is called <strong>lucky</strong>&nbsp;if it contains <strong>only</strong> lucky digits.</p>

<p>You are given an integer <code>k</code>, return<em> the </em><code>k<sup>th</sup></code><em>&nbsp;lucky number represented as a <strong>string</strong>.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> k = 4
<strong>Output:</strong> &quot;47&quot;
<strong>Explanation:</strong> The first lucky number is 4, the second one is 7, the third one is 44 and the fourth one is 47.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> k = 10
<strong>Output:</strong> &quot;477&quot;
<strong>Explanation:</strong> Here are lucky numbers sorted in increasing order:
4, 7, 44, 47, 74, 77, 444, 447, 474, 477. So the 10<sup>th</sup> lucky number is 477.</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> k = 1000
<strong>Output:</strong> &quot;777747447&quot;
<strong>Explanation:</strong> It can be shown that the 1000<sup>th</sup> lucky number is 777747447.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：数学**

根据题目描述，一个幸运数只包含数字 $4$ 和 $7$，因此 $n$ 位幸运数的个数为 $2^n$。

我们初始化 $n=1$，接下来循环判断 $k$ 是否大于 $2^n$，如果大于则将 $k$ 减去 $2^n$，同时 $n$ 加一，直到 $k$ 小于等于 $2^n$。此时，我们只需要找出 $n$ 位幸运数中的第 $k$ 个即可。

如果 $k$ 小于等于 $2^{n-1}$，则第 $k$ 个幸运数的第一位为 $4$，否则第一位为 $7$，然后我们将 $k$ 减去 $2^{n-1}$，继续判断第二位，直到 $n$ 位幸运数的所有位都判断完毕。

时间复杂度 $O(\log k)$，空间复杂度 $O(\log k)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

### **C++**

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

### **Go**

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

### **TypeScript**

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

### **...**

```

```

<!-- tabs:end -->
