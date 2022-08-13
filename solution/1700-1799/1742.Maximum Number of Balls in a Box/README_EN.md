# [1742. Maximum Number of Balls in a Box](https://leetcode.com/problems/maximum-number-of-balls-in-a-box)

[中文文档](/solution/1700-1799/1742.Maximum%20Number%20of%20Balls%20in%20a%20Box/README.md)

## Description

<p>You are working in a ball factory where you have <code>n</code> balls numbered from <code>lowLimit</code> up to <code>highLimit</code> <strong>inclusive</strong> (i.e., <code>n == highLimit - lowLimit + 1</code>), and an infinite number of boxes numbered from <code>1</code> to <code>infinity</code>.</p>

<p>Your job at this factory is to put each ball in the box with a number equal to the sum of digits of the ball&#39;s number. For example, the ball number <code>321</code> will be put in the box number <code>3 + 2 + 1 = 6</code> and the ball number <code>10</code> will be put in the box number <code>1 + 0 = 1</code>.</p>

<p>Given two integers <code>lowLimit</code> and <code>highLimit</code>, return<em> the number of balls in the box with the most balls.</em></p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> lowLimit = 1, highLimit = 10
<strong>Output:</strong> 2
<strong>Explanation:</strong>
Box Number:  1 2 3 4 5 6 7 8 9 10 11 ...
Ball Count:  2 1 1 1 1 1 1 1 1 0  0  ...
Box 1 has the most number of balls with 2 balls.</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> lowLimit = 5, highLimit = 15
<strong>Output:</strong> 2
<strong>Explanation:</strong>
Box Number:  1 2 3 4 5 6 7 8 9 10 11 ...
Ball Count:  1 1 1 1 2 2 1 1 1 0  0  ...
Boxes 5 and 6 have the most number of balls with 2 balls in each.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> lowLimit = 19, highLimit = 28
<strong>Output:</strong> 2
<strong>Explanation:</strong>
Box Number:  1 2 3 4 5 6 7 8 9 10 11 12 ...
Ball Count:  0 1 1 1 1 1 1 1 1 2  0  0  ...
Box 10 has the most number of balls with 2 balls.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= lowLimit &lt;= highLimit &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def countBalls(self, lowLimit: int, highLimit: int) -> int:
        counter = [0] * 60
        for i in range(lowLimit, highLimit + 1):
            s = 0
            while i:
                s += i % 10
                i //= 10
            counter[s] += 1
        return max(counter)
```

### **Java**

```java
class Solution {
    public int countBalls(int lowLimit, int highLimit) {
        int[] counter = new int[60];
        int ans = 0;
        for (int i = lowLimit; i <= highLimit; ++i) {
            int s = 0;
            int j = i;
            while (j > 0) {
                s += (j % 10);
                j /= 10;
            }
            ++counter[s];
            ans = Math.max(ans, counter[s]);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int countBalls(int lowLimit, int highLimit) {
        vector<int> counter(60);
        int ans = 0;
        for (int i = lowLimit; i <= highLimit; ++i) {
            int s = 0, j = i;
            while (j) {
                s += (j % 10);
                j /= 10;
            }
            ++counter[s];
            ans = max(ans, counter[s]);
        }
        return ans;
    }
};
```

### **Go**

```go
func countBalls(lowLimit int, highLimit int) int {
	counter := make([]int, 60)
	ans := 0
	for i := lowLimit; i <= highLimit; i++ {
		s, j := 0, i
		for j > 0 {
			s += (j % 10)
			j /= 10
		}
		counter[s]++
		if counter[s] > ans {
			ans = counter[s]
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
