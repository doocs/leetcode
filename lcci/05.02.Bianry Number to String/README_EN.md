# [05.02. Bianry Number to String](https://leetcode.cn/problems/bianry-number-to-string-lcci)

[中文文档](/lcci/05.02.Bianry%20Number%20to%20String/README.md)

## Description

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
	<li>This two charaters &quot;0.&quot; should be counted into 32 characters.</li>
</ol>

## Solutions

<!-- tabs:start -->

### **Python3**

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
