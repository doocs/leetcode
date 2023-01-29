# [2549. Count Distinct Numbers on Board](https://leetcode.com/problems/count-distinct-numbers-on-board)

[中文文档](/solution/2500-2599/2549.Count%20Distinct%20Numbers%20on%20Board/README.md)

## Description

<p>You are given a positive integer <code>n</code>, that is initially placed on a board. Every day, for <code>10<sup>9</sup></code> days, you perform the following procedure:</p>

<ul>
	<li>For each number <code>x</code> present on the board, find all numbers <code>1 &lt;= i &lt;= n</code> such that <code>x % i == 1</code>.</li>
	<li>Then, place those numbers on the board.</li>
</ul>

<p>Return<em> the number of <strong>distinct</strong> integers present on the board after</em> <code>10<sup>9</sup></code> <em>days have elapsed</em>.</p>

<p><strong>Note:</strong></p>

<ul>
	<li>Once a number is placed on the board, it will remain on it until the end.</li>
	<li><code>%</code>&nbsp;stands&nbsp;for the modulo operation. For example,&nbsp;<code>14 % 3</code> is <code>2</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 5
<strong>Output:</strong> 4
<strong>Explanation:</strong> Initially, 5 is present on the board. 
The next day, 2 and 4 will be added since 5 % 2 == 1 and 5 % 4 == 1. 
After that day, 3 will be added to the board because 4 % 3 == 1. 
At the end of a billion days, the distinct numbers on the board will be 2, 3, 4, and 5. 
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 3
<strong>Output:</strong> 2
<strong>Explanation:</strong> 
Since 3 % 2 == 1, 2 will be added to the board. 
After a billion days, the only two distinct numbers on the board are 2 and 3. 
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 100</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def distinctIntegers(self, n: int) -> int:
        return max(1, n - 1)
```

### **Java**

```java
class Solution {
    public int distinctIntegers(int n) {
        return Math.max(1, n - 1);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int distinctIntegers(int n) {
        return max(1, n - 1);
    }
};
```

### **Go**

```go
func distinctIntegers(n int) int {
	if n == 1 {
		return 1
	}
	return n - 1
}
```

### **TypeScript**

```ts
function distinctIntegers(n: number): number {
    return Math.max(1, n - 1);
}
```

### **...**

```

```

<!-- tabs:end -->
