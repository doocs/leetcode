# [946. Validate Stack Sequences](https://leetcode.com/problems/validate-stack-sequences)

[中文文档](/solution/0900-0999/0946.Validate%20Stack%20Sequences/README.md)

## Description

<p>Given two sequences <code>pushed</code> and <code>popped</code>&nbsp;<strong>with distinct values</strong>,&nbsp;return <code>true</code> if and only if this could have been the result of a sequence of push and pop operations on an initially empty stack.</p>

<p>&nbsp;</p>

<div>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input: </strong>pushed = <span id="example-input-1-1">[1,2,3,4,5]</span>, popped = <span id="example-input-1-2">[4,5,3,2,1]</span>
<strong>Output: </strong><span id="example-output-1">true</span>
<strong>Explanation: </strong>We might do the following sequence:
push(1), push(2), push(3), push(4), pop() -&gt; 4,
push(5), pop() -&gt; 5, pop() -&gt; 3, pop() -&gt; 2, pop() -&gt; 1
</pre>

<div>
<p><strong>Example 2:</strong></p>

<pre>
<strong>Input: </strong>pushed = <span id="example-input-2-1">[1,2,3,4,5]</span>, popped = <span id="example-input-2-2">[4,3,5,1,2]</span>
<strong>Output: </strong><span id="example-output-2">false</span>
<strong>Explanation: </strong>1 cannot be popped before 2.
</pre>
</div>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= pushed.length == popped.length &lt;= 1000</code></li>
	<li><code>0 &lt;= pushed[i], popped[i] &lt; 1000</code></li>
	<li><code>pushed</code> is a permutation of <code>popped</code>.</li>
	<li><code>pushed</code> and <code>popped</code> have distinct values.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def validateStackSequences(self, pushed: List[int], popped: List[int]) -> bool:
        stk, j, n = [], 0, len(popped)
        for x in pushed:
            stk.append(x)
            while stk and j < n and stk[-1] == popped[j]:
                stk.pop()
                j += 1
        return j == n
```

### **Java**

```java
class Solution {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Deque<Integer> stk = new ArrayDeque<>();
        int j = 0, n = popped.length;
        for (int x : pushed) {
            stk.push(x);
            while (!stk.isEmpty() && j < n && stk.peek() == popped[j]) {
                stk.pop();
                ++j;
            }
        }
        return j == n;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool validateStackSequences(vector<int>& pushed, vector<int>& popped) {
        int j = 0, n = popped.size();
        stack<int> stk;
        for (int x : pushed)
        {
            stk.push(x);
            while (!stk.empty() && j < n && stk.top() == popped[j])
            {
                stk.pop();
                ++j;
            }
        }
        return j == n;
    }
};
```

### **Go**

```go
func validateStackSequences(pushed []int, popped []int) bool {
	j, n := 0, len(popped)
	var stk []int
	for _, x := range pushed {
		stk = append(stk, x)
		for len(stk) > 0 && j < n && stk[len(stk)-1] == popped[j] {
			stk = stk[:len(stk)-1]
			j++
		}
	}
	return j == n
}
```

### **...**

```

```

<!-- tabs:end -->
