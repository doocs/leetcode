# [788. Rotated Digits](https://leetcode.com/problems/rotated-digits)

[中文文档](/solution/0700-0799/0788.Rotated%20Digits/README.md)

## Description

<p>An integer <code>x</code> is a <strong>good</strong> if after rotating each digit individually by 180 degrees, we get a valid number that is different from <code>x</code>. Each digit must be rotated - we cannot choose to leave it alone.</p>

<p>A number is valid if each digit remains a digit after rotation. For example:</p>

<ul>
	<li><code>0</code>, <code>1</code>, and <code>8</code> rotate to themselves,</li>
	<li><code>2</code> and <code>5</code> rotate to each other (in this case they are rotated in a different direction, in other words, <code>2</code> or <code>5</code> gets mirrored),</li>
	<li><code>6</code> and <code>9</code> rotate to each other, and</li>
	<li>the rest of the numbers do not rotate to any other number and become invalid.</li>
</ul>

<p>Given an integer <code>n</code>, return <em>the number of <strong>good</strong> integers in the range </em><code>[1, n]</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 10
<strong>Output:</strong> 4
<strong>Explanation:</strong> There are four good numbers in the range [1, 10] : 2, 5, 6, 9.
Note that 1 and 10 are not good numbers, since they remain unchanged after rotating.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 1
<strong>Output:</strong> 0
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 2
<strong>Output:</strong> 1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>4</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def rotatedDigits(self, n: int) -> int:
        def check(x):
            y, t = 0, x
            k = 1
            while t:
                v = t % 10
                if v not in d:
                    return False
                y = d[v] * k + y
                k *= 10
                t //= 10
            return x != y

        d = {0: 0, 1: 1, 8: 8, 2: 5, 5: 2, 6: 9, 9: 6}
        return sum(check(i) for i in range(1, n + 1))
```

### **Java**

```java
class Solution {
    private static final Map<Integer, Integer> d = new HashMap<>();
    static {
        d.put(0, 0);
        d.put(1, 1);
        d.put(8, 8);
        d.put(2, 5);
        d.put(5, 2);
        d.put(6, 9);
        d.put(9, 6);
    }

    public int rotatedDigits(int n) {
        int ans = 0;
        for (int i = 1; i <= n; ++i) {
            if (check(i)) {
                ++ans;
            }
        }
        return ans;
    }

    private boolean check(int x) {
        int y = 0, t = x;
        int k = 1;
        while (t > 0) {
            int v = t % 10;
            if (!d.containsKey(v)) {
                return false;
            }
            y = d.get(v) * k + y;
            k *= 10;
            t /= 10;
        }
        return x != y;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    unordered_map<int, int> d{{0, 0}, {1, 1}, {8, 8}, {2, 5}, {5, 2}, {6, 9}, {9, 6}};

    int rotatedDigits(int n) {
        int ans = 0;
        for (int i = 1; i <= n; ++i) {
            ans += check(i);
        }
        return ans;
    }

    bool check(int x) {
        int y = 0, t = x;
        int k = 1;
        while (t) {
            int v = t % 10;
            if (!d.count(v)) {
                return false;
            }
            y = d[v] * k + y;
            k *= 10;
            t /= 10;
        }
        return x != y;
    }
};
```

### **Go**

```go
func rotatedDigits(n int) int {
	d := map[int]int{0: 0, 1: 1, 8: 8, 2: 5, 5: 2, 6: 9, 9: 6}
	check := func(x int) bool {
		y, t := 0, x
		k := 1
		for ; t > 0; t /= 10 {
			v := t % 10
			if _, ok := d[v]; !ok {
				return false
			}
			y = d[v]*k + y
			k *= 10
		}
		return x != y
	}
	ans := 0
	for i := 1; i <= n; i++ {
		if check(i) {
			ans++
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
