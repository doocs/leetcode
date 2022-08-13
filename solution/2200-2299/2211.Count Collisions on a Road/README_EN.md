# [2211. Count Collisions on a Road](https://leetcode.com/problems/count-collisions-on-a-road)

[中文文档](/solution/2200-2299/2211.Count%20Collisions%20on%20a%20Road/README.md)

## Description

<p>There are <code>n</code> cars on an infinitely long road. The cars are numbered from <code>0</code> to <code>n - 1</code> from left to right and each car is present at a <strong>unique</strong> point.</p>

<p>You are given a <strong>0-indexed</strong> string <code>directions</code> of length <code>n</code>. <code>directions[i]</code> can be either <code>&#39;L&#39;</code>, <code>&#39;R&#39;</code>, or <code>&#39;S&#39;</code> denoting whether the <code>i<sup>th</sup></code> car is moving towards the <strong>left</strong>, towards the <strong>right</strong>, or <strong>staying</strong> at its current point respectively. Each moving car has the <strong>same speed</strong>.</p>

<p>The number of collisions can be calculated as follows:</p>

<ul>
	<li>When two cars moving in <strong>opposite</strong> directions collide with each other, the number of collisions increases by <code>2</code>.</li>
	<li>When a moving car collides with a stationary car, the number of collisions increases by <code>1</code>.</li>
</ul>

<p>After a collision, the cars involved can no longer move and will stay at the point where they collided. Other than that, cars cannot change their state or direction of motion.</p>

<p>Return <em>the <strong>total number of collisions</strong> that will happen on the road</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> directions = &quot;RLRSLL&quot;
<strong>Output:</strong> 5
<strong>Explanation:</strong>
The collisions that will happen on the road are:
- Cars 0 and 1 will collide with each other. Since they are moving in opposite directions, the number of collisions becomes 0 + 2 = 2.
- Cars 2 and 3 will collide with each other. Since car 3 is stationary, the number of collisions becomes 2 + 1 = 3.
- Cars 3 and 4 will collide with each other. Since car 3 is stationary, the number of collisions becomes 3 + 1 = 4.
- Cars 4 and 5 will collide with each other. After car 4 collides with car 3, it will stay at the point of collision and get hit by car 5. The number of collisions becomes 4 + 1 = 5.
Thus, the total number of collisions that will happen on the road is 5. 
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> directions = &quot;LLRR&quot;
<strong>Output:</strong> 0
<strong>Explanation:</strong>
No cars will collide with each other. Thus, the total number of collisions that will happen on the road is 0.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= directions.length &lt;= 10<sup>5</sup></code></li>
	<li><code>directions[i]</code> is either <code>&#39;L&#39;</code>, <code>&#39;R&#39;</code>, or <code>&#39;S&#39;</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def countCollisions(self, directions: str) -> int:
        d = directions.lstrip('L').rstrip('R')
        return len(d) - d.count('S')
```

### **Java**

```java
class Solution {
    public int countCollisions(String directions) {
        char[] ds = directions.toCharArray();
        int n = ds.length;
        int l = 0;
        int r = n - 1;
        while (l < n && ds[l] == 'L') {
            ++l;
        }
        while (r >= 0 && ds[r] == 'R') {
            --r;
        }
        int ans = 0;
        for (int i = l; i <= r; ++i) {
            if (ds[i] != 'S') {
                ++ans;
            }
        }
        return ans;
    }
}
```

### **TypeScript**

```ts
function countCollisions(directions: string): number {
    const n = directions.length;
    let l = 0,
        r = n - 1;
    while (l < n && directions[l] == 'L') {
        ++l;
    }
    while (r >= 0 && directions[r] == 'R') {
        --r;
    }
    let ans = 0;
    for (let i = l; i <= r; ++i) {
        if (directions[i] != 'S') {
            ++ans;
        }
    }
    return ans;
}
```

### **C++**

```cpp
class Solution {
public:
    int countCollisions(string directions) {
        int l = 0, r = directions.size() - 1, count = 0;
        while (l <= r && directions[l] == 'L') {
            l++;
        }
        while (l <= r && directions[r] == 'R') {
            r--;
        }
        for (int i = l; i <= r; i++) {
            count += directions[i] != 'S';
        }
        return count;
    }
};
```

### **Go**

```go
func countCollisions(directions string) int {
	d := strings.TrimLeft(directions, "L")
	d = strings.TrimRight(d, "R")
	return len(d) - strings.Count(d, "S")
}
```

### **...**

```

```

<!-- tabs:end -->
