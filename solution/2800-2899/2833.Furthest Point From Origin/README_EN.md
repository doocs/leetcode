# [2833. Furthest Point From Origin](https://leetcode.com/problems/furthest-point-from-origin)

[中文文档](/solution/2800-2899/2833.Furthest%20Point%20From%20Origin/README.md)

## Description

<p>You are given a string <code>moves</code> of length <code>n</code> consisting only of characters <code>&#39;L&#39;</code>, <code>&#39;R&#39;</code>, and <code>&#39;_&#39;</code>. The string represents your movement on a number line starting from the origin <code>0</code>.</p>

<p>In the <code>i<sup>th</sup></code> move, you can choose one of the following directions:</p>

<ul>
	<li>move to the left if <code>moves[i] = &#39;L&#39;</code> or <code>moves[i] = &#39;_&#39;</code></li>
	<li>move to the right if <code>moves[i] = &#39;R&#39;</code> or <code>moves[i] = &#39;_&#39;</code></li>
</ul>

<p>Return <em>the <strong>distance from the origin</strong> of the <strong>furthest</strong> point you can get to after </em><code>n</code><em> moves</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> moves = &quot;L_RL__R&quot;
<strong>Output:</strong> 3
<strong>Explanation:</strong> The furthest point we can reach from the origin 0 is point -3 through the following sequence of moves &quot;LLRLLLR&quot;.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> moves = &quot;_R__LL_&quot;
<strong>Output:</strong> 5
<strong>Explanation:</strong> The furthest point we can reach from the origin 0 is point -5 through the following sequence of moves &quot;LRLLLLL&quot;.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> moves = &quot;_______&quot;
<strong>Output:</strong> 7
<strong>Explanation:</strong> The furthest point we can reach from the origin 0 is point 7 through the following sequence of moves &quot;RRRRRRR&quot;.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= moves.length == n &lt;= 50</code></li>
	<li><code>moves</code> consists only of characters <code>&#39;L&#39;</code>, <code>&#39;R&#39;</code> and <code>&#39;_&#39;</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def furthestDistanceFromOrigin(self, moves: str) -> int:
        return abs(moves.count("L") - moves.count("R")) + moves.count("_")
```

### **Java**

```java
class Solution {
    public int furthestDistanceFromOrigin(String moves) {
        return Math.abs(count(moves, 'L') - count(moves, 'R')) + count(moves, '_');
    }

    private int count(String s, char c) {
        int cnt = 0;
        for (int i = 0; i < s.length(); ++i) {
            if (s.charAt(i) == c) {
                ++cnt;
            }
        }
        return cnt;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int furthestDistanceFromOrigin(string moves) {
        auto cnt = [&](char c) {
            return count(moves.begin(), moves.end(), c);
        };
        return abs(cnt('L') - cnt('R')) + cnt('_');
    }
};
```

### **Go**

```go
func furthestDistanceFromOrigin(moves string) int {
	count := func(c string) int { return strings.Count(moves, c) }
	return abs(count("L")-count("R")) + count("_")
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

### **TypeScript**

```ts
function furthestDistanceFromOrigin(moves: string): number {
    const count = (c: string) => moves.split('').filter(x => x === c).length;
    return Math.abs(count('L') - count('R')) + count('_');
}
```

### **...**

```

```

<!-- tabs:end -->
