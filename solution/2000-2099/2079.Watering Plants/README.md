# [2079. 给植物浇水](https://leetcode.cn/problems/watering-plants)

[English Version](/solution/2000-2099/2079.Watering%20Plants/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>你打算用一个水罐给花园里的 <code>n</code> 株植物浇水。植物排成一行，从左到右进行标记，编号从 <code>0</code> 到 <code>n - 1</code> 。其中，第 <code>i</code> 株植物的位置是 <code>x = i</code> 。<code>x = -1</code>&nbsp;处有一条河，你可以在那里重新灌满你的水罐。</p>

<p>每一株植物都需要浇特定量的水。你将会按下面描述的方式完成浇水：</p>

<ul>
	<li>按从左到右的顺序给植物浇水。</li>
	<li>在给当前植物浇完水之后，如果你没有足够的水 <strong>完全</strong> 浇灌下一株植物，那么你就需要返回河边重新装满水罐。</li>
	<li>你 <strong>不能</strong> 提前重新灌满水罐。</li>
</ul>

<p>最初，你在河边（也就是，<code>x = -1</code>），在 x 轴上每移动 <strong>一个单位</strong>&nbsp;都需要 <strong>一步</strong> 。</p>

<p>给你一个下标从 <strong>0</strong> 开始的整数数组 <code>plants</code> ，数组由 <code>n</code> 个整数组成。其中，<code>plants[i]</code> 为第 <code>i</code> 株植物需要的水量。另有一个整数 <code>capacity</code> 表示水罐的容量，返回浇灌所有植物需要的 <strong>步数</strong> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>plants = [2,2,3,3], capacity = 5
<strong>输出：</strong>14
<strong>解释：</strong>从河边开始，此时水罐是装满的：
- 走到植物 0 (1 步) ，浇水。水罐中还有 3 单位的水。
- 走到植物 1 (1 步) ，浇水。水罐中还有 1 单位的水。
- 由于不能完全浇灌植物 2 ，回到河边取水 (2 步)。
- 走到植物 2 (3 步) ，浇水。水罐中还有 2 单位的水。
- 由于不能完全浇灌植物 3 ，回到河边取水 (3 步)。
- 走到植物 3 (4 步) ，浇水。
需要的步数是 = 1 + 1 + 2 + 3 + 3 + 4 = 14 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>plants = [1,1,1,4,2,3], capacity = 4
<strong>输出：</strong>30
<strong>解释：</strong>从河边开始，此时水罐是装满的：
- 走到植物 0，1，2 (3 步) ，浇水。回到河边取水 (3 步)。
- 走到植物 3 (4 步) ，浇水。回到河边取水 (4 步)。
- 走到植物 4 (5 步) ，浇水。回到河边取水 (5 步)。
- 走到植物 5 (6 步) ，浇水。
需要的步数是 = 3 + 3 + 4 + 4 + 5 + 5 + 6 = 30 。</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>plants = [7,7,7,7,7,7,7], capacity = 8
<strong>输出：</strong>49
<strong>解释：</strong>每次浇水都需要重新灌满水罐。
需要的步数是 = 1 + 1 + 2 + 2 + 3 + 3 + 4 + 4 + 5 + 5 + 6 + 6 + 7 = 49 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == plants.length</code></li>
	<li><code>1 &lt;= n &lt;= 1000</code></li>
	<li><code>1 &lt;= plants[i] &lt;= 10<sup>6</sup></code></li>
	<li><code>max(plants[i]) &lt;= capacity &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def wateringPlants(self, plants: List[int], capacity: int) -> int:
        ans, cap = 0, capacity
        for i, x in enumerate(plants):
            if cap >= x:
                cap -= x
                ans += 1
            else:
                cap = capacity - x
                ans += i * 2 + 1
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int wateringPlants(int[] plants, int capacity) {
        int ans = 0, cap = capacity;
        for (int i = 0; i < plants.length; ++i) {
            if (cap >= plants[i]) {
                cap -= plants[i];
                ++ans;
            } else {
                ans += (i * 2 + 1);
                cap = capacity - plants[i];
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
    int wateringPlants(vector<int>& plants, int capacity) {
        int ans = 0, cap = capacity;
        for (int i = 0; i < plants.size(); ++i) {
            if (cap >= plants[i]) {
                cap -= plants[i];
                ++ans;
            } else {
                cap = capacity - plants[i];
                ans += i * 2 + 1;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func wateringPlants(plants []int, capacity int) int {
	ans, cap := 0, capacity
	for i, x := range plants {
		if cap >= x {
			cap -= x
			ans++
		} else {
			cap = capacity - x
			ans += i*2 + 1
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
