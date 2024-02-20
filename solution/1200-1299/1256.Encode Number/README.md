# [1256. 加密数字](https://leetcode.cn/problems/encode-number)

[English Version](/solution/1200-1299/1256.Encode%20Number/README_EN.md)

<!-- tags:位运算,数学,字符串 -->

## 题目描述

<!-- 这里写题目描述 -->

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

## 解法

### 方法一：位运算

我们将 $num$ 加一，然后将其转换为二进制字符串，去掉最高位的 $1$ 即可。

时间复杂度 $O(\log n)$，空间复杂度 $O(\log n)$。其中 $n$ 为 $num$ 的大小。

<!-- tabs:start -->

```python
class Solution:
    def encode(self, num: int) -> str:
        return bin(num + 1)[3:]
```

```java
class Solution {
    public String encode(int num) {
        return Integer.toBinaryString(num + 1).substring(1);
    }
}
```

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

```go
func encode(num int) string {
	num++
	s := strconv.FormatInt(int64(num), 2)
	return s[1:]
}
```

```ts
function encode(num: number): string {
    ++num;
    let s = num.toString(2);
    return s.slice(1);
}
```

<!-- tabs:end -->

<!-- end -->
