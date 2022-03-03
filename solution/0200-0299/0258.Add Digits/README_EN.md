# [258. Add Digits](https://leetcode.com/problems/add-digits)

[中文文档](/solution/0200-0299/0258.Add%20Digits/README.md)

## Description

<p>Given an integer <code>num</code>, repeatedly add all its digits until the result has only one digit, and return it.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> num = 38
<strong>Output:</strong> 2
<strong>Explanation:</strong> The process is
38 --&gt; 3 + 8 --&gt; 11
11 --&gt; 1 + 1 --&gt; 2 
Since 2 has only one digit, return it.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> num = 0
<strong>Output:</strong> 0
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= num &lt;= 2<sup>31</sup> - 1</code></li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow up:</strong> Could you do it without any loop/recursion in <code>O(1)</code> runtime?</p>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def addDigits(self, num: int) -> int:
        return 0 if num == 0 else (num - 1) % 9 + 1
```

### **Java**

```java
class Solution {
    public int addDigits(int num) {
        return (num - 1) % 9 + 1;
    }
}

```

### **C++**

```cpp
class Solution {
public:
    int addDigits(int num) {
        return (num - 1) % 9 + 1;
    }
};
```

### **Go**

```go
func addDigits(num int) int {
	if num == 0 {
		return 0
	}
	return (num-1)%9 + 1
}
```

### **Rust**

```rust
impl Solution {
    pub fn add_digits(num: i32) -> i32 {
        if num < 10 {
            return num;
        }
        Self::add_digits(
            num.to_string()
                .chars()
                .map(|c| c.to_string().parse::<i32>().unwrap())
                .sum::<i32>(),
        )
    }
}
```

```rust
impl Solution {
    pub fn add_digits(mut num: i32) -> i32 {
        (num - 1) % 9 + 1
    }
}
```

### **...**

```

```

<!-- tabs:end -->
