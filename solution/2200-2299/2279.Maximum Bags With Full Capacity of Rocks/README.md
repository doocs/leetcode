# [2279. 装满石头的背包的最大数量](https://leetcode.cn/problems/maximum-bags-with-full-capacity-of-rocks)

[English Version](/solution/2200-2299/2279.Maximum%20Bags%20With%20Full%20Capacity%20of%20Rocks/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>现有编号从&nbsp;<code>0</code> 到 <code>n - 1</code> 的 <code>n</code> 个背包。给你两个下标从 <strong>0</strong> 开始的整数数组 <code>capacity</code> 和 <code>rocks</code> 。第 <code>i</code> 个背包最大可以装 <code>capacity[i]</code> 块石头，当前已经装了 <code>rocks[i]</code> 块石头。另给你一个整数 <code>additionalRocks</code> ，表示<span class="text-only" data-eleid="10" style="white-space: pre;">你可以放置的额外石头数量，石头可以往 </span><strong><span class="text-only" data-eleid="11" style="white-space: pre;">任意</span></strong><span class="text-only" data-eleid="12" style="white-space: pre;"> 背包中放置。</span></p>

<p>请你将额外的石头放入一些背包中，并返回放置后装满石头的背包的 <strong>最大 </strong>数量<em>。</em></p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>capacity = [2,3,4,5], rocks = [1,2,4,4], additionalRocks = 2
<strong>输出：</strong>3
<strong>解释：</strong>
1 块石头放入背包 0 ，1 块石头放入背包 1 。
每个背包中的石头总数是 [2,3,4,4] 。
背包 0 、背包 1 和 背包 2 都装满石头。
总计 3 个背包装满石头，所以返回 3 。
可以证明不存在超过 3 个背包装满石头的情况。
注意，可能存在其他放置石头的方案同样能够得到 3 这个结果。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>capacity = [10,2,2], rocks = [2,2,0], additionalRocks = 100
<strong>输出：</strong>3
<strong>解释：</strong>
8 块石头放入背包 0 ，2 块石头放入背包 2 。
每个背包中的石头总数是 [10,2,2] 。
背包 0 、背包 1 和背包 2 都装满石头。
总计 3 个背包装满石头，所以返回 3 。
可以证明不存在超过 3 个背包装满石头的情况。
注意，不必用完所有的额外石头。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == capacity.length == rocks.length</code></li>
	<li><code>1 &lt;= n &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= capacity[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= rocks[i] &lt;= capacity[i]</code></li>
	<li><code>1 &lt;= additionalRocks &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：排序 + 贪心**

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
