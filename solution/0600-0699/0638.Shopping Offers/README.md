# [638. 大礼包](https://leetcode-cn.com/problems/shopping-offers)

[English Version](/solution/0600-0699/0638.Shopping%20Offers/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>在LeetCode商店中， 有许多在售的物品。</p>

<p>然而，也有一些大礼包，每个大礼包以优惠的价格捆绑销售一组物品。</p>

<p>现给定每个物品的价格，每个大礼包包含物品的清单，以及待购物品清单。请输出<strong>确切</strong>完成待购清单的最低花费。</p>

<p>每个大礼包的由一个数组中的一组数据描述，最后一个数字代表大礼包的价格，其他数字分别表示内含的其他种类物品的数量。</p>

<p>任意大礼包可无限次购买。</p>

<p><strong>示例 1:</strong></p>

<pre><strong>输入:</strong> [2,5], [[3,0,5],[1,2,10]], [3,2]
<strong>输出:</strong> 14
<strong>解释:</strong> 
有A和B两种物品，价格分别为&yen;2和&yen;5。
大礼包1，你可以以&yen;5的价格购买3A和0B。
大礼包2， 你可以以&yen;10的价格购买1A和2B。
你需要购买3个A和2个B， 所以你付了&yen;10购买了1A和2B（大礼包2），以及&yen;4购买2A。</pre>

<p><strong>示例 2:</strong></p>

<pre><strong>输入:</strong> [2,3,4], [[1,1,0,4],[2,2,1,9]], [1,2,1]
<strong>输出:</strong> 11
<strong>解释:</strong> 
A，B，C的价格分别为&yen;2，&yen;3，&yen;4.
你可以用&yen;4购买1A和1B，也可以用&yen;9购买2A，2B和1C。
你需要买1A，2B和1C，所以你付了&yen;4买了1A和1B（大礼包1），以及&yen;3购买1B， &yen;4购买1C。
你不可以购买超出待购清单的物品，尽管购买大礼包2更加便宜。
</pre>

<p><strong>说明:</strong></p>

<ol>
	<li>最多6种物品， 100种大礼包。</li>
	<li>每种物品，你最多只需要购买6个。</li>
	<li>你不可以购买超出待购清单的物品，即使更便宜。</li>
</ol>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def shoppingOffers(self, price: List[int], special: List[List[int]], needs: List[int]) -> int:
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
                ans = min(ans, offer[-1] +
                          self.shoppingOffers(price, special, t))
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
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
                ans = Math.min(ans, offer.get(offer.size() - 1) + shoppingOffers(price, special, t));
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
        for (auto& offer : special)
        {
            t.clear();
            for (int j = 0; j < needs.size(); ++j)
            {
                if (offer[j] > needs[j])
                {
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
