# [1441. Build an Array With Stack Operations](https://leetcode.com/problems/build-an-array-with-stack-operations)

[中文文档](/solution/1400-1499/1441.Build%20an%20Array%20With%20Stack%20Operations/README.md)

## Description

<p>You are given an array <code>target</code> and an integer <code>n</code>.</p>

<p>In each iteration, you will read a number from <code>list = [1, 2, 3, ..., n]</code>.</p>

<p>Build the <code>target</code> array using the following operations:</p>

<ul>
	<li><code>&quot;Push&quot;</code>: Reads a new element from the beginning list, and pushes it in the array.</li>
	<li><code>&quot;Pop&quot;</code>: Deletes the last element of the array.</li>
	<li>If the target array is already built, stop reading more elements.</li>
</ul>

<p>Return <em>a list of the operations needed to build </em><code>target</code>. The test cases are generated so that the answer is <strong>unique</strong>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> target = [1,3], n = 3
<strong>Output:</strong> [&quot;Push&quot;,&quot;Push&quot;,&quot;Pop&quot;,&quot;Push&quot;]
<strong>Explanation:</strong> 
Read number 1 and automatically push in the array -&gt; [1]
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
<strong>Explanation:</strong> You only need to read the first 2 numbers and stop.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= target.length &lt;= 100</code></li>
	<li><code>1 &lt;= n &lt;= 100</code></li>
	<li><code>1 &lt;= target[i] &lt;= n</code></li>
	<li><code>target</code> is strictly increasing.</li>
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
