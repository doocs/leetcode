# [1441. Build an Array With Stack Operations](https://leetcode.com/problems/build-an-array-with-stack-operations)

[中文文档](/solution/1400-1499/1441.Build%20an%20Array%20With%20Stack%20Operations/README.md)

## Description

<p>You are given an integer array <code>target</code> and an integer <code>n</code>.</p>

<p>You have an empty stack with the two following operations:</p>

<ul>
	<li><strong><code>&quot;Push&quot;</code></strong>: pushes an integer to the top of the stack.</li>
	<li><strong><code>&quot;Pop&quot;</code></strong>: removes the integer on the top of the stack.</li>
</ul>

<p>You also have a stream of the integers in the range <code>[1, n]</code>.</p>

<p>Use the two stack operations to make the numbers in the stack (from the bottom to the top) equal to <code>target</code>. You should follow the following rules:</p>

<ul>
	<li>If the stream of the integers is not empty, pick the next integer from the stream and push it to the top of the stack.</li>
	<li>If the stack is not empty, pop the integer at the top of the stack.</li>
	<li>If, at any moment, the elements in the stack (from the bottom to the top) are equal to <code>target</code>, do not read new integers from the stream and do not do more operations on the stack.</li>
</ul>

<p>Return <em>the stack operations needed to build </em><code>target</code> following the mentioned rules. If there are multiple valid answers, return <strong>any of them</strong>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> target = [1,3], n = 3
<strong>Output:</strong> [&quot;Push&quot;,&quot;Push&quot;,&quot;Pop&quot;,&quot;Push&quot;]
<strong>Explanation:</strong> Initially the stack s is empty. The last element is the top of the stack.
Read 1 from the stream and push it to the stack. s = [1].
Read 2 from the stream and push it to the stack. s = [1,2].
Pop the integer on the top of the stack. s = [1].
Read 3 from the stream and push it to the stack. s = [1,3].
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> target = [1,2,3], n = 3
<strong>Output:</strong> [&quot;Push&quot;,&quot;Push&quot;,&quot;Push&quot;]
<strong>Explanation:</strong> Initially the stack s is empty. The last element is the top of the stack.
Read 1 from the stream and push it to the stack. s = [1].
Read 2 from the stream and push it to the stack. s = [1,2].
Read 3 from the stream and push it to the stack. s = [1,2,3].
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> target = [1,2], n = 4
<strong>Output:</strong> [&quot;Push&quot;,&quot;Push&quot;]
<strong>Explanation:</strong> Initially the stack s is empty. The last element is the top of the stack.
Read 1 from the stream and push it to the stack. s = [1].
Read 2 from the stream and push it to the stack. s = [1,2].
Since the stack (from the bottom to the top) is equal to target, we stop the stack operations.
The answers that read integer 3 from the stream are not accepted.
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
        for (int t : target) {
            for (int i = cur; i <= n; ++i) {
                ans.push_back("Push");
                if (t == i) {
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
