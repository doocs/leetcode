# [1342. Number of Steps to Reduce a Number to Zero](https://leetcode.com/problems/number-of-steps-to-reduce-a-number-to-zero)

[中文文档](/solution/1300-1399/1342.Number%20of%20Steps%20to%20Reduce%20a%20Number%20to%20Zero/README.md)

## Description

<p>Given a non-negative integer <code>num</code>, return the number of steps to reduce it to zero. If the current number is even, you have to divide it by 2, otherwise, you have to subtract 1 from it.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> num = 14
<strong>Output:</strong> 6
<strong>Explanation:</strong>&nbsp;
Step 1) 14 is even; divide by 2 and obtain 7.&nbsp;
Step 2) 7 is odd; subtract 1 and obtain 6.
Step 3) 6 is even; divide by 2 and obtain 3.&nbsp;
Step 4) 3 is odd; subtract 1 and obtain 2.&nbsp;
Step 5) 2 is even; divide by 2 and obtain 1.&nbsp;
Step 6) 1 is odd; subtract 1 and obtain 0.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> num = 8
<strong>Output:</strong> 4
<strong>Explanation:</strong>&nbsp;
Step 1) 8 is even; divide by 2 and obtain 4.&nbsp;
Step 2) 4 is even; divide by 2 and obtain 2.&nbsp;
Step 3) 2 is even; divide by 2 and obtain 1.&nbsp;
Step 4) 1 is odd; subtract 1 and obtain 0.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> num = 123
<strong>Output:</strong> 12
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= num &lt;= 10^6</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def numberOfSteps(self, num: int) -> int:
        res = 0
        while num:
            if (num & 1) == 0:
                num >>= 1
            else:
                num -= 1
            res += 1
        return res
```

### **Java**

```java
class Solution {
    public int numberOfSteps(int num) {
        int res = 0;
        while (num != 0) {
            if ((num & 1) == 0) {
                num >>= 1;
            } else {
                --num;
            }
            ++res;
        }
        return res;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int numberOfSteps(int num) {
        int res = 0;
        while (num)
        {
            if ((num & 1) == 0) num >>= 1;
            else --num;
            ++res;
        }
        return res;
    }
};
```

### **Go**

```go
func numberOfSteps(num int) int {
	res := 0
	for num != 0 {
		if (num & 1) == 0 {
			num >>= 1
		} else {
			num--
		}
		res++
	}
	return res
}
```

### **...**

```

```

<!-- tabs:end -->
