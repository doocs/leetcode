# [1732. 找到最高海拔](https://leetcode.cn/problems/find-the-highest-altitude)

[English Version](/solution/1700-1799/1732.Find%20the%20Highest%20Altitude/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>有一个自行车手打算进行一场公路骑行，这条路线总共由 <code>n + 1</code> 个不同海拔的点组成。自行车手从海拔为 <code>0</code> 的点 <code>0</code> 开始骑行。</p>

<p>给你一个长度为 <code>n</code> 的整数数组 <code>gain</code> ，其中 <code>gain[i]</code> 是点 <code>i</code> 和点 <code>i + 1</code> 的 <strong>净海拔高度差</strong>（<code>0 <= i < n</code>）。请你返回 <strong>最高点的海拔</strong> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>gain = [-5,1,5,0,-7]
<b>输出：</b>1
<b>解释：</b>海拔高度依次为 [0,-5,-4,1,1,-6] 。最高海拔为 1 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>gain = [-4,-3,-2,-1,4,3,2]
<b>输出：</b>0
<b>解释：</b>海拔高度依次为 [0,-4,-7,-9,-10,-6,-3,-1] 。最高海拔为 0 。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == gain.length</code></li>
	<li><code>1 <= n <= 100</code></li>
	<li><code>-100 <= gain[i] <= 100</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：前缀和（差分数组）**

我们假设每个点的海拔为 $h_i$，由于 $gain[i]$ 表示第 $i$ 个点和第 $i + 1$ 个点的海拔差，因此 $gain[i] = h_{i + 1} - h_i$。那么：

$$
\sum_{i = 0}^{n-1} gain[i] = h_1 - h_0 + h_2 - h_1 + \cdots + h_n - h_{n - 1} = h_n - h_0 = h_n
$$

即：

$$
h_{i+1} = \sum_{j = 0}^{i} gain[j]
$$

可以发现，每个点的海拔都可以通过前缀和的方式计算出来。因此，我们只需要遍历一遍数组，求出前缀和的最大值，即为最高点的海拔。

> 实际上题目中的 $gain$ 数组是一个差分数组，对差分数组求前缀和即可得到原海拔数组。然后求出原海拔数组的最大值即可。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为数组 $gain$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def largestAltitude(self, gain: List[int]) -> int:
        return max(accumulate(gain, initial=0))
```

```python
class Solution:
    def largestAltitude(self, gain: List[int]) -> int:
        ans = h = 0
        for v in gain:
            h += v
            ans = max(ans, h)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int largestAltitude(int[] gain) {
        int ans = 0, h = 0;
        for (int v : gain) {
            h += v;
            ans = Math.max(ans, h);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int largestAltitude(vector<int>& gain) {
        int ans = 0, h = 0;
        for (int v : gain) h += v, ans = max(ans, h);
        return ans;
    }
};
```

### **Go**

```go
func largestAltitude(gain []int) (ans int) {
	h := 0
	for _, v := range gain {
		h += v
		if ans < h {
			ans = h
		}
	}
	return
}
```

### **JavaScript**

```js
/**
 * @param {number[]} gain
 * @return {number}
 */
var largestAltitude = function (gain) {
    let ans = 0;
    let h = 0;
    for (const v of gain) {
        h += v;
        ans = Math.max(ans, h);
    }
    return ans;
};
```

### **Rust**

```rust
impl Solution {
    pub fn largest_altitude(gain: Vec<i32>) -> i32 {
        let mut ans = 0;
        let mut h = 0;
        for v in gain.iter() {
            h += v;
            ans = ans.max(h);
        }
        ans
    }
}
```

### **C**

```c
#define max(a, b) (((a) > (b)) ? (a) : (b))

int largestAltitude(int *gain, int gainSize) {
    int ans = 0;
    int h = 0;
    for (int i = 0; i < gainSize; i++) {
        h += gain[i];
        ans = max(ans, h);
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
