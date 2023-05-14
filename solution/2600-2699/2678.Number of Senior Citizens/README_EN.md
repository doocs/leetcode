# [2678. Number of Senior Citizens](https://leetcode.com/problems/number-of-senior-citizens)

[中文文档](/solution/2600-2699/2678.Number%20of%20Senior%20Citizens/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> array of strings <code>details</code>. Each element of <code>details</code> provides information about a given passenger compressed into a string of length <code>15</code>. The system is such that:</p>

<ul>
	<li>The first ten characters consist of the phone number of passengers.</li>
	<li>The next character denotes the gender of the person.</li>
	<li>The following two characters are used to indicate the age of the person.</li>
	<li>The last two characters determine the seat allotted to that person.</li>
</ul>

<p>Return <em>the number of passengers who are <strong>strictly </strong><strong>more than 60 years old</strong>.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> details = [&quot;7868190130M7522&quot;,&quot;5303914400F9211&quot;,&quot;9273338290F4010&quot;]
<strong>Output:</strong> 2
<strong>Explanation:</strong> The passengers at indices 0, 1, and 2 have ages 75, 92, and 40. Thus, there are 2 people who are over 60 years old.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> details = [&quot;1313579440F2036&quot;,&quot;2921522980M5644&quot;]
<strong>Output:</strong> 0
<strong>Explanation:</strong> None of the passengers are older than 60.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= details.length &lt;= 100</code></li>
	<li><code>details[i].length == 15</code></li>
	<li><code>details[i] consists of digits from &#39;0&#39; to &#39;9&#39;.</code></li>
	<li><code>details[i][10] is either &#39;M&#39; or &#39;F&#39; or &#39;O&#39;.</code></li>
	<li>The phone numbers and seat numbers of the passengers are distinct.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def countSeniors(self, details: List[str]) -> int:
        return sum(int(x[11:13]) > 60 for x in details)
```

### **Java**

```java
class Solution {
    public int countSeniors(String[] details) {
        int ans = 0;
        for (var x : details) {
            int age = Integer.parseInt(x.substring(11, 13));
            if (age > 60) {
                ++ans;
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int countSeniors(vector<string>& details) {
        int ans = 0;
        for (auto& x : details) {
            int age = stoi(x.substr(11, 2));
            ans += age > 60;
        }
        return ans;
    }
};
```

### **Go**

```go
func countSeniors(details []string) (ans int) {
	for _, x := range details {
		age, _ := strconv.Atoi(x[11:13])
		if age > 60 {
			ans++
		}
	}
	return
}
```

### **...**

```

```

<!-- tabs:end -->
