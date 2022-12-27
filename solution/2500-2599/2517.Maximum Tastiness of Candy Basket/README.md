# [2517. 礼盒的最大甜蜜度](https://leetcode.cn/problems/maximum-tastiness-of-candy-basket)

[English Version](/solution/2500-2599/2517.Maximum%20Tastiness%20of%20Candy%20Basket/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个正整数数组 <code>price</code> ，其中 <code>price[i]</code> 表示第 <code>i</code> 类糖果的价格，另给你一个正整数 <code>k</code> 。</p>

<p>商店组合 <code>k</code> 类 <strong>不同</strong> 糖果打包成礼盒出售。礼盒的 <strong>甜蜜度</strong> 是礼盒中任意两种糖果 <strong>价格</strong> 绝对差的最小值。</p>

<p>返回礼盒的 <strong>最大 </strong>甜蜜度<em>。</em></p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>price = [13,5,1,8,21,2], k = 3
<strong>输出：</strong>8
<strong>解释：</strong>选出价格分别为 [13,5,21] 的三类糖果。
礼盒的甜蜜度为 min(|13 - 5|, |13 - 21|, |5 - 21|) = min(8, 8, 16) = 8 。
可以证明能够取得的最大甜蜜度就是 8 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>price = [1,3,1], k = 2
<strong>输出：</strong>2
<strong>解释：</strong>选出价格分别为 [1,3] 的两类糖果。 
礼盒的甜蜜度为 min(|1 - 3|) = min(2) = 2 。
可以证明能够取得的最大甜蜜度就是 2 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>price = [7,7,7,7], k = 2
<strong>输出：</strong>0
<strong>解释：</strong>从现有的糖果中任选两类糖果，甜蜜度都会是 0 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= price.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= price[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>2 &lt;= k &lt;= price.length</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：二分查找**

我们先对数组 `price` 进行排序，然后二分枚举甜蜜度，找到最大的且满足至少有 $k$ 类糖果的甜蜜度。

时间复杂度 $O(n \times \log M)$，空间复杂度 $O(1)$。其中 $n$ 为数组 `price` 的长度，而 $M$ 为数组 `price` 中的最大值。本题中我们取 $M = 10^9$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maximumTastiness(self, price: List[int], k: int) -> int:
        def check(x):
            cnt = 1
            s = price[0]
            for p in price[1:]:
                if p - s >= x:
                    s = p
                    cnt += 1
            return cnt >= k

        price.sort()
        left, right = 0, 10**9
        while left < right:
            mid = (left + right + 1) >> 1
            if check(mid):
                left = mid
            else:
                right = mid - 1
        return left
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private int[] price;
    private int k;

    public int maximumTastiness(int[] price, int k) {
        Arrays.sort(price);
        this.price = price;
        this.k = k;
        int left = 0, right = 1000000000;
        while (left < right) {
            int mid = (left + right + 1) >> 1;
            if (check(mid)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    private boolean check(int x) {
        int s = price[0];
        int cnt = 1;
        for (int i = 1; i < price.length; ++i) {
            if (price[i] - s >= x) {
                s = price[i];
                ++cnt;
            }
        }
        return cnt >= k;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maximumTastiness(vector<int>& price, int k) {
        sort(price.begin(), price.end());
        int left = 0, right = 1e9;
        auto check = [&](int x) {
            int s = price[0];
            int cnt = 1;
            for (int i = 1; i < price.size(); ++i) {
                if (price[i] - s >= x) {
                    s = price[i];
                    ++cnt;
                }
            }
            return cnt >= k;
        };
        while (left < right) {
            int mid = (left + right + 1) >> 1;
            if (check(mid)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }
};
```

### **Go**

```go
func maximumTastiness(price []int, k int) int {
	sort.Ints(price)
	check := func(x int) bool {
		s := price[0]
		cnt := 1
		for _, p := range price[1:] {
			if p-s >= x {
				s = p
				cnt++
			}
		}
		return cnt >= k
	}
	left, right := 0, 1000000000
	for left < right {
		mid := (left + right + 1) >> 1
		if check(mid) {
			left = mid
		} else {
			right = mid - 1
		}
	}
	return left
}
```

### **...**

```

```

<!-- tabs:end -->
