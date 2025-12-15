---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1000-1099/1017.Convert%20to%20Base%20-2/README.md
rating: 1697
source: 第 130 场周赛 Q2
tags:
    - 数学
---

<!-- problem:start -->

# [1017. 负二进制转换](https://leetcode.cn/problems/convert-to-base-2)

[English Version](/solution/1000-1099/1017.Convert%20to%20Base%20-2/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数 <code>n</code> ，以二进制字符串的形式返回该整数的 <strong>负二进制（<code>base -2</code>）</strong>表示。</p>

<p><strong>注意，</strong>除非字符串就是&nbsp;<code>"0"</code>，否则返回的字符串中不能含有前导零。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 2
<strong>输出：</strong>"110"
<strong>解释：</strong>(-2)<sup>2</sup> + (-2)<sup>1</sup> = 2
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 3
<strong>输出：</strong>"111"
<strong>解释：</strong>(-2)<sup>2</sup> + (-2)<sup>1</sup> + (-2)<sup>0</sup> = 3
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>n = 4
<strong>输出：</strong>"100"
<strong>解释：</strong>(-2)<sup>2</sup> = 4
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= n &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

我们可以判断 $n$ 从低位到高位的每一位，如果该位为 $1$，那么答案的该位为 $1$，否则为 $0$。如果该位为 $1$，那么我们需要将 $n$ 减去 $k$。接下来我们更新 $n = \lfloor n / 2 \rfloor$, $k = -k$。继续判断下一位。

最后，我们将答案反转后返回即可。

时间复杂度 $O(\log n)$，其中 $n$ 为给定的整数。忽略答案的空间消耗，空间复杂度 $O(1)$。

相似题目：

- [1073. 负二进制数相加](https://github.com/doocs/leetcode/blob/main/solution/1000-1099/1073.Adding%20Two%20Negabinary%20Numbers/README.md)

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def baseNeg2(self, n: int) -> str:
        k = 1
        ans = []
        while n:
            if n % 2:
                ans.append('1')
                n -= k
            else:
                ans.append('0')
            n //= 2
            k *= -1
        return ''.join(ans[::-1]) or '0'
```

#### Java

```java
class Solution {
    public String baseNeg2(int n) {
        if (n == 0) {
            return "0";
        }
        int k = 1;
        StringBuilder ans = new StringBuilder();
        while (n != 0) {
            if (n % 2 != 0) {
                ans.append(1);
                n -= k;
            } else {
                ans.append(0);
            }
            k *= -1;
            n /= 2;
        }
        return ans.reverse().toString();
    }
}
```

#### C++

```cpp
class Solution {
public:
    string baseNeg2(int n) {
        if (n == 0) {
            return "0";
        }
        int k = 1;
        string ans;
        while (n) {
            if (n % 2) {
                ans.push_back('1');
                n -= k;
            } else {
                ans.push_back('0');
            }
            k *= -1;
            n /= 2;
        }
        reverse(ans.begin(), ans.end());
        return ans;
    }
};
```

#### Go

```go
func baseNeg2(n int) string {
	if n == 0 {
		return "0"
	}
	ans := []byte{}
	k := 1
	for n != 0 {
		if n%2 != 0 {
			ans = append(ans, '1')
			n -= k
		} else {
			ans = append(ans, '0')
		}
		k *= -1
		n /= 2
	}
	for i, j := 0, len(ans)-1; i < j; i, j = i+1, j-1 {
		ans[i], ans[j] = ans[j], ans[i]
	}
	return string(ans)
}
```

#### TypeScript

```ts
function baseNeg2(n: number): string {
    if (n === 0) {
        return '0';
    }
    let k = 1;
    const ans: string[] = [];
    while (n) {
        if (n % 2) {
            ans.push('1');
            n -= k;
        } else {
            ans.push('0');
        }
        k *= -1;
        n /= 2;
    }
    return ans.reverse().join('');
}
```

#### Rust

```rust
impl Solution {
    pub fn base_neg2(n: i32) -> String {
        if n == 0 {
            return "0".to_string();
        }
        let mut k = 1;
        let mut ans = String::new();
        let mut num = n;
        while num != 0 {
            if num % 2 != 0 {
                ans.push('1');
                num -= k;
            } else {
                ans.push('0');
            }
            k *= -1;
            num /= 2;
        }
        ans.chars().rev().collect::<String>()
    }
}
```

#### C#

```cs
public class Solution {
    public string BaseNeg2(int n) {
        if (n == 0) {
            return "0";
        }
        int k = 1;
        StringBuilder ans = new StringBuilder();
        int num = n;
        while (num != 0) {
            if (num % 2 != 0) {
                ans.Append('1');
                num -= k;
            } else {
                ans.Append('0');
            }
            k *= -1;
            num /= 2;
        }
        char[] cs = ans.ToString().ToCharArray();
        Array.Reverse(cs);
        return new string(cs);
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
