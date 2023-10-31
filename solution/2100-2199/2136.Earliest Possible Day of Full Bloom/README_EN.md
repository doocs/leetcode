# [2136. Earliest Possible Day of Full Bloom](https://leetcode.com/problems/earliest-possible-day-of-full-bloom)

[中文文档](/solution/2100-2199/2136.Earliest%20Possible%20Day%20of%20Full%20Bloom/README.md)

## Description

<p>You have <code>n</code> flower seeds. Every seed must be planted first before it can begin to grow, then bloom. Planting a seed takes time and so does the growth of a seed. You are given two <strong>0-indexed</strong> integer arrays <code>plantTime</code> and <code>growTime</code>, of length <code>n</code> each:</p>

<ul>
	<li><code>plantTime[i]</code> is the number of <strong>full days</strong> it takes you to <strong>plant</strong> the <code>i<sup>th</sup></code> seed. Every day, you can work on planting exactly one seed. You <strong>do not</strong> have to work on planting the same seed on consecutive days, but the planting of a seed is not complete <strong>until</strong> you have worked <code>plantTime[i]</code> days on planting it in total.</li>
	<li><code>growTime[i]</code> is the number of <strong>full days</strong> it takes the <code>i<sup>th</sup></code> seed to grow after being completely planted. <strong>After</strong> the last day of its growth, the flower <strong>blooms</strong> and stays bloomed forever.</li>
</ul>

<p>From the beginning of day <code>0</code>, you can plant the seeds in <strong>any</strong> order.</p>

<p>Return <em>the <strong>earliest</strong> possible day where <strong>all</strong> seeds are blooming</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2136.Earliest%20Possible%20Day%20of%20Full%20Bloom/images/1.png" style="width: 453px; height: 149px;" />
<pre>
<strong>Input:</strong> plantTime = [1,4,3], growTime = [2,3,1]
<strong>Output:</strong> 9
<strong>Explanation:</strong> The grayed out pots represent planting days, colored pots represent growing days, and the flower represents the day it blooms.
One optimal way is:
On day 0, plant the 0<sup>th</sup> seed. The seed grows for 2 full days and blooms on day 3.
On days 1, 2, 3, and 4, plant the 1<sup>st</sup> seed. The seed grows for 3 full days and blooms on day 8.
On days 5, 6, and 7, plant the 2<sup>nd</sup> seed. The seed grows for 1 full day and blooms on day 9.
Thus, on day 9, all the seeds are blooming.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2136.Earliest%20Possible%20Day%20of%20Full%20Bloom/images/2.png" style="width: 454px; height: 184px;" />
<pre>
<strong>Input:</strong> plantTime = [1,2,3,2], growTime = [2,1,2,1]
<strong>Output:</strong> 9
<strong>Explanation:</strong> The grayed out pots represent planting days, colored pots represent growing days, and the flower represents the day it blooms.
One optimal way is:
On day 1, plant the 0<sup>th</sup> seed. The seed grows for 2 full days and blooms on day 4.
On days 0 and 3, plant the 1<sup>st</sup> seed. The seed grows for 1 full day and blooms on day 5.
On days 2, 4, and 5, plant the 2<sup>nd</sup> seed. The seed grows for 2 full days and blooms on day 8.
On days 6 and 7, plant the 3<sup>rd</sup> seed. The seed grows for 1 full day and blooms on day 9.
Thus, on day 9, all the seeds are blooming.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> plantTime = [1], growTime = [1]
<strong>Output:</strong> 2
<strong>Explanation:</strong> On day 0, plant the 0<sup>th</sup> seed. The seed grows for 1 full day and blooms on day 2.
Thus, on day 2, all the seeds are blooming.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == plantTime.length == growTime.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= plantTime[i], growTime[i] &lt;= 10<sup>4</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def earliestFullBloom(self, plantTime: List[int], growTime: List[int]) -> int:
        ans = t = 0
        for pt, gt in sorted(zip(plantTime, growTime), key=lambda x: -x[1]):
            t += pt
            ans = max(ans, t + gt)
        return ans
```

### **Java**

```java
class Solution {
    public int earliestFullBloom(int[] plantTime, int[] growTime) {
        int n = plantTime.length;
        Integer[] idx = new Integer[n];
        for (int i = 0; i < n; i++) {
            idx[i] = i;
        }
        Arrays.sort(idx, (i, j) -> growTime[j] - growTime[i]);
        int ans = 0, t = 0;
        for (int i : idx) {
            t += plantTime[i];
            ans = Math.max(ans, t + growTime[i]);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int earliestFullBloom(vector<int>& plantTime, vector<int>& growTime) {
        int n = plantTime.size();
        vector<int> idx(n);
        iota(idx.begin(), idx.end(), 0);
        sort(idx.begin(), idx.end(), [&](int i, int j) { return growTime[j] < growTime[i]; });
        int ans = 0, t = 0;
        for (int i : idx) {
            t += plantTime[i];
            ans = max(ans, t + growTime[i]);
        }
        return ans;
    }
};
```

### **Go**

```go
func earliestFullBloom(plantTime []int, growTime []int) (ans int) {
	n := len(plantTime)
	idx := make([]int, n)
	for i := range idx {
		idx[i] = i
	}
	sort.Slice(idx, func(i, j int) bool { return growTime[idx[j]] < growTime[idx[i]] })
	t := 0
	for _, i := range idx {
		t += plantTime[i]
		ans = max(ans, t+growTime[i])
	}
	return
}
```

### **TypeScript**

```ts
function earliestFullBloom(plantTime: number[], growTime: number[]): number {
    const n = plantTime.length;
    const idx: number[] = Array.from({ length: n }, (_, i) => i);
    idx.sort((i, j) => growTime[j] - growTime[i]);
    let [ans, t] = [0, 0];
    for (const i of idx) {
        t += plantTime[i];
        ans = Math.max(ans, t + growTime[i]);
    }
    return ans;
}
```

### **Rust**

```rust
impl Solution {
    pub fn earliest_full_bloom(plant_time: Vec<i32>, grow_time: Vec<i32>) -> i32 {
        let mut idx: Vec<usize> = (0..plant_time.len()).collect();
        idx.sort_by_key(|&i| -&grow_time[i]);
        let mut ans = 0;
        let mut t = 0;
        for &i in &idx {
            t += plant_time[i];
            ans = ans.max(t + grow_time[i]);
        }
        ans
    }
}
```

### **...**

```

```

<!-- tabs:end -->
