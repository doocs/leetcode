# [946. Validate Stack Sequences](https://leetcode.com/problems/validate-stack-sequences)

[中文文档](/solution/0900-0999/0946.Validate%20Stack%20Sequences/README.md)

## Description

<p>Given two integer arrays <code>pushed</code> and <code>popped</code> each with distinct values, return <code>true</code><em> if this could have been the result of a sequence of push and pop operations on an initially empty stack, or </em><code>false</code><em> otherwise.</em></p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
<strong>Output:</strong> true
<strong>Explanation:</strong> We might do the following sequence:
push(1), push(2), push(3), push(4),
pop() -&gt; 4,
push(5),
pop() -&gt; 5, pop() -&gt; 3, pop() -&gt; 2, pop() -&gt; 1
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> pushed = [1,2,3,4,5], popped = [4,3,5,1,2]
<strong>Output:</strong> false
<strong>Explanation:</strong> 1 cannot be popped before 2.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= pushed.length &lt;= 1000</code></li>
	<li><code>0 &lt;= pushed[i] &lt;= 1000</code></li>
	<li>All the elements of <code>pushed</code> are <strong>unique</strong>.</li>
	<li><code>popped.length == pushed.length</code></li>
	<li><code>popped</code> is a permutation of <code>pushed</code>.</li>
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
        for (int x : pushed) {
            stk.push(x);
            while (!stk.empty() && j < n && stk.top() == popped[j]) {
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
