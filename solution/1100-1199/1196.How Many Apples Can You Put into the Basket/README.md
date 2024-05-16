---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1100-1199/1196.How%20Many%20Apples%20Can%20You%20Put%20into%20the%20Basket/README.md
rating: 1248
source: 第 9 场双周赛 Q1
tags:
    - 贪心
    - 数组
    - 排序
---

# [1196. 最多可以买到的苹果数量 🔒](https://leetcode.cn/problems/how-many-apples-can-you-put-into-the-basket)

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

### 方法一：贪心

要使得苹果数量最多，那么就要使得苹果的重量尽可能的小，因此我们可以对苹果的重量进行排序，然后从小到大依次放入篮子中，直到篮子的重量超过 $5000$ 为止，返回此时放入篮子的苹果数量。

如果所有的苹果都能放入篮子中，那么就返回苹果的数量。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(\log n)$。其中 $n$ 是苹果的数量。

<!-- tabs:start -->

```python
class Solution:
    def maxNumberOfApples(self, weight: List[int]) -> int:
        weight.sort()
        s = 0
        for i, x in enumerate(weight):
            s += x
            if s > 5000:
                return i
        return len(weight)
```

```java
class Solution {
    public int maxNumberOfApples(int[] weight) {
        Arrays.sort(weight);
        int s = 0;
        for (int i = 0; i < weight.length; ++i) {
            s += weight[i];
            if (s > 5000) {
                return i;
            }
        }
        return weight.length;
    }
}
```

```cpp
class Solution {
public:
    int maxNumberOfApples(vector<int>& weight) {
        sort(weight.begin(), weight.end());
        int s = 0;
        for (int i = 0; i < weight.size(); ++i) {
            s += weight[i];
            if (s > 5000) {
                return i;
            }
        }
        return weight.size();
    }
};
```

```go
func maxNumberOfApples(weight []int) int {
	sort.Ints(weight)
	s := 0
	for i, x := range weight {
		s += x
		if s > 5000 {
			return i
		}
	}
	return len(weight)
}
```

```ts
function maxNumberOfApples(weight: number[]): number {
    weight.sort((a, b) => a - b);
    let s = 0;
    for (let i = 0; i < weight.length; ++i) {
        s += weight[i];
        if (s > 5000) {
            return i;
        }
    }
    return weight.length;
}
```

<!-- tabs:end -->

<!-- end -->
