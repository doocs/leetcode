# [1552. Magnetic Force Between Two Balls](https://leetcode.com/problems/magnetic-force-between-two-balls)

[中文文档](/solution/1500-1599/1552.Magnetic%20Force%20Between%20Two%20Balls/README.md)

## Description

<p>In the universe Earth C-137, Rick discovered a special form of magnetic force between two balls if they are put in his new invented basket. Rick has <code>n</code> empty baskets, the <code>i<sup>th</sup></code> basket is at <code>position[i]</code>, Morty has <code>m</code> balls and needs to distribute the balls into the baskets such that the <strong>minimum magnetic force</strong> between any two balls is <strong>maximum</strong>.</p>

<p>Rick stated that magnetic force between two different balls at positions <code>x</code> and <code>y</code> is <code>|x - y|</code>.</p>

<p>Given the integer array <code>position</code> and the integer <code>m</code>. Return <em>the required force</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1500-1599/1552.Magnetic%20Force%20Between%20Two%20Balls/images/q3v1.jpg" style="width: 562px; height: 195px;" />
<pre>
<strong>Input:</strong> position = [1,2,3,4,7], m = 3
<strong>Output:</strong> 3
<strong>Explanation:</strong> Distributing the 3 balls into baskets 1, 4 and 7 will make the magnetic force between ball pairs [3, 3, 6]. The minimum magnetic force is 3. We cannot achieve a larger minimum magnetic force than 3.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> position = [5,4,3,2,1,1000000000], m = 2
<strong>Output:</strong> 999999999
<strong>Explanation:</strong> We can use baskets 1 and 1000000000.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == position.length</code></li>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= position[i] &lt;= 10<sup>9</sup></code></li>
	<li>All integers in <code>position</code> are <strong>distinct</strong>.</li>
	<li><code>2 &lt;= m &lt;= position.length</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maxDistance(self, position: List[int], m: int) -> int:
        def check(f):
            prev = position[0]
            cnt = 1
            for curr in position[1:]:
                if curr - prev >= f:
                    prev = curr
                    cnt += 1
            return cnt >= m

        position.sort()
        left, right = 1, position[-1]
        while left < right:
            mid = (left + right + 1) >> 1

            if check(mid):
                left = mid
            else:
                right = mid - 1
        return left
```

### **Java**

```java
class Solution {
    public int maxDistance(int[] position, int m) {
        Arrays.sort(position);
        int left = 1, right = position[position.length - 1];
        while (left < right) {
            int mid = (left + right + 1) >>> 1;
            if (check(position, mid, m)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    private boolean check(int[] position, int f, int m) {
        int prev = position[0];
        int cnt = 1;
        for (int i = 1; i < position.length; ++i) {
            int curr = position[i];
            if (curr - prev >= f) {
                prev = curr;
                ++cnt;
            }
        }
        return cnt >= m;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maxDistance(vector<int>& position, int m) {
        sort(position.begin(), position.end());
        int left = 1, right = position[position.size() - 1];
        while (left < right) {
            int mid = (left + right + 1) >> 1;
            if (check(position, mid, m))
                left = mid;
            else
                right = mid - 1;
        }
        return left;
    }

    bool check(vector<int>& position, int f, int m) {
        int prev = position[0];
        int cnt = 1;
        for (int i = 1; i < position.size(); ++i) {
            int curr = position[i];
            if (curr - prev >= f) {
                prev = curr;
                ++cnt;
            }
        }
        return cnt >= m;
    }
};
```

### **Go**

```go
func maxDistance(position []int, m int) int {
	sort.Ints(position)
	left, right := 1, position[len(position)-1]
	check := func(f int) bool {
		prev, cnt := position[0], 1
		for _, curr := range position[1:] {
			if curr-prev >= f {
				prev = curr
				cnt++
			}
		}
		return cnt >= m
	}
	for left < right {
		mid := (left + right + 1) >> 1
		if check(mid) {
			left = mid
		} else {
			right = mid - 1
		}
	}
	return left
}
```

### **JavaScript**

```js
/**
 * @param {number[]} position
 * @param {number} m
 * @return {number}
 */
var maxDistance = function (position, m) {
    position.sort((a, b) => {
        return a - b;
    });
    let left = 1,
        right = position[position.length - 1];
    const check = function (f) {
        let prev = position[0];
        let cnt = 1;
        for (let i = 1; i < position.length; ++i) {
            const curr = position[i];
            if (curr - prev >= f) {
                prev = curr;
                ++cnt;
            }
        }
        return cnt >= m;
    };
    while (left < right) {
        const mid = (left + right + 1) >> 1;
        if (check(mid)) {
            left = mid;
        } else {
            right = mid - 1;
        }
    }
    return left;
};
```

### **...**

```

```

<!-- tabs:end -->
