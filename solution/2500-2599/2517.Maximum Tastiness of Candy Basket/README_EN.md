# [2517. Maximum Tastiness of Candy Basket](https://leetcode.com/problems/maximum-tastiness-of-candy-basket)

[中文文档](/solution/2500-2599/2517.Maximum%20Tastiness%20of%20Candy%20Basket/README.md)

<!-- tags:Greedy,Array,Binary Search,Sorting -->

<!-- difficulty:Medium -->

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

### Solution 1

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

<!-- end -->
