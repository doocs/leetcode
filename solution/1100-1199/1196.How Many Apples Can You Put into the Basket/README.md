# [1196. 最多可以买到的苹果数量](https://leetcode.cn/problems/how-many-apples-can-you-put-into-the-basket)

[English Version](/solution/1100-1199/1196.How%20Many%20Apples%20Can%20You%20Put%20into%20the%20Basket/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>你有一些苹果和一个可以承载 <code>5000</code> 单位重量的篮子。</p>

<p>给定一个整数数组 <code>weight</code> ，其中 <code>weight[i]</code> 是第 <code>i</code> 个苹果的重量，返回 <em>你可以放入篮子的最大苹果数量</em> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>weight = [100,200,150,1000]
<strong>输出：</strong>4
<strong>解释：</strong>所有 4 个苹果都可以装进去，因为它们的重量之和为 1450。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>weight = [900,950,800,1000,700,800]
<strong>输出：</strong>5
<strong>解释：</strong>6 个苹果的总重量超过了 5000，所以我们只能从中任选 5 个。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= weight.length &lt;= 10<sup>3</sup></code></li>
	<li><code>1 &lt;= weight[i] &lt;= 10<sup>3</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：排序**

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maxNumberOfApples(self, weight: List[int]) -> int:
        weight.sort()
        ans = 0
        t = 0
        for v in weight:
            if t + v > 5000:
                break
            t += v
            ans += 1
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int maxNumberOfApples(int[] weight) {
        Arrays.sort(weight);
        int ans = 0, t = 0;
        for (int v : weight) {
            if (t + v > 5000) {
                break;
            }
            t += v;
            ++ans;
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maxNumberOfApples(vector<int>& weight) {
        sort(weight.begin(), weight.end());
        int ans = 0, t = 0;
        for (int v : weight) {
            if (t + v > 5000) break;
            t += v;
            ++ans;
        }
        return ans;
    }
};
```

### **Go**

```go
func maxNumberOfApples(weight []int) int {
	sort.Ints(weight)
	ans, t := 0, 0
	for _, v := range weight {
		if t+v > 5000 {
			break
		}
		t += v
		ans++
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
