# [2517. Maximum Tastiness of Candy Basket](https://leetcode.com/problems/maximum-tastiness-of-candy-basket)

[中文文档](/solution/2500-2599/2517.Maximum%20Tastiness%20of%20Candy%20Basket/README.md)

## Description

<p>You are given an array of positive integers <code>price</code> where <code>price[i]</code> denotes the price of the <code>i<sup>th</sup></code> candy and a positive integer <code>k</code>.</p>

<p>The store sells baskets of <code>k</code> <strong>distinct</strong> candies. The <strong>tastiness</strong> of a candy basket is the smallest absolute difference of the <strong>prices</strong> of any two candies in the basket.</p>

<p>Return <em>the <strong>maximum</strong> tastiness of a candy basket.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> price = [13,5,1,8,21,2], k = 3
<strong>Output:</strong> 8
<strong>Explanation:</strong> Choose the candies with the prices [13,5,21].
The tastiness of the candy basket is: min(|13 - 5|, |13 - 21|, |5 - 21|) = min(8, 8, 16) = 8.
It can be proven that 8 is the maximum tastiness that can be achieved.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> price = [1,3,1], k = 2
<strong>Output:</strong> 2
<strong>Explanation:</strong> Choose the candies with the prices [1,3].
The tastiness of the candy basket is: min(|1 - 3|) = min(2) = 2.
It can be proven that 2 is the maximum tastiness that can be achieved.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> price = [7,7,7,7], k = 2
<strong>Output:</strong> 0
<strong>Explanation:</strong> Choosing any two distinct candies from the candies we have will result in a tastiness of 0.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= k &lt;= price.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= price[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
