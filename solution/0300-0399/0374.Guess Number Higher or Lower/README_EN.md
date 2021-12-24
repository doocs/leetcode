# [374. Guess Number Higher or Lower](https://leetcode.com/problems/guess-number-higher-or-lower)

[中文文档](/solution/0300-0399/0374.Guess%20Number%20Higher%20or%20Lower/README.md)

## Description

<p>We are playing the Guess Game. The game is as follows:</p>

<p>I pick a number from <code>1</code> to <code>n</code>. You have to guess which number I picked.</p>

<p>Every time you guess wrong, I will tell you whether the number I picked is higher or lower than your guess.</p>

<p>You call a pre-defined API <code>int guess(int num)</code>, which returns 3 possible results:</p>

<ul>
	<li><code>-1</code>: The number I picked is lower than your guess (i.e. <code>pick &lt; num</code>).</li>
	<li><code>1</code>: The number I picked is higher than your guess (i.e. <code>pick &gt; num</code>).</li>
	<li><code>0</code>: The number I picked is equal to your guess (i.e. <code>pick == num</code>).</li>
</ul>

<p>Return <em>the number that I picked</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>
<pre><strong>Input:</strong> n = 10, pick = 6
<strong>Output:</strong> 6
</pre><p><strong>Example 2:</strong></p>
<pre><strong>Input:</strong> n = 1, pick = 1
<strong>Output:</strong> 1
</pre><p><strong>Example 3:</strong></p>
<pre><strong>Input:</strong> n = 2, pick = 1
<strong>Output:</strong> 1
</pre><p><strong>Example 4:</strong></p>
<pre><strong>Input:</strong> n = 2, pick = 2
<strong>Output:</strong> 2
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 2<sup>31</sup> - 1</code></li>
	<li><code>1 &lt;= pick &lt;= n</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
# The guess API is already defined for you.
# @param num, your guess
# @return -1 if my number is lower, 1 if my number is higher, otherwise return 0
# def guess(num: int) -> int:

class Solution:
    def guessNumber(self, n: int) -> int:
        left, right = 1, n
        while left < right:
            mid = (left + right) >> 1
            if guess(mid) <= 0:
                right = mid
            else:
                left = mid + 1
        return left
```

### **Java**

```java
/**
 * Forward declaration of guess API.
 * @param  num   your guess
 * @return 	     -1 if num is lower than the guess number
 *			      1 if num is higher than the guess number
 *               otherwise return 0
 * int guess(int num);
 */

public class Solution extends GuessGame {
    public int guessNumber(int n) {
        int left = 1, right = n;
        while (left < right) {
            int mid = (left + right) >>> 1;
            if (guess(mid) <= 0) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
```

### **C++**

```cpp
/**
 * Forward declaration of guess API.
 * @param  num   your guess
 * @return 	     -1 if num is lower than the guess number
 *			      1 if num is higher than the guess number
 *               otherwise return 0
 * int guess(int num);
 */

class Solution {
public:
    int guessNumber(int n) {
        int left = 1, right = n;
        while (left < right) {
            int mid = left + ((right - left) >> 1);
            if (guess(mid) <= 0) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
};
```

### **Go**

```go
/**
 * Forward declaration of guess API.
 * @param  num   your guess
 * @return 	     -1 if num is lower than the guess number
 *			      1 if num is higher than the guess number
 *               otherwise return 0
 * func guess(num int) int;
 */

func guessNumber(n int) int {
	left, right := 1, n
	for left < right {
		mid := (left + right) >> 1
		if guess(mid) <= 0 {
			right = mid
		} else {
			left = mid + 1
		}
	}
	return left
}
```

### **C#**

```cs
/**
 * Forward declaration of guess API.
 * @param  num   your guess
 * @return 	     -1 if num is lower than the guess number
 *			      1 if num is higher than the guess number
 *               otherwise return 0
 * int guess(int num);
 */

public class Solution : GuessGame {
    public int GuessNumber(int n) {
        int left = 1, right = n;
        while (left < right)
        {
            int mid = left + ((right - left) >> 1);
            if (guess(mid) <= 0)
            {
                right = mid;
            }
            else
            {
                left = mid + 1;
            }
        }
        return left;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
