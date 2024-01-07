# [10035. 对角线最长的矩形的面积](https://leetcode.cn/problems/maximum-area-of-longest-diagonal-rectangle)

[English Version](/solution/10000-10099/10035.Maximum%20Area%20of%20Longest%20Diagonal%20Rectangle/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从<strong> 0</strong> 开始的二维整数数组 <code>dimensions</code>。</p>

<p>对于所有下标 <code>i</code>（<code>0 &lt;= i &lt; dimensions.length</code>），<code>dimensions[i][0]</code> 表示矩形 <span style="font-size: 13.3333px;"> <code>i</code></span> 的长度，而 <code>dimensions[i][1]</code> 表示矩形 <span style="font-size: 13.3333px;"> <code>i</code></span> 的宽度。</p>

<p>返回对角线最 <strong>长 </strong>的矩形的<strong> 面积 </strong>。如果存在多个对角线长度相同的矩形，返回面积最<strong> 大 </strong>的矩形的面积。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>dimensions = [[9,3],[8,6]]
<strong>输出：</strong>48
<strong>解释：</strong>
下标 = 0，长度 = 9，宽度 = 3。对角线长度 = sqrt(9 * 9 + 3 * 3) = sqrt(90) ≈<!-- notionvc: 882cf44c-3b17-428e-9c65-9940810216f1 --> 9.487。
下标 = 1，长度 = 8，宽度 = 6。对角线长度 = sqrt(8 * 8 + 6 * 6) = sqrt(100) = 10。
因此，下标为 1 的矩形对角线更长，所以返回面积 = 8 * 6 = 48。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>dimensions = [[3,4],[4,3]]
<strong>输出：</strong>12
<strong>解释：</strong>两个矩形的对角线长度相同，为 5，所以最大面积 = 12。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= dimensions.length &lt;= 100</code></li>
	<li><code>dimensions[i].length == 2</code></li>
	<li><code>1 &lt;= dimensions[i][0], dimensions[i][1] &lt;= 100</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def areaOfMaxDiagonal(self, dimensions: List[List[int]]) -> int:
        ans = mx = 0
        for l, w in dimensions:
            t = l**2 + w**2
            if mx < t:
                mx = t
                ans = l * w
            elif mx == t:
                ans = max(ans, l * w)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int areaOfMaxDiagonal(int[][] dimensions) {
        int ans = 0, mx = 0;
        for (var d : dimensions) {
            int l = d[0], w = d[1];
            int t = l * l + w * w;
            if (mx < t) {
                mx = t;
                ans = l * w;
            } else if (mx == t) {
                ans = Math.max(ans, l * w);
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
    int areaOfMaxDiagonal(vector<vector<int>>& dimensions) {
        int ans = 0, mx = 0;
        for (auto& d : dimensions) {
            int l = d[0], w = d[1];
            int t = l * l + w * w;
            if (mx < t) {
                mx = t;
                ans = l * w;
            } else if (mx == t) {
                ans = max(ans, l * w);
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func areaOfMaxDiagonal(dimensions [][]int) (ans int) {
	mx := 0
	for _, d := range dimensions {
		l, w := d[0], d[1]
		t := l*l + w*w
		if mx < t {
			mx = t
			ans = l * w
		} else if mx == t {
			ans = max(ans, l*w)
		}
	}
	return
}
```

### **TypeScript**

```ts
function areaOfMaxDiagonal(dimensions: number[][]): number {
    let [ans, mx] = [0, 0];
    for (const [l, w] of dimensions) {
        const t = l * l + w * w;
        if (mx < t) {
            mx = t;
            ans = l * w;
        } else if (mx === t) {
            ans = Math.max(ans, l * w);
        }
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
