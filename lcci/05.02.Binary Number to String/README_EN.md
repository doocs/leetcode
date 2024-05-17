---
comments: true
edit_url: https://github.com/doocs/leetcode/edit/main/lcci/05.02.Binary%20Number%20to%20String/README_EN.md
---

<!-- problem:start -->

# [05.02. Binary Number to String](https://leetcode.cn/problems/binary-number-to-string-lcci)

[中文文档](/lcci/05.02.Binary%20Number%20to%20String/README.md)

## Description

<!-- description:start -->

<p>Given a real number between O and 1 (e.g., 0.72) that is passed in as a double, print the binary representation. If the number cannot be represented accurately in binary with at most 32 characters, print &quot;ERROR&quot;.</p>
<p><strong>Example1:</strong></p>
<pre>

<strong> Input</strong>: 0.625

<strong> Output</strong>: &quot;0.101&quot;

</pre>
<p><strong>Example2:</strong></p>
<pre>

<strong> Input</strong>: 0.1

<strong> Output</strong>: &quot;ERROR&quot;

<strong> Note</strong>: 0.1 cannot be represented accurately in binary.

</pre>
<p><strong>Note: </strong></p>
<ol>
	<li>This two characters &quot;0.&quot; should be counted into 32 characters.</li>
</ol>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Decimal Fraction to Binary Fraction

The method of converting a decimal fraction to a binary fraction is as follows: multiply the decimal part by $2$, take the integer part as the next digit of the binary fraction, and take the decimal part as the multiplicand for the next multiplication, until the decimal part is $0$ or the length of the binary fraction exceeds $32$ bits.

Let's take an example, suppose we want to convert $0.8125$ to a binary fraction, the process is as follows:

$$
\begin{aligned}
0.8125 \times 2 &= 1.625 \quad \text{take the integer part} \quad 1 \\
0.625 \times 2 &= 1.25 \quad \text{take the integer part} \quad 1 \\
0.25 \times 2 &= 0.5 \quad \text{take the integer part} \quad 0 \\
0.5 \times 2 &= 1 \quad \text{take the integer part} \quad 1 \\
\end{aligned}
$$

So the binary fraction representation of the decimal fraction $0.8125$ is $0.1101_{(2)}$.

For this problem, since the real number is between $0$ and $1$, its integer part must be $0$. We only need to convert the decimal part into a binary fraction according to the above method. Stop the conversion when the decimal part is $0$ or the length of the binary fraction is not less than $32$ bits.

Finally, if the decimal part is not $0$, it means that the real number cannot be represented in binary within $32$ bits, return the string `"ERROR"`. Otherwise, return the converted binary fraction.

The time complexity is $O(C)$, and the space complexity is $O(C)$. Here, $C$ is the length of the binary fraction, with a maximum of $32$.

<!-- tabs:start -->

#### Python3

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

#### Java

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

#### C++

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

#### Go

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

#### Swift

```swift
class Solution {
    func printBin(_ num: Double) -> String {
        var num = num
        var ans = "0."

        while ans.count < 32 && num != 0 {
            num *= 2
            let x = Int(num)
            ans.append("\(x)")
            num -= Double(x)
        }

        return num != 0 ? "ERROR" : ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
