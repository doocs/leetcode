# [1753. Maximum Score From Removing Stones](https://leetcode.com/problems/maximum-score-from-removing-stones)

[中文文档](/solution/1700-1799/1753.Maximum%20Score%20From%20Removing%20Stones/README.md)

## Description

<p>You are playing a solitaire game with <strong>three piles</strong> of stones of sizes <code>a</code>​​​​​​, <code>b</code>,​​​​​​ and <code>c</code>​​​​​​ respectively. Each turn you choose two <strong>different non-empty </strong>piles, take one stone from each, and add <code>1</code> point to your score. The game stops when there are <strong>fewer than two non-empty</strong> piles (meaning there are no more available moves).</p>

<p>Given three integers <code>a</code>​​​​​, <code>b</code>,​​​​​ and <code>c</code>​​​​​, return <em>the</em> <strong><em>maximum</em> </strong><em><strong>score</strong> you can get.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> a = 2, b = 4, c = 6
<strong>Output:</strong> 6
<strong>Explanation:</strong> The starting state is (2, 4, 6). One optimal set of moves is:
- Take from 1st and 3rd piles, state is now (1, 4, 5)
- Take from 1st and 3rd piles, state is now (0, 4, 4)
- Take from 2nd and 3rd piles, state is now (0, 3, 3)
- Take from 2nd and 3rd piles, state is now (0, 2, 2)
- Take from 2nd and 3rd piles, state is now (0, 1, 1)
- Take from 2nd and 3rd piles, state is now (0, 0, 0)
There are fewer than two non-empty piles, so the game ends. Total: 6 points.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> a = 4, b = 4, c = 6
<strong>Output:</strong> 7
<strong>Explanation:</strong> The starting state is (4, 4, 6). One optimal set of moves is:
- Take from 1st and 2nd piles, state is now (3, 3, 6)
- Take from 1st and 3rd piles, state is now (2, 3, 5)
- Take from 1st and 3rd piles, state is now (1, 3, 4)
- Take from 1st and 3rd piles, state is now (0, 3, 3)
- Take from 2nd and 3rd piles, state is now (0, 2, 2)
- Take from 2nd and 3rd piles, state is now (0, 1, 1)
- Take from 2nd and 3rd piles, state is now (0, 0, 0)
There are fewer than two non-empty piles, so the game ends. Total: 7 points.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> a = 1, b = 8, c = 8
<strong>Output:</strong> 8
<strong>Explanation:</strong> One optimal set of moves is to take from the 2nd and 3rd piles for 8 turns until they are empty.
After that, there are fewer than two non-empty piles, so the game ends.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= a, b, c &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maximumScore(self, a: int, b: int, c: int) -> int:
        s = sorted([a, b, c])
        ans = 0
        while s[1]:
            ans += 1
            s[1] -= 1
            s[2] -= 1
            s.sort()
        return ans
```

```python
class Solution:
    def maximumScore(self, a: int, b: int, c: int) -> int:
        a, b, c = sorted([a, b, c])
        if a + b < c:
            return a + b
        return (a + b + c) >> 1
```

### **Java**

```java
class Solution {
    public int maximumScore(int a, int b, int c) {
        int[] s = new int[] {a, b, c};
        Arrays.sort(s);
        int ans = 0;
        while (s[1] > 0) {
            ++ans;
            s[1]--;
            s[2]--;
            Arrays.sort(s);
        }
        return ans;
    }
}
```

```java
class Solution {
    public int maximumScore(int a, int b, int c) {
        int[] s = new int[] {a, b, c};
        Arrays.sort(s);
        if (s[0] + s[1] < s[2]) {
            return s[0] + s[1];
        }
        return (a + b + c) >> 1;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maximumScore(int a, int b, int c) {
        vector<int> s = {a, b, c};
        sort(s.begin(), s.end());
        int ans = 0;
        while (s[1]) {
            ++ans;
            s[1]--;
            s[2]--;
            sort(s.begin(), s.end());
        }
        return ans;
    }
};
```

```cpp
class Solution {
public:
    int maximumScore(int a, int b, int c) {
        vector<int> s = {a, b, c};
        sort(s.begin(), s.end());
        if (s[0] + s[1] < s[2]) return s[0] + s[1];
        return (a + b + c) >> 1;
    }
};
```

### **Go**

```go
func maximumScore(a int, b int, c int) (ans int) {
	s := []int{a, b, c}
	sort.Ints(s)
	for s[1] > 0 {
		ans++
		s[1]--
		s[2]--
		sort.Ints(s)
	}
	return
}
```

```go
func maximumScore(a int, b int, c int) int {
	s := []int{a, b, c}
	sort.Ints(s)
	if s[0]+s[1] < s[2] {
		return s[0] + s[1]
	}
	return (a + b + c) >> 1
}
```

### **...**

```

```

<!-- tabs:end -->
