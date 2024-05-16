---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2500-2599/2517.Maximum%20Tastiness%20of%20Candy%20Basket/README.md
rating: 2020
source: 第 325 场周赛 Q3
tags:
    - 贪心
    - 数组
    - 二分查找
    - 排序
---

<!-- problem:start -->

# [2517. 礼盒的最大甜蜜度](https://leetcode.cn/problems/maximum-tastiness-of-candy-basket)

[English Version](/solution/2500-2599/2517.Maximum%20Tastiness%20of%20Candy%20Basket/README_EN.md)

## 题目描述

<!-- description:start -->

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
	<li><code>2 &lt;= k &lt;= price.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= price[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：贪心 + 二分查找

我们注意到，如果一个甜蜜度为 $x$ 的礼盒是可行的，那么甜蜜度小于 $x$ 的礼盒也是可行的，这存在着单调性，因此我们可以使用二分查找的方法，找到最大的可行甜蜜度。

我们首先将数组 $price$ 排序，然后定义二分查找的左边界 $l=0$, $r=price[n-1]-price[0]$。每一次，我们计算出当前的中间值 $mid = \lfloor \frac{l+r+1}{2} \rfloor$，以 $mid$ 作为甜蜜度，判断是否可行。若可行，那么我们将左边界 $l$ 更新为 $mid$，否则将右边界 $r$ 更新为 $mid-1$。最后返回 $l$ 即可。

那么问题的关键转化为：判断一个甜蜜度是否可行，我们通过函数 $check(x)$ 来实现。函数 $check(x)$ 的执行逻辑如下：

定义一个变量 $cnt$ 表示当前已经选取的糖果的数量，初始值为 $0$，定义一个变量 $pre$ 表示上一个选取的糖果的价格，初始值为 $-x$。然后我们遍历排好序的数组 $price$，对于每一个糖果的价格 $cur$，如果 $cur-pre \geq x$，那么我们就选取这个糖果，将 $pre$ 更新为 $cur$，并将 $cnt$ 加一。最后判断 $cnt$ 是否大于等于 $k$，如果是，那么返回 $true$，否则返回 $false$。

时间复杂度 $O(n \times (\log n + \log M))$，空间复杂度 $O(\log n)$。其中 $n$ 是数组 $price$ 的长度；而 $M$ 是数组 $price$ 中的最大值，本题中 $M \leq 10^9$。

<!-- tabs:start -->

```python
class Solution:
    def maximumTastiness(self, price: List[int], k: int) -> int:
        def check(x: int) -> bool:
            cnt, pre = 0, -x
            for cur in price:
                if cur - pre >= x:
                    pre = cur
                    cnt += 1
            return cnt >= k

        price.sort()
        l, r = 0, price[-1] - price[0]
        while l < r:
            mid = (l + r + 1) >> 1
            if check(mid):
                l = mid
            else:
                r = mid - 1
        return l
```

```java
class Solution {
    public int maximumTastiness(int[] price, int k) {
        Arrays.sort(price);
        int l = 0, r = price[price.length - 1] - price[0];
        while (l < r) {
            int mid = (l + r + 1) >> 1;
            if (check(price, k, mid)) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }

    private boolean check(int[] price, int k, int x) {
        int cnt = 0, pre = -x;
        for (int cur : price) {
            if (cur - pre >= x) {
                pre = cur;
                ++cnt;
            }
        }
        return cnt >= k;
    }
}
```

```cpp
class Solution {
public:
    int maximumTastiness(vector<int>& price, int k) {
        sort(price.begin(), price.end());
        int l = 0, r = price.back() - price[0];
        auto check = [&](int x) -> bool {
            int cnt = 0, pre = -x;
            for (int& cur : price) {
                if (cur - pre >= x) {
                    pre = cur;
                    ++cnt;
                }
            }
            return cnt >= k;
        };
        while (l < r) {
            int mid = (l + r + 1) >> 1;
            if (check(mid)) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }
};
```

```go
func maximumTastiness(price []int, k int) int {
	sort.Ints(price)
	return sort.Search(price[len(price)-1], func(x int) bool {
		cnt, pre := 0, -x
		for _, cur := range price {
			if cur-pre >= x {
				pre = cur
				cnt++
			}
		}
		return cnt < k
	}) - 1
}
```

```ts
function maximumTastiness(price: number[], k: number): number {
    price.sort((a, b) => a - b);
    let l = 0;
    let r = price[price.length - 1] - price[0];
    const check = (x: number): boolean => {
        let [cnt, pre] = [0, -x];
        for (const cur of price) {
            if (cur - pre >= x) {
                pre = cur;
                ++cnt;
            }
        }
        return cnt >= k;
    };
    while (l < r) {
        const mid = (l + r + 1) >> 1;
        if (check(mid)) {
            l = mid;
        } else {
            r = mid - 1;
        }
    }
    return l;
}
```

```cs
public class Solution {
    public int MaximumTastiness(int[] price, int k) {
        Array.Sort(price);
        int l = 0, r = price[price.Length - 1] - price[0];
        while (l < r) {
            int mid = (l + r + 1) >> 1;
            if (check(price, mid, k)) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }

    private bool check(int[] price, int x, int k) {
        int cnt = 0, pre = -x;
        foreach (int cur in price) {
            if (cur - pre >= x) {
                ++cnt;
                pre = cur;
            }
        }
        return cnt >= k;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
