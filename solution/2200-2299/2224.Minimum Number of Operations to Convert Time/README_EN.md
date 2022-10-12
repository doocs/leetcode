# [2224. Minimum Number of Operations to Convert Time](https://leetcode.com/problems/minimum-number-of-operations-to-convert-time)

[中文文档](/solution/2200-2299/2224.Minimum%20Number%20of%20Operations%20to%20Convert%20Time/README.md)

## Description

<p>You are given two strings <code>current</code> and <code>correct</code> representing two <strong>24-hour times</strong>.</p>

<p>24-hour times are formatted as <code>&quot;HH:MM&quot;</code>, where <code>HH</code> is between <code>00</code> and <code>23</code>, and <code>MM</code> is between <code>00</code> and <code>59</code>. The earliest 24-hour time is <code>00:00</code>, and the latest is <code>23:59</code>.</p>

<p>In one operation you can increase the time <code>current</code> by <code>1</code>, <code>5</code>, <code>15</code>, or <code>60</code> minutes. You can perform this operation <strong>any</strong> number of times.</p>

<p>Return <em>the <strong>minimum number of operations</strong> needed to convert </em><code>current</code><em> to </em><code>correct</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> current = &quot;02:30&quot;, correct = &quot;04:35&quot;
<strong>Output:</strong> 3
<strong>Explanation:
</strong>We can convert current to correct in 3 operations as follows:
- Add 60 minutes to current. current becomes &quot;03:30&quot;.
- Add 60 minutes to current. current becomes &quot;04:30&quot;.
- Add 5 minutes to current. current becomes &quot;04:35&quot;.
It can be proven that it is not possible to convert current to correct in fewer than 3 operations.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> current = &quot;11:00&quot;, correct = &quot;11:01&quot;
<strong>Output:</strong> 1
<strong>Explanation:</strong> We only have to add one minute to current, so the minimum number of operations needed is 1.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>current</code> and <code>correct</code> are in the format <code>&quot;HH:MM&quot;</code></li>
	<li><code>current &lt;= correct</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def convertTime(self, current: str, correct: str) -> int:
        a = int(current[:2]) * 60 + int(current[3:])
        b = int(correct[:2]) * 60 + int(correct[3:])
        ans, d = 0, b - a
        for i in [60, 15, 5, 1]:
            ans += d // i
            d %= i
        return ans
```

### **Java**

```java
class Solution {
    public int convertTime(String current, String correct) {
        int a = Integer.parseInt(current.substring(0, 2)) * 60
            + Integer.parseInt(current.substring(3));
        int b = Integer.parseInt(correct.substring(0, 2)) * 60
            + Integer.parseInt(correct.substring(3));
        int ans = 0, d = b - a;
        for (int i : Arrays.asList(60, 15, 5, 1)) {
            ans += d / i;
            d %= i;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int convertTime(string current, string correct) {
        int a = stoi(current.substr(0, 2)) * 60 + stoi(current.substr(3, 2));
        int b = stoi(correct.substr(0, 2)) * 60 + stoi(correct.substr(3, 2));
        int ans = 0, d = b - a;
        vector<int> inc = {60, 15, 5, 1};
        for (int i : inc) {
            ans += d / i;
            d %= i;
        }
        return ans;
    }
};
```

### **Go**

```go
func convertTime(current string, correct string) int {
    parse := func(s string) int {
        h := int(s[0] - '0') * 10 + int(s[1] - '0')
        m := int(s[3] - '0') * 10 + int(s[4] - '0')
        return h * 60 + m
    }
    a, b := parse(current), parse(correct)
    ans, d := 0, b - a
    for _, i := range []int{60, 15, 5, 1} {
        ans += d / i
        d %= i
    }
    return ans
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
