# [735. Asteroid Collision](https://leetcode.com/problems/asteroid-collision)

[中文文档](/solution/0700-0799/0735.Asteroid%20Collision/README.md)

## Description

<p>We are given an array <code>asteroids</code> of integers representing asteroids in a row.</p>

<p>For each asteroid, the absolute value represents its size, and the sign represents its direction (positive meaning right, negative meaning left). Each asteroid moves at the same speed.</p>

<p>Find out the state of the asteroids after all collisions. If two asteroids meet, the smaller one will explode. If both are the same size, both will explode. Two asteroids moving in the same direction will never meet.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> asteroids = [5,10,-5]
<strong>Output:</strong> [5,10]
<strong>Explanation:</strong> The 10 and -5 collide resulting in 10. The 5 and 10 never collide.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> asteroids = [8,-8]
<strong>Output:</strong> []
<strong>Explanation:</strong> The 8 and -8 collide exploding each other.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> asteroids = [10,2,-5]
<strong>Output:</strong> [10]
<strong>Explanation:</strong> The 2 and -5 collide resulting in -5. The 10 and -5 collide resulting in 10.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= asteroids.length &lt;= 10<sup>4</sup></code></li>
	<li><code>-1000 &lt;= asteroids[i] &lt;= 1000</code></li>
	<li><code>asteroids[i] != 0</code></li>
</ul>

## Solutions

this can be analogous to matching parentheses:

-   right-moving asteroid (left parenthesis): will not cause a collision, will be pushed directly
-   left-moving asteroid (right parenthesis): may collision with the right-moving asteroid, special treatment

because the answer requires all the asteroids left after the collision, which is element left in the stack, you can simply represent the stack in an array

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def asteroidCollision(self, asteroids: List[int]) -> List[int]:
        ans = []
        for a in asteroids:
            if a > 0:
                ans.append(a)
            else:
                while ans and 0 < ans[-1] < -a:
                    ans.pop()
                if ans and ans[-1] == -a:
                    ans.pop()
                elif not ans or ans[-1] < -a:
                    ans.append(a)
        return ans
```

### **Java**

```java
class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        Deque<Integer> d = new ArrayDeque<>();
        for (int a : asteroids) {
            if (a > 0) {
                d.offerLast(a);
            } else {
                while (!d.isEmpty() && d.peekLast() > 0 && d.peekLast() < -a) {
                    d.pollLast();
                }
                if (!d.isEmpty() && d.peekLast() == -a) {
                    d.pollLast();
                } else if (d.isEmpty() || d.peekLast() < -a) {
                    d.offerLast(a);
                }
            }
        }
        return d.stream().mapToInt(Integer::valueOf).toArray();
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> asteroidCollision(vector<int>& asteroids) {
        vector<int> ans;
        for (int a : asteroids) {
            if (a > 0) {
                ans.push_back(a);
            } else {
                while (!ans.empty() && ans.back() > 0 && ans.back() < -a) {
                    ans.pop_back();
                }
                if (!ans.empty() && ans.back() == -a) {
                    ans.pop_back();
                } else if (ans.empty() || ans.back() < -a) {
                    ans.push_back(a);
                }
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func asteroidCollision(asteroids []int) []int {
	var ans []int
	for _, a := range asteroids {
		if a > 0 {
			ans = append(ans, a)
		} else {
			for len(ans) > 0 && ans[len(ans)-1] > 0 && ans[len(ans)-1] < -a {
				ans = ans[:len(ans)-1]
			}
			if len(ans) > 0 && ans[len(ans)-1] == -a {
				ans = ans[:len(ans)-1]
			} else if len(ans) == 0 || ans[len(ans)-1] < -a {
				ans = append(ans, a)
			}
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
