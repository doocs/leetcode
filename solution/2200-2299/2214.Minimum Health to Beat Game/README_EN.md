# [2214. Minimum Health to Beat Game](https://leetcode.com/problems/minimum-health-to-beat-game)

[中文文档](/solution/2200-2299/2214.Minimum%20Health%20to%20Beat%20Game/README.md)

## Description

<p>You are playing a game that has <code>n</code> levels numbered from <code>0</code> to <code>n - 1</code>. You are given a <strong>0-indexed</strong> integer array <code>damage</code> where <code>damage[i]</code> is the amount of health you will lose to complete the <code>i<sup>th</sup></code> level.</p>

<p>You are also given an integer <code>armor</code>. You may use your armor ability <strong>at most once</strong> during the game on <strong>any</strong> level which will protect you from <strong>at most</strong> <code>armor</code> damage.</p>

<p>You must complete the levels in order and your health must be <strong>greater than</strong> <code>0</code> at all times to beat the game.</p>

<p>Return <em>the <strong>minimum</strong> health you need to start with to beat the game.</em></p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> damage = [2,7,4,3], armor = 4
<strong>Output:</strong> 13
<strong>Explanation:</strong> One optimal way to beat the game starting at 13 health is:
On round 1, take 2 damage. You have 13 - 2 = 11 health.
On round 2, take 7 damage. You have 11 - 7 = 4 health.
On round 3, use your armor to protect you from 4 damage. You have 4 - 0 = 4 health.
On round 4, take 3 damage. You have 4 - 3 = 1 health.
Note that 13 is the minimum health you need to start with to beat the game.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> damage = [2,5,3,4], armor = 7
<strong>Output:</strong> 10
<strong>Explanation:</strong> One optimal way to beat the game starting at 10 health is:
On round 1, take 2 damage. You have 10 - 2 = 8 health.
On round 2, use your armor to protect you from 5 damage. You have 8 - 0 = 8 health.
On round 3, take 3 damage. You have 8 - 3 = 5 health.
On round 4, take 4 damage. You have 5 - 4 = 1 health.
Note that 10 is the minimum health you need to start with to beat the game.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> damage = [3,3,3], armor = 0
<strong>Output:</strong> 10
<strong>Explanation:</strong> One optimal way to beat the game starting at 10 health is:
On round 1, take 3 damage. You have 10 - 3 = 7 health.
On round 2, take 3 damage. You have 7 - 3 = 4 health.
On round 3, take 3 damage. You have 4 - 3 = 1 health.
Note that you did not use your armor ability.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == damage.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= damage[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= armor &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minimumHealth(self, damage: List[int], armor: int) -> int:
        return sum(damage) - min(max(damage), armor) + 1
```

### **Java**

```java
class Solution {
    public long minimumHealth(int[] damage, int armor) {
        long s = 0;
        int mx = damage[0];
        for (int v : damage) {
            s += v;
            mx = Math.max(mx, v);
        }
        return s - Math.min(mx, armor) + 1;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    long long minimumHealth(vector<int>& damage, int armor) {
        long long s = 0;
        int mx = damage[0];
        for (int& v : damage) {
            s += v;
            mx = max(mx, v);
        }
        return s - min(mx, armor) + 1;
    }
};
```

### **Go**

```go
func minimumHealth(damage []int, armor int) int64 {
	var s int64
	var mx int
	for _, v := range damage {
		s += int64(v)
		mx = max(mx, v)
	}
	return s - int64(min(mx, armor)) + 1
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
