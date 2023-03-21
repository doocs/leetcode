# [面试题 05.02. 二进制数转字符串](https://leetcode.cn/problems/bianry-number-to-string-lcci)

[English Version](/lcci/05.02.Bianry%20Number%20to%20String/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
<p>二进制数转字符串。给定一个介于0和1之间的实数（如0.72），类型为double，打印它的二进制表达式。如果该数字不在0和1之间，<strong>或者</strong>无法精确地用32位以内的二进制表示，则打印&ldquo;ERROR&rdquo;。</p>
<p><strong>示例1:</strong></p>
<pre><strong> 输入</strong>：0.625
<strong> 输出</strong>：&quot;0.101&quot;
</pre>
<p><strong>示例2:</strong></p>
<pre><strong> 输入</strong>：0.1
<strong> 输出</strong>：&quot;ERROR&quot;
<strong> 提示</strong>：0.1无法被二进制准确表示
</pre>
<p><strong>提示：</strong></p>
<ol>
	<li>32位包括输出中的&quot;0.&quot;这两位。</li>
</ol>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：十进制小数转二进制小数**

十进制小数转二进制小数的方法是：小数部分乘以 $2$，取整数部分作为二进制小数的下一位，小数部分作为下一次乘法的被乘数，直到小数部分为 $0$ 或者二进制小数的长度超过 $32$ 位。

我们不妨举个例子，比如说我们要将 $0.8125$ 转换为二进制小数，过程如下：

$$
\begin{aligned}
0.8125 \times 2 &= 1.625 \quad \text{取整数部分} \quad 1 \\
0.625 \times 2 &= 1.25 \quad \text{取整数部分} \quad 1 \\
0.25 \times 2 &= 0.5 \quad \text{取整数部分} \quad 0 \\
0.5 \times 2 &= 1 \quad \text{取整数部分} \quad 1 \\
\end{aligned}
$$

所以十进制小数 $0.8125$ 的二进制小数表示为 $0.1101_{(2)}$。

对于本题，由于实数介于 $0$ 和 $1$ 之间，所以其整数部分一定是 $0$，我们只需要将小数部分，按照上述方法转换为二进制小数即可。当小数部分为 $0$ 或者二进制小数的长度不小于 $32$ 位时，停止转换。

最后，如果小数部分不为 $0$，说明该实数无法用 $32$ 位以内的二进制表示，返回字符串 `"ERROR"`，否则返回转换后的二进制小数。

时间复杂度 $O(C)$，空间复杂度 $O(C)$。其中 $C$ 为二进制小数的长度，最大为 $32$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def printBin(self, num: float) -> str:
        ans = '0.'
        while len(ans) < 32 and num:
            num *= 2
            x = int(num)
            ans += str(x)
            num -= x
        return 'ERROR' if num else ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String printBin(double num) {
        StringBuilder ans = new StringBuilder("0.");
        while (ans.length() < 32 && num != 0) {
            num *= 2;
            int x = (int) num;
            ans.append(x);
            num -= x;
        }
        return num != 0 ? "ERROR" : ans.toString();
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string printBin(double num) {
        string ans = "0.";
        while (ans.size() < 32 && num != 0) {
            num *= 2;
            int x = (int) num;
            ans.push_back('0' + x);
            num -= x;
        }
        return num != 0 ? "ERROR" : ans;
    }
};
```

### **Go**

```go
func printBin(num float64) string {
	ans := &strings.Builder{}
	ans.WriteString("0.")
	for ans.Len() < 32 && num != 0 {
		num *= 2
		x := byte(num)
		ans.WriteByte('0' + x)
		num -= float64(x)
	}
	if num != 0 {
		return "ERROR"
	}
	return ans.String()
}
```

### **...**

```

```

<!-- tabs:end -->
