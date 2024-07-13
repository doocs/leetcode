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

<!-- problem:start -->

# [853. Car Fleet](https://leetcode.com/problems/car-fleet)

[中文文档](/solution/0800-0899/0853.Car%20Fleet/README.md)

## Description

<!-- description:start -->

<p>There are <code>n</code> cars at given miles away from the starting mile 0, traveling to reach the mile <code>target</code>.</p>

<p>You are given two integer array <code>position</code> and <code>speed</code>, both of length <code>n</code>, where <code>position[i]</code> is the starting mile of the <code>i<sup>th</sup></code> car and <code>speed[i]</code> is the speed of the <code>i<sup>th</sup></code> car in miles per hour.</p>

<p>A car cannot pass another car, but it can catch up and then travel next to it at the speed of the slower car.</p>

<p>A <strong>car fleet</strong> is a car or cars driving next to each other. The speed of the car fleet is the <strong>minimum</strong> speed of any car in the fleet.</p>

<p>If a car catches up to a car fleet at the mile <code>target</code>, it will still be considered as part of the car fleet.</p>

<p>Return the number of car fleets that will arrive at the destination.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">target = 12, position = [10,8,0,5,3], speed = [2,4,1,1,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The cars starting at 10 (speed 2) and 8 (speed 4) become a fleet, meeting each other at 12. The fleet forms at <code>target</code>.</li>
	<li>The car starting at 0 (speed 1) does not catch up to any other car, so it is a fleet by itself.</li>
	<li>The cars starting at 5 (speed 1) and 3 (speed 3) become a fleet, meeting each other at 6. The fleet moves at speed 1 until it reaches <code>target</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">target = 10, position = [3], speed = [3]</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>
There is only one car, hence there is only one fleet.</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">target = 100, position = [0,2,4], speed = [4,2,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The cars starting at 0 (speed 4) and 2 (speed 2) become a fleet, meeting each other at 4. The car starting at 4 (speed 1) travels to 5.</li>
	<li>Then, the fleet at 4 (speed 2) and the car at position 5 (speed 1) become one fleet, meeting each other at 6. The fleet moves at speed 1 until it reaches <code>target</code>.</li>
</ul>
</div>

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

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- tabs:start -->

#### Python3

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

#### Java

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

#### C++

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

#### Go

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

#### TypeScript

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

<!-- solution:end -->

<!-- problem:end -->
