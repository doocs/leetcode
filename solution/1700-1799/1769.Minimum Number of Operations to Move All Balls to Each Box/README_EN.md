# [1769. Minimum Number of Operations to Move All Balls to Each Box](https://leetcode.com/problems/minimum-number-of-operations-to-move-all-balls-to-each-box)

[中文文档](/solution/1700-1799/1769.Minimum%20Number%20of%20Operations%20to%20Move%20All%20Balls%20to%20Each%20Box/README.md)

## Description

<p>You have <code>n</code> boxes. You are given a binary string <code>boxes</code> of length <code>n</code>, where <code>boxes[i]</code> is <code>&#39;0&#39;</code> if the <code>i<sup>th</sup></code> box is <strong>empty</strong>, and <code>&#39;1&#39;</code> if it contains <strong>one</strong> ball.</p>

<p>In one operation, you can move <strong>one</strong> ball from a box to an adjacent box. Box <code>i</code> is adjacent to box <code>j</code> if <code>abs(i - j) == 1</code>. Note that after doing so, there may be more than one ball in some boxes.</p>

<p>Return an array <code>answer</code> of size <code>n</code>, where <code>answer[i]</code> is the <strong>minimum</strong> number of operations needed to move all the balls to the <code>i<sup>th</sup></code> box.</p>

<p>Each <code>answer[i]</code> is calculated considering the <strong>initial</strong> state of the boxes.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> boxes = &quot;110&quot;
<strong>Output:</strong> [1,1,3]
<strong>Explanation:</strong> The answer for each box is as follows:
1) First box: you will have to move one ball from the second box to the first box in one operation.
2) Second box: you will have to move one ball from the first box to the second box in one operation.
3) Third box: you will have to move one ball from the first box to the third box in two operations, and move one ball from the second box to the third box in one operation.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> boxes = &quot;001011&quot;
<strong>Output:</strong> [11,8,5,4,3,4]</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == boxes.length</code></li>
	<li><code>1 &lt;= n &lt;= 2000</code></li>
	<li><code>boxes[i]</code> is either <code>&#39;0&#39;</code> or <code>&#39;1&#39;</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minOperations(self, boxes: str) -> List[int]:
        n = len(boxes)
        res = [0] * n
        total = 0
        for i, b in enumerate(boxes):
            if b == '1':
                res[0] += i
                total += 1
        left, right = 0, total
        for i in range(1, n):
            if boxes[i - 1] == '1':
                left += 1
                right -= 1
            res[i] = res[i - 1] + left - right
        return res
```

### **Java**

```java
class Solution {
    public int[] minOperations(String boxes) {
        int n = boxes.length();
        int[] res = new int[n];
        int total = 0;
        for (int i = 0; i < n; ++i) {
            if (boxes.charAt(i) == '1') {
                res[0] += i;
                ++total;
            }
        }
        int left = 0, right = total;
        for (int i = 1; i < n; ++i) {
            if (boxes.charAt(i - 1) == '1') {
                ++left;
                --right;
            }
            res[i] = res[i - 1] + left - right;
        }
        return res;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> minOperations(string boxes) {
        int n = boxes.size();
        vector<int> res(n);
        int total = 0;
        for (int i = 0; i < n; ++i) {
            if (boxes[i] == '1') {
                res[0] += i;
                ++total;
            }
        }
        int left = 0, right = total;
        for (int i = 1; i < n; ++i) {
            if (boxes[i - 1] == '1') {
                ++left;
                --right;
            }
            res[i] = res[i - 1] + left - right;
        }
        return res;
    }
};
```

### **Go**

```go
func minOperations(boxes string) []int {
	n := len(boxes)
	res := make([]int, n)
	total := 0
	for i, b := range boxes {
		if b == '1' {
			res[0] += i
			total++
		}
	}
	left, right := 0, total
	for i := 1; i < n; i++ {
		if boxes[i-1] == '1' {
			left++
			right--
		}
		res[i] = res[i-1] + left - right
	}
	return res
}
```

### **...**

```

```

<!-- tabs:end -->
