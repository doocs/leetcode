# [504. 七进制数](https://leetcode.cn/problems/base-7)

[English Version](/solution/0500-0599/0504.Base%207/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个整数 <code>num</code>，将其转化为 <strong>7 进制</strong>，并以字符串形式输出。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> num = 100
<strong>输出:</strong> "202"
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> num = -7
<strong>输出:</strong> "-10"
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>-10<sup>7</sup>&nbsp;&lt;= num &lt;= 10<sup>7</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：模拟**

我们不妨假设 `num` 大于等于 $0$，那么，如果 `num` 等于 $0$，只需要返回 $0$ 即可。否则，我们将 $num$ 模 $7$ 的结果保存起来，最后逆序拼接成字符串即可。

时间复杂度 $O(\log n)$，忽略答案的空间消耗，空间复杂度 $O(1)$。其中 $n$ 是 `num` 的绝对值大小。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def convertToBase7(self, num: int) -> str:
        if num == 0:
            return '0'
        if num < 0:
            return '-' + self.convertToBase7(-num)
        ans = []
        while num:
            ans.append(str(num % 7))
            num //= 7
        return ''.join(ans[::-1])
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String convertToBase7(int num) {
        if (num == 0) {
            return "0";
        }
        if (num < 0) {
            return "-" + convertToBase7(-num);
        }
        StringBuilder sb = new StringBuilder();
        while (num != 0) {
            sb.append(num % 7);
            num /= 7;
        }
        return sb.reverse().toString();
    }
}
```

### **TypeScript**

```ts
function convertToBase7(num: number): string {
    if (num == 0) {
        return '0';
    }
    let res = '';
    const isMinus = num < 0;
    if (isMinus) {
        num = -num;
    }
    while (num != 0) {
        const r = num % 7;
        res = r + res;
        num = (num - r) / 7;
    }
    return isMinus ? '-' + res : res;
}
```

### **Rust**

```rust
impl Solution {
    pub fn convert_to_base7(mut num: i32) -> String {
        if num == 0 {
            return String::from("0");
        }
        let mut res = String::new();
        let is_minus = num < 0;
        if is_minus {
            num = -num;
        }
        while num != 0 {
            res.push_str((num % 7).to_string().as_str());
            num /= 7;
        }
        if is_minus {
            res.push('-');
        }
        res.chars().rev().collect()
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string convertToBase7(int num) {
        if (num == 0) return "0";
        if (num < 0) return "-" + convertToBase7(-num);
        string ans = "";
        while (num) {
            ans = to_string(num % 7) + ans;
            num /= 7;
        }
        return ans;
    }
};
```

### **Go**

```go
func convertToBase7(num int) string {
	if num == 0 {
		return "0"
	}
	if num < 0 {
		return "-" + convertToBase7(-num)
	}
	ans := []byte{}
	for num != 0 {
		ans = append([]byte{'0' + byte(num%7)}, ans...)
		num /= 7
	}
	return string(ans)
}
```

### **...**

```

```

<!-- tabs:end -->
