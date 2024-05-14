---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0800-0899/0853.Car%20Fleet/README_EN.md
tags:
    - Stack
    - Array
    - Sorting
    - Monotonic Stack
---

# [853. Car Fleet](https://leetcode.com/problems/car-fleet)

[中文文档](/solution/0800-0899/0853.Car%20Fleet/README.md)

## Description

<p>There are <code>n</code> cars going to the same destination along a one-lane road. The destination is <code>target</code> miles away.</p>

<p>You are given two integer array <code>position</code> and <code>speed</code>, both of length <code>n</code>, where <code>position[i]</code> is the position of the <code>i<sup>th</sup></code> car and <code>speed[i]</code> is the speed of the <code>i<sup>th</sup></code> car (in miles per hour).</p>

<p>A car can never pass another car ahead of it, but it can catch up to it&nbsp;and drive bumper to bumper <strong>at the same speed</strong>. The faster car will <strong>slow down</strong> to match the slower car&#39;s speed. The distance between these two cars is ignored (i.e., they are assumed to have the same position).</p>

<p>A <strong>car fleet</strong> is some non-empty set of cars driving at the same position and same speed. Note that a single car is also a car fleet.</p>

<p>If a car catches up to a car fleet right at the destination point, it will still be considered as one car fleet.</p>

<p>Return <em>the <strong>number of car fleets</strong> that will arrive at the destination</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> target = 12, position = [10,8,0,5,3], speed = [2,4,1,1,3]
<strong>Output:</strong> 3
<strong>Explanation:</strong>
The cars starting at 10 (speed 2) and 8 (speed 4) become a fleet, meeting each other at 12.
The car starting at 0 does not catch up to any other car, so it is a fleet by itself.
The cars starting at 5 (speed 1) and 3 (speed 3) become a fleet, meeting each other at 6. The fleet moves at speed 1 until it reaches target.
Note that no other cars meet these fleets before the destination, so the answer is 3.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> target = 10, position = [3], speed = [3]
<strong>Output:</strong> 1
<strong>Explanation:</strong> There is only one car, hence there is only one fleet.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> target = 100, position = [0,2,4], speed = [4,2,1]
<strong>Output:</strong> 1
<strong>Explanation:</strong>
The cars starting at 0 (speed 4) and 2 (speed 2) become a fleet, meeting each other at 4. The fleet moves at speed 2.
Then, the fleet (speed 2) and the car starting at 4 (speed 1) become one fleet, meeting each other at 6. The fleet moves at speed 1 until it reaches target.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == position.length == speed.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt; target &lt;= 10<sup>6</sup></code></li>
	<li><code>0 &lt;= position[i] &lt; target</code></li>
	<li>All the values of <code>position</code> are <strong>unique</strong>.</li>
	<li><code>0 &lt; speed[i] &lt;= 10<sup>6</sup></code></li>
</ul>

## Solutions

### Solution 1

<!-- tabs:start -->

```python
class Solution:
    def carFleet(self, target: int, position: List[int], speed: List[int]) -> int:
        idx = sorted(range(len(position)), key=lambda i: position[i])
        ans = pre = 0
        for i in idx[::-1]:
            t = (target - position[i]) / speed[i]
            if t > pre:
                ans += 1
                pre = t
        return ans
```

```java
class Solution {
    public int carFleet(int target, int[] position, int[] speed) {
        int n = position.length;
        Integer[] idx = new Integer[n];
        for (int i = 0; i < n; ++i) {
            idx[i] = i;
        }
        Arrays.sort(idx, (i, j) -> position[j] - position[i]);
        int ans = 0;
        double pre = 0;
        for (int i : idx) {
            double t = 1.0 * (target - position[i]) / speed[i];
            if (t > pre) {
                ++ans;
                pre = t;
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int carFleet(int target, vector<int>& position, vector<int>& speed) {
        int n = position.size();
        vector<int> idx(n);
        iota(idx.begin(), idx.end(), 0);
        sort(idx.begin(), idx.end(), [&](int i, int j) {
            return position[i] > position[j];
        });
        int ans = 0;
        double pre = 0;
        for (int i : idx) {
            double t = 1.0 * (target - position[i]) / speed[i];
            if (t > pre) {
                ++ans;
                pre = t;
            }
        }
        return ans;
    }
};
```

```go
func carFleet(target int, position []int, speed []int) (ans int) {
	n := len(position)
	idx := make([]int, n)
	for i := range idx {
		idx[i] = i
	}
	sort.Slice(idx, func(i, j int) bool { return position[idx[j]] < position[idx[i]] })
	var pre float64
	for _, i := range idx {
		t := float64(target-position[i]) / float64(speed[i])
		if t > pre {
			ans++
			pre = t
		}
	}
	return
}
```

```ts
function carFleet(target: number, position: number[], speed: number[]): number {
    const n = position.length;
    const idx = Array(n)
        .fill(0)
        .map((_, i) => i)
        .sort((i, j) => position[j] - position[i]);
    let ans = 0;
    let pre = 0;
    for (const i of idx) {
        const t = (target - position[i]) / speed[i];
        if (t > pre) {
            ++ans;
            pre = t;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
