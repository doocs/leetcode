# [1921. Eliminate Maximum Number of Monsters](https://leetcode.com/problems/eliminate-maximum-number-of-monsters)

[中文文档](/solution/1900-1999/1921.Eliminate%20Maximum%20Number%20of%20Monsters/README.md)

## Description

<p>You are playing a video game where you are defending your city from a group of <code>n</code> monsters. You are given a <strong>0-indexed</strong> integer array <code>dist</code> of size <code>n</code>, where <code>dist[i]</code> is the <strong>initial distance</strong> in meters of the <code>i<sup>th</sup></code> monster from the city.</p>

<p>The monsters walk toward the city at a <strong>constant</strong> speed. The speed of each monster is given to you in an integer array <code>speed</code> of size <code>n</code>, where <code>speed[i]</code> is the speed of the <code>i<sup>th</sup></code> monster in meters per minute.</p>

<p>The monsters start moving at <strong>minute 0</strong>. You have a weapon that you can <strong>choose</strong> to use at the start of every minute, including minute 0. You cannot use the weapon in the middle of a minute. The weapon can eliminate any monster that is still alive. You lose when any monster reaches your city. If a monster reaches the city <strong>exactly</strong> at the start of a minute, it counts as a <strong>loss</strong>, and the game ends before you can use your weapon in that minute.</p>

<p>Return <em>the <strong>maximum</strong> number of monsters that you can eliminate before you lose, or </em><code>n</code><em> if you can eliminate all the monsters before they reach the city.</em></p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> dist = [1,3,4], speed = [1,1,1]
<strong>Output:</strong> 3
<strong>Explanation:</strong>
At the start of minute 0, the distances of the monsters are [1,3,4], you eliminate the first monster.
At the start of minute 1, the distances of the monsters are [X,2,3], you don&#39;t do anything.
At the start of minute 2, the distances of the monsters are [X,1,2], you eliminate the second monster.
At the start of minute 3, the distances of the monsters are [X,X,1], you eliminate the third monster.
All 3 monsters can be eliminated.</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> dist = [1,1,2,3], speed = [1,1,1,1]
<strong>Output:</strong> 1
<strong>Explanation:</strong>
At the start of minute 0, the distances of the monsters are [1,1,2,3], you eliminate the first monster.
At the start of minute 1, the distances of the monsters are [X,0,1,2], so you lose.
You can only eliminate 1 monster.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> dist = [3,2,4], speed = [5,3,2]
<strong>Output:</strong> 1
<strong>Explanation:</strong>
At the start of minute 0, the distances of the monsters are [3,2,4], you eliminate the first monster.
At the start of minute 1, the distances of the monsters are [X,0,2], so you lose.
You can only eliminate 1 monster.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == dist.length == speed.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= dist[i], speed[i] &lt;= 10<sup>5</sup></code></li>
</ul>


## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def eliminateMaximum(self, dist: List[int], speed: List[int]) -> int:
        n = len(dist)
        times = [(dist[i] - 1) // speed[i] for i in range(n)]
        times.sort()
        for i in range(n):
            if times[i] < i:
                return i
        return n
```

### **Java**

```java
class Solution {
    public int eliminateMaximum(int[] dist, int[] speed) {
        int n = dist.length;
        int[] times = new int[n];
        for (int i = 0; i < n; ++i) {
            times[i] = (dist[i] - 1) / speed[i];
        }
        Arrays.sort(times);
        for (int i = 0; i < n; ++i) {
            if (times[i] < i) {
                return i;
            }
        }
        return n;
    }
}
```

### **JavaScript**

```js
/**
 * @param {number[]} dist
 * @param {number[]} speed
 * @return {number}
 */
 var eliminateMaximum = function(dist, speed) {
    let arr = [];
    for (let i = 0; i < dist.length; i++) {
        arr[i] = dist[i] / speed[i];
    }
    arr.sort((a, b) => a - b);
    let ans = 0;
    while (arr[0] > ans) {
        arr.shift();
        ++ans;
    }
    return ans;
};
```

### **C++**

```cpp
class Solution {
public:
    int eliminateMaximum(vector<int>& dist, vector<int>& speed) {
        int n = dist.size();
        vector<int> times;
        for (int i = 0; i < n; ++i) {
            times.push_back((dist[i] - 1) / speed[i]);
        }
        sort(times.begin(), times.end());
        for (int i = 0; i < n; ++i) {
            if (times[i] < i) {
                return i;
            }
        }
        return n;
    }
};
```

### **Go**

```go
func eliminateMaximum(dist []int, speed []int) int {
	n := len(dist)
	times := make([]int, n)
	for i := 0; i < n; i++ {
		times[i] = (dist[i] - 1) / speed[i]
	}
	sort.Ints(times)
	for i := 0; i < n; i++ {
		if times[i] < i {
			return i
		}
	}
	return n
}
```

### **...**

```

```

<!-- tabs:end -->
