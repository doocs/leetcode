---
comments: true
difficulty: 中等
rating: 1561
source: 第 13 场双周赛 Q1
tags:
    - 位运算
    - 数学
    - 字符串
---

<!-- problem:start -->

# [1256. 加密数字 🔒](https://leetcode.cn/problems/encode-number)

[English Version](/solution/1200-1299/1256.Encode%20Number/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个非负整数 <code>num</code> ，返回它的「加密字符串」。</p>

<p>加密的过程是把一个整数用某个未知函数进行转化，你需要从下表推测出该转化函数：</p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1200-1299/1256.Encode%20Number/images/encode_number.png" style="height: 360px; width: 164px;"></p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>num = 23
<strong>输出：</strong>&quot;1000&quot;
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>num = 107
<strong>输出：</strong>&quot;101100&quot;
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= num &lt;= 10^9</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：位运算

<!-- thinking:start -->

> **思考**
>
> 编码表按 $0,1,00,01,\ldots$ 排列，即所有二进制串的字典序。$num+1$ 的二进制去掉最高位的 $1$，剩下的恰是第 $num$ 个这样的串。$num$ 达 $10^9$，不能列表，位运算一次完成。

<!-- thinking:end -->

我们将 $num$ 加一，然后将其转换为二进制字符串，去掉最高位的 $1$ 即可。

时间复杂度 $O(\log n)$，空间复杂度 $O(\log n)$。其中 $n$ 为 $num$ 的大小。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def encode(self, num: int) -> str:
        return bin(num + 1)[3:]
```

#### Java

```java
class Solution {
    public String encode(int num) {
        return Integer.toBinaryString(num + 1).substring(1);
    }
}
```

#### C++

```cpp
class Solution {
public:
    string encode(int num) {
        bitset<32> bs(++num);
        string ans = bs.to_string();
        int i = 0;
        while (ans[i] == '0') {
            ++i;
        }
        return ans.substr(i + 1);
    }
};
```

#### Go

```go
func encode(num int) string {
	num++
	s := strconv.FormatInt(int64(num), 2)
	return s[1:]
}
```

#### TypeScript

```ts
function encode(num: number): string {
    ++num;
    let s = num.toString(2);
    return s.slice(1);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
