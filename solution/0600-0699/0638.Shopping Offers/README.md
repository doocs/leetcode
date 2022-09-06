# [638. 大礼包](https://leetcode.cn/problems/shopping-offers)

[English Version](/solution/0600-0699/0638.Shopping%20Offers/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>在 LeetCode 商店中， 有 <code>n</code> 件在售的物品。每件物品都有对应的价格。然而，也有一些大礼包，每个大礼包以优惠的价格捆绑销售一组物品。</p>

<p>给你一个整数数组 <code>price</code> 表示物品价格，其中 <code>price[i]</code> 是第 <code>i</code> 件物品的价格。另有一个整数数组 <code>needs</code> 表示购物清单，其中 <code>needs[i]</code> 是需要购买第 <code>i</code> 件物品的数量。</p>

<p>还有一个数组 <code>special</code> 表示大礼包，<code>special[i]</code> 的长度为 <code>n + 1</code> ，其中 <code>special[i][j]</code> 表示第 <code>i</code> 个大礼包中内含第 <code>j</code> 件物品的数量，且 <code>special[i][n]</code> （也就是数组中的最后一个整数）为第 <code>i</code> 个大礼包的价格。</p>

<p>返回<strong> 确切 </strong>满足购物清单所需花费的最低价格，你可以充分利用大礼包的优惠活动。你不能购买超出购物清单指定数量的物品，即使那样会降低整体价格。任意大礼包可无限次购买。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>price = [2,5], special = [[3,0,5],[1,2,10]], needs = [3,2]
<strong>输出：</strong>14
<strong>解释：</strong>有 A 和 B 两种物品，价格分别为 ¥2 和 ¥5 。 
大礼包 1 ，你可以以 ¥5 的价格购买 3A 和 0B 。 
大礼包 2 ，你可以以 ¥10 的价格购买 1A 和 2B 。 
需要购买 3 个 A 和 2 个 B ， 所以付 ¥10 购买 1A 和 2B（大礼包 2），以及 ¥4 购买 2A 。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>price = [2,3,4], special = [[1,1,0,4],[2,2,1,9]], needs = [1,2,1]
<strong>输出：</strong>11
<strong>解释：</strong>A ，B ，C 的价格分别为 ¥2 ，¥3 ，¥4 。
可以用 ¥4 购买 1A 和 1B ，也可以用 ¥9 购买 2A ，2B 和 1C 。 
需要买 1A ，2B 和 1C ，所以付 ¥4 买 1A 和 1B（大礼包 1），以及 ¥3 购买 1B ， ¥4 购买 1C 。 
不可以购买超出待购清单的物品，尽管购买大礼包 2 更加便宜。</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == price.length</code></li>
	<li><code>n == needs.length</code></li>
	<li><code>1 <= n <= 6</code></li>
	<li><code>0 <= price[i] <= 10</code></li>
	<li><code>0 <= needs[i] <= 10</code></li>
	<li><code>1 <= special.length <= 100</code></li>
	<li><code>special[i].length == n + 1</code></li>
	<li><code>0 <= special[i][j] <= 50</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def shoppingOffers(
        self, price: List[int], special: List[List[int]], needs: List[int]
    ) -> int:
        def total(price, needs):
            return sum(price[i] * needs[i] for i in range(len(needs)))

        ans = total(price, needs)
        t = []
        for offer in special:
            t.clear()
            for j in range(len(needs)):
                if offer[j] > needs[j]:
                    t.clear()
                    break
                t.append(needs[j] - offer[j])
            if t:
                ans = min(ans, offer[-1] + self.shoppingOffers(price, special, t))
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int shoppingOffers(
        List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        int ans = total(price, needs);
        List<Integer> t = new ArrayList<>();
        for (List<Integer> offer : special) {
            t.clear();
            for (int j = 0; j < needs.size(); ++j) {
                if (offer.get(j) > needs.get(j)) {
                    t.clear();
                    break;
                }
                t.add(needs.get(j) - offer.get(j));
            }
            if (!t.isEmpty()) {
                ans = Math.min(
                    ans, offer.get(offer.size() - 1) + shoppingOffers(price, special, t));
            }
        }
        return ans;
    }

    private int total(List<Integer> price, List<Integer> needs) {
        int s = 0;
        for (int i = 0; i < price.size(); ++i) {
            s += price.get(i) * needs.get(i);
        }
        return s;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int shoppingOffers(vector<int>& price, vector<vector<int>>& special, vector<int>& needs) {
        int ans = total(price, needs);
        vector<int> t;
        for (auto& offer : special) {
            t.clear();
            for (int j = 0; j < needs.size(); ++j) {
                if (offer[j] > needs[j]) {
                    t.clear();
                    break;
                }
                t.push_back(needs[j] - offer[j]);
            }
            if (!t.empty()) ans = min(ans, offer[offer.size() - 1] + shoppingOffers(price, special, t));
        }
        return ans;
    }

    int total(vector<int>& price, vector<int>& needs) {
        int s = 0;
        for (int i = 0; i < price.size(); ++i) s += price[i] * needs[i];
        return s;
    }
};
```

### **Go**

```go
func shoppingOffers(price []int, special [][]int, needs []int) int {
	total := func(price, needs []int) int {
		s := 0
		for i := 0; i < len(needs); i++ {
			s += price[i] * needs[i]
		}
		return s
	}

	min := func(a, b int) int {
		if a < b {
			return a
		}
		return b
	}

	ans := total(price, needs)
	var t []int
	for _, offer := range special {
		t = t[:0]
		for j := 0; j < len(needs); j++ {
			if offer[j] > needs[j] {
				t = t[:0]
				break
			}
			t = append(t, needs[j]-offer[j])
		}
		if len(t) > 0 {
			ans = min(ans, offer[len(offer)-1]+shoppingOffers(price, special, t))
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
