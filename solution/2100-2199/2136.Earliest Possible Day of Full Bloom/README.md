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

生长时间越长的种子，越先播种，因此将 $growTime$ 降序排列。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def earliestFullBloom(self, plantTime: List[int], growTime: List[int]) -> int:
        ans = t = 0
        for a, b in sorted(zip(plantTime, growTime), key=lambda x: -x[1]):
            t += a
            ans = max(ans, t + b)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int earliestFullBloom(int[] plantTime, int[] growTime) {
        int n = plantTime.length;
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; ++i) {
            arr[i] = new int[] {plantTime[i], growTime[i]};
        }
        Arrays.sort(arr, (a, b) -> b[1] - a[1]);
        int ans = 0;
        int t = 0;
        for (int[] e : arr) {
            t += e[0];
            ans = Math.max(ans, t + e[1]);
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
        vector<pair<int, int>> arr;
        for (int i = 0; i < n; ++i) arr.push_back({-growTime[i], plantTime[i]});
        sort(arr.begin(), arr.end());
        int ans = 0, t = 0;
        for (auto [a, b] : arr) {
            t += b;
            ans = max(ans, t - a);
        }
        return ans;
    }
};
```

### **Go**

```go
func earliestFullBloom(plantTime []int, growTime []int) int {
	arr := [][]int{}
	for i, a := range plantTime {
		arr = append(arr, []int{a, growTime[i]})
	}
	sort.Slice(arr, func(i, j int) bool {
		return arr[i][1] > arr[j][1]
	})
	ans, t := 0, 0
	for _, e := range arr {
		t += e[0]
		ans = max(ans, t+e[1])
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **TypeScript**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```ts

```

### **...**

```

```

<!-- tabs:end -->
