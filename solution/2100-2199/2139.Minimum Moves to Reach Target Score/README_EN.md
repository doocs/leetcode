# [2139. Minimum Moves to Reach Target Score](https://leetcode.com/problems/minimum-moves-to-reach-target-score)

[中文文档](/solution/2100-2199/2139.Minimum%20Moves%20to%20Reach%20Target%20Score/README.md)

## Description

<p>You are playing a game with integers. You start with the integer <code>1</code> and you want to reach the integer <code>target</code>.</p>

<p>In one move, you can either:</p>

<ul>
	<li><strong>Increment</strong> the current integer by one (i.e., <code>x = x + 1</code>).</li>
	<li><strong>Double</strong> the current integer (i.e., <code>x = 2 * x</code>).</li>
</ul>

<p>You can use the <strong>increment</strong> operation <strong>any</strong> number of times, however, you can only use the <strong>double</strong> operation <strong>at most</strong> <code>maxDoubles</code> times.</p>

<p>Given the two integers <code>target</code> and <code>maxDoubles</code>, return <em>the minimum number of moves needed to reach </em><code>target</code><em> starting with </em><code>1</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> target = 5, maxDoubles = 0
<strong>Output:</strong> 4
<strong>Explanation:</strong> Keep incrementing by 1 until you reach target.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> target = 19, maxDoubles = 2
<strong>Output:</strong> 7
<strong>Explanation:</strong> Initially, x = 1
Increment 3 times so x = 4
Double once so x = 8
Increment once so x = 9
Double again so x = 18
Increment once so x = 19
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> target = 10, maxDoubles = 4
<strong>Output:</strong> 4
<strong>Explanation:</strong><b> </b>Initially, x = 1
Increment once so x = 2
Double once so x = 4
Increment once so x = 5
Double again so x = 10
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= target &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= maxDoubles &lt;= 100</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minMoves(self, target: int, maxDoubles: int) -> int:
        if target == 1:
            return 0
        if maxDoubles == 0:
            return target - 1
        if target % 2 == 0 and maxDoubles:
            return 1 + self.minMoves(target >> 1, maxDoubles - 1)
        return 1 + self.minMoves(target - 1, maxDoubles)
```

### **Java**

```java
class Solution {
    public int minMoves(int target, int maxDoubles) {
        if (target == 1) {
            return 0;
        }
        if (maxDoubles == 0) {
            return target - 1;
        }
        if (target % 2 == 0 && maxDoubles > 0) {
            return 1 + minMoves(target >> 1, maxDoubles - 1);
        }
        return 1 + minMoves(target - 1, maxDoubles);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minMoves(int target, int maxDoubles) {
        if (target == 1) return 0;
        if (maxDoubles == 0) return target - 1;
        if (target % 2 == 0 && maxDoubles) return 1 + minMoves(target >> 1, maxDoubles - 1);
        return 1 + minMoves(target - 1, maxDoubles);
    }
};
```

### **Go**

```go
func minMoves(target int, maxDoubles int) int {
	if target == 1 {
		return 0
	}
	if maxDoubles == 0 {
		return target - 1
	}
	if target%2 == 0 && maxDoubles > 0 {
		return 1 + minMoves(target>>1, maxDoubles-1)
	}
	return 1 + minMoves(target-1, maxDoubles)
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
