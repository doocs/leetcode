# [2594. Minimum Time to Repair Cars](https://leetcode.com/problems/minimum-time-to-repair-cars)

[中文文档](/solution/2500-2599/2594.Minimum%20Time%20to%20Repair%20Cars/README.md)

## Description

<p>You are given an integer array <code>ranks</code> representing the <strong>ranks</strong> of some mechanics. <font face="monospace">ranks<sub>i</sub></font> is the rank of the <font face="monospace">i<sup>th</sup></font> mechanic<font face="monospace">.</font> A mechanic with a rank <code>r</code> can repair <font face="monospace">n</font> cars in <code>r * n<sup>2</sup></code> minutes.</p>

<p>You are also given an integer <code>cars</code> representing the total number of cars waiting in the garage to be repaired.</p>

<p>Return <em>the <strong>minimum</strong> time taken to repair all the cars.</em></p>

<p><strong>Note:</strong> All the mechanics can repair the cars simultaneously.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> ranks = [4,2,3,1], cars = 10
<strong>Output:</strong> 16
<strong>Explanation:</strong> 
- The first mechanic will repair two cars. The time required is 4 * 2 * 2 = 16 minutes.
- The second mechanic will repair two cars. The time required is 2 * 2 * 2 = 8 minutes.
- The third mechanic will repair two cars. The time required is 3 * 2 * 2 = 12 minutes.
- The fourth mechanic will repair four cars. The time required is 1 * 4 * 4 = 16 minutes.
It can be proved that the cars cannot be repaired in less than 16 minutes.​​​​​
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> ranks = [5,1,8], cars = 6
<strong>Output:</strong> 16
<strong>Explanation:</strong> 
- The first mechanic will repair one car. The time required is 5 * 1 * 1 = 5 minutes.
- The second mechanic will repair four cars. The time required is 1 * 4 * 4 = 16 minutes.
- The third mechanic will repair one car. The time required is 8 * 1 * 1 = 8 minutes.
It can be proved that the cars cannot be repaired in less than 16 minutes.​​​​​
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= ranks.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= ranks[i] &lt;= 100</code></li>
	<li><code>1 &lt;= cars &lt;= 10<sup>6</sup></code></li>
</ul>

## Solutions

**Solution 1: Binary Search**

We notice that the longer the repair time, the more repaired cars. Therefore, we can use binary search to find the minimum repair time.

We define the left and right boundaries of binary search as $left=0$, $right=ranks[0] \times cars \times cars$. Next, we enumerate the repair time $mid$ in binary search. The number of cars that each mechanic can repair is $\lfloor \sqrt{\frac{mid}{r}} \rfloor$, where $\lfloor x \rfloor$ represents the floor function. If the number of cars repaired is greater than or equal to $cars$, then the repair time $mid$ is feasible, and we shrink the right boundary to $mid$, otherwise we increase the left boundary to $mid+1$.

Finally, we return the left boundary.

Time complexity $(n \times \log n)$, space complexity $O(1)$. Where $n$ is the number of mechanics.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def repairCars(self, ranks: List[int], cars: int) -> int:
        def check(t):
            return sum(int(sqrt(t // r)) for r in ranks) >= cars

        return bisect_left(range(ranks[0] * cars * cars), True, key=check)
```

### **Java**

```java
class Solution {
    public long repairCars(int[] ranks, int cars) {
        long left = 0, right = 1L * ranks[0] * cars * cars;
        while (left < right) {
            long mid = (left + right) >> 1;
            long cnt = 0;
            for (int r : ranks) {
                cnt += Math.sqrt(mid / r);
            }
            if (cnt >= cars) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    long long repairCars(vector<int>& ranks, int cars) {
        long long left = 0, right = 1LL * ranks[0] * cars * cars;
        while (left < right) {
            long long mid = (left + right) >> 1;
            long long cnt = 0;
            for (int r : ranks) {
                cnt += sqrt(mid / r);
            }
            if (cnt >= cars) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
};
```

### **Go**

```go
func repairCars(ranks []int, cars int) int64 {
	return int64(sort.Search(ranks[0]*cars*cars, func(t int) bool {
		cnt := 0
		for _, r := range ranks {
			cnt += int(math.Sqrt(float64(t / r)))
		}
		return cnt >= cars
	}))
}
```

### **TypeScript**

```ts
function repairCars(ranks: number[], cars: number): number {
    let left = 0;
    let right = ranks[0] * cars * cars;
    while (left < right) {
        const mid = left + Math.floor((right - left) / 2);
        let cnt = 0;
        for (const r of ranks) {
            cnt += Math.floor(Math.sqrt(mid / r));
        }
        if (cnt >= cars) {
            right = mid;
        } else {
            left = mid + 1;
        }
    }
    return left;
}
```

### **...**

```

```

<!-- tabs:end -->
