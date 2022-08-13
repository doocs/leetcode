# [2279. Maximum Bags With Full Capacity of Rocks](https://leetcode.com/problems/maximum-bags-with-full-capacity-of-rocks)

[中文文档](/solution/2200-2299/2279.Maximum%20Bags%20With%20Full%20Capacity%20of%20Rocks/README.md)

## Description

<p>You have <code>n</code> bags numbered from <code>0</code> to <code>n - 1</code>. You are given two <strong>0-indexed</strong> integer arrays <code>capacity</code> and <code>rocks</code>. The <code>i<sup>th</sup></code> bag can hold a maximum of <code>capacity[i]</code> rocks and currently contains <code>rocks[i]</code> rocks. You are also given an integer <code>additionalRocks</code>, the number of additional rocks you can place in <strong>any</strong> of the bags.</p>

<p>Return<em> the <strong>maximum</strong> number of bags that could have full capacity after placing the additional rocks in some bags.</em></p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> capacity = [2,3,4,5], rocks = [1,2,4,4], additionalRocks = 2
<strong>Output:</strong> 3
<strong>Explanation:</strong>
Place 1 rock in bag 0 and 1 rock in bag 1.
The number of rocks in each bag are now [2,3,4,4].
Bags 0, 1, and 2 have full capacity.
There are 3 bags at full capacity, so we return 3.
It can be shown that it is not possible to have more than 3 bags at full capacity.
Note that there may be other ways of placing the rocks that result in an answer of 3.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> capacity = [10,2,2], rocks = [2,2,0], additionalRocks = 100
<strong>Output:</strong> 3
<strong>Explanation:</strong>
Place 8 rocks in bag 0 and 2 rocks in bag 2.
The number of rocks in each bag are now [10,2,2].
Bags 0, 1, and 2 have full capacity.
There are 3 bags at full capacity, so we return 3.
It can be shown that it is not possible to have more than 3 bags at full capacity.
Note that we did not use all of the additional rocks.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == capacity.length == rocks.length</code></li>
	<li><code>1 &lt;= n &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= capacity[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= rocks[i] &lt;= capacity[i]</code></li>
	<li><code>1 &lt;= additionalRocks &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maximumBags(
        self, capacity: List[int], rocks: List[int], additionalRocks: int
    ) -> int:
        d = [a - b for a, b in zip(capacity, rocks)]
        d.sort()
        ans = 0
        for v in d:
            if v <= additionalRocks:
                ans += 1
                additionalRocks -= v
        return ans
```

### **Java**

```java
class Solution {
    public int maximumBags(int[] capacity, int[] rocks, int additionalRocks) {
        int n = capacity.length;
        int[] d = new int[n];
        for (int i = 0; i < n; ++i) {
            d[i] = capacity[i] - rocks[i];
        }
        Arrays.sort(d);
        int ans = 0;
        for (int v : d) {
            if (v <= additionalRocks) {
                ++ans;
                additionalRocks -= v;
            } else {
                break;
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maximumBags(vector<int>& capacity, vector<int>& rocks, int additionalRocks) {
        int n = capacity.size();
        vector<int> d(n);
        for (int i = 0; i < n; ++i) d[i] = capacity[i] - rocks[i];
        sort(d.begin(), d.end());
        int ans = 0;
        for (int& v : d) {
            if (v > additionalRocks) break;
            ++ans;
            additionalRocks -= v;
        }
        return ans;
    }
};
```

### **Go**

```go
func maximumBags(capacity []int, rocks []int, additionalRocks int) int {
	n := len(capacity)
	d := make([]int, n)
	for i, v := range capacity {
		d[i] = v - rocks[i]
	}
	sort.Ints(d)
	ans := 0
	for _, v := range d {
		if v > additionalRocks {
			break
		}
		ans++
		additionalRocks -= v
	}
	return ans
}
```

### **TypeScript**

```ts
function maximumBags(
    capacity: number[],
    rocks: number[],
    additionalRocks: number,
): number {
    const n = capacity.length;
    const diffs = capacity.map((c, i) => c - rocks[i]);
    diffs.sort((a, b) => a - b);
    let ans = 0;
    for (
        let i = 0;
        i < n && (diffs[i] === 0 || diffs[i] <= additionalRocks);
        i++
    ) {
        ans++;
        additionalRocks -= diffs[i];
    }
    return ans;
}
```

### **Rust**

```rust
impl Solution {
    pub fn maximum_bags(capacity: Vec<i32>, rocks: Vec<i32>, mut additional_rocks: i32) -> i32 {
        let n = capacity.len();
        let mut diffs = vec![0; n];
        for i in 0..n {
            diffs[i] = capacity[i] - rocks[i];
        }
        diffs.sort();
        for i in 0..n {
            if diffs[i] > additional_rocks {
                return i as i32;
            }
            additional_rocks -= diffs[i];
        }
        n as i32
    }
}
```

### **...**

```

```

<!-- tabs:end -->
