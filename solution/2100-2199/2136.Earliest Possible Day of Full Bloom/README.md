# [2136. 全部开花的最早一天](https://leetcode.cn/problems/earliest-possible-day-of-full-bloom)

[English Version](/solution/2100-2199/2136.Earliest%20Possible%20Day%20of%20Full%20Bloom/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>你有 <code>n</code> 枚花的种子。每枚种子必须先种下，才能开始生长、开花。播种需要时间，种子的生长也是如此。给你两个下标从 <strong>0</strong> 开始的整数数组 <code>plantTime</code> 和 <code>growTime</code> ，每个数组的长度都是 <code>n</code> ：</p>

<ul>
	<li><code>plantTime[i]</code> 是 <strong>播种</strong> 第 <code>i</code> 枚种子所需的 <strong>完整天数</strong> 。每天，你只能为播种某一枚种子而劳作。<strong>无须</strong> 连续几天都在种同一枚种子，但是种子播种必须在你工作的天数达到 <code>plantTime[i]</code> 之后才算完成。</li>
	<li><code>growTime[i]</code> 是第 <code>i</code> 枚种子完全种下后生长所需的 <strong>完整天数 </strong>。在它生长的最后一天 <strong>之后</strong> ，将会开花并且永远 <strong>绽放</strong> 。</li>
</ul>

<p>从第 <code>0</code> 开始，你可以按 <strong>任意</strong> 顺序播种种子。</p>

<p>返回所有种子都开花的 <strong>最早</strong> 一天是第几天。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2136.Earliest%20Possible%20Day%20of%20Full%20Bloom/images/1.png" style="width: 453px; height: 149px;">
<pre><strong>输入：</strong>plantTime = [1,4,3], growTime = [2,3,1]
<strong>输出：</strong>9
<strong>解释：</strong>灰色的花盆表示播种的日子，彩色的花盆表示生长的日子，花朵表示开花的日子。
一种最优方案是：
第 0 天，播种第 0 枚种子，种子生长 2 整天。并在第 3 天开花。
第 1、2、3、4 天，播种第 1 枚种子。种子生长 3 整天，并在第 8 天开花。
第 5、6、7 天，播种第 2 枚种子。种子生长 1 整天，并在第 9 天开花。
因此，在第 9 天，所有种子都开花。 
</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2100-2199/2136.Earliest%20Possible%20Day%20of%20Full%20Bloom/images/2.png" style="width: 454px; height: 184px;">
<pre><strong>输入：</strong>plantTime = [1,2,3,2], growTime = [2,1,2,1]
<strong>输出：</strong>9
<strong>解释：</strong>灰色的花盆表示播种的日子，彩色的花盆表示生长的日子，花朵表示开花的日子。 
一种最优方案是：
第 1 天，播种第 0 枚种子，种子生长 2 整天。并在第 4 天开花。
第 0、3 天，播种第 1 枚种子。种子生长 1 整天，并在第 5 天开花。
第 2、4、5 天，播种第 2 枚种子。种子生长 2 整天，并在第 8 天开花。
第 6、7 天，播种第 3 枚种子。种子生长 1 整天，并在第 9 天开花。
因此，在第 9 天，所有种子都开花。 
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>plantTime = [1], growTime = [1]
<strong>输出：</strong>2
<strong>解释：</strong>第 0 天，播种第 0 枚种子。种子需要生长 1 整天，然后在第 2 天开花。
因此，在第 2 天，所有种子都开花。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == plantTime.length == growTime.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= plantTime[i], growTime[i] &lt;= 10<sup>4</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：贪心 + 排序**

根据题目描述，我们知道，每一天只能为一枚种子进行播种，因此不管什么播种顺序，所有种子的播种时间之和总是等于 $\sum_{i=0}^{n-1} plantTime[i]$。那么，为了让尽快让所有种子开花，我们应该尽快播种生长时间最长的种子。因此，我们可以对所有种子按照生长时间从大到小进行排序，然后依次进行播种。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 是种子的数量。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
            int pt = plantTime[i], gt = growTime[i];
            t += pt;
            ans = Math.max(ans, t + gt);
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
            int pt = plantTime[i], gt = growTime[i];
            t += pt;
            ans = max(ans, t + gt);
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
		pt, gt := plantTime[i], growTime[i]
		t += pt
		ans = max(ans, t+gt)
	}
	return
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
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
        const [pt, gt] = [plantTime[i], growTime[i]];
        t += pt;
        ans = Math.max(ans, t + gt);
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
