# [1441. Build an Array With Stack Operations](https://leetcode.com/problems/build-an-array-with-stack-operations)

[中文文档](/solution/1400-1499/1441.Build%20an%20Array%20With%20Stack%20Operations/README.md)

## Description

<p>Given an array <code>target</code> and&nbsp;an integer <code>n</code>. In each iteration, you will read a number from &nbsp;<code>list = {1,2,3..., n}</code>.</p>

<p>Build the <code>target</code>&nbsp;array&nbsp;using the following operations:</p>

<ul>
	<li><strong>Push</strong>: Read a new element from the beginning&nbsp;<code>list</code>, and push it in the array.</li>
	<li><strong>Pop</strong>: delete the last element of&nbsp;the array.</li>
	<li>If the target array is already&nbsp;built, stop reading more elements.</li>
</ul>

<p>Return the operations to build the target array. You are guaranteed that the answer is unique.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> target = [1,3], n = 3
<strong>Output:</strong> [&quot;Push&quot;,&quot;Push&quot;,&quot;Pop&quot;,&quot;Push&quot;]
<strong>Explanation: 
</strong>Read number 1 and automatically push in the array -&gt; [1]
Read number 2 and automatically push in the array then Pop it -&gt; [1]
Read number 3 and automatically push in the array -&gt; [1,3]
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> target = [1,2,3], n = 3
<strong>Output:</strong> [&quot;Push&quot;,&quot;Push&quot;,&quot;Push&quot;]
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> target = [1,2], n = 4
<strong>Output:</strong> [&quot;Push&quot;,&quot;Push&quot;]
<strong>Explanation: </strong>You only need to read the first 2 numbers and stop.
</pre>

<p><strong>Example 4:</strong></p>

<pre>
<strong>Input:</strong> target = [2,3,4], n = 4
<strong>Output:</strong> [&quot;Push&quot;,&quot;Pop&quot;,&quot;Push&quot;,&quot;Push&quot;,&quot;Push&quot;]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= target.length &lt;= 100</code></li>
	<li><code>1 &lt;= target[i]&nbsp;&lt;= n</code></li>
	<li><code>1 &lt;= n &lt;= 100</code></li>
	<li><code>target</code> is strictly&nbsp;increasing.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def buildArray(self, target: List[int], n: int) -> List[str]:
        cur, ans = 1, []
        for t in target:
            for i in range(cur, n + 1):
                ans.append('Push')
                if t == i:
                    cur = i + 1
                    break
                ans.append('Pop')
        return ans
```

### **Java**

```java
class Solution {
    public List<String> buildArray(int[] target, int n) {
        List<String> ans = new ArrayList<>();
        int cur = 1;
        for (int t : target) {
            for (int i = cur; i <= n; ++i) {
                ans.add("Push");
                if (t == i) {
                    cur = i + 1;
                    break;
                }
                ans.add("Pop");
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
    vector<string> buildArray(vector<int>& target, int n) {
        vector<string> ans;
        int cur = 1;
        for (int t : target)
        {
            for (int i = cur; i <= n; ++i)
            {
                ans.push_back("Push");
                if (t == i)
                {
                    cur = i + 1;
                    break;
                }
                ans.push_back("Pop");
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func buildArray(target []int, n int) []string {
	var ans []string
	cur := 1
	for _, t := range target {
		for i := cur; i <= n; i++ {
			ans = append(ans, "Push")
			if t == i {
				cur = i + 1
				break
			}
			ans = append(ans, "Pop")
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
