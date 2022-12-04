# [1774. Closest Dessert Cost](https://leetcode.com/problems/closest-dessert-cost)

[中文文档](/solution/1700-1799/1774.Closest%20Dessert%20Cost/README.md)

## Description

<p>You would like to make dessert and are preparing to buy the ingredients. You have <code>n</code> ice cream base flavors and <code>m</code> types of toppings to choose from. You must follow these rules when making your dessert:</p>

<ul>
	<li>There must be <strong>exactly one</strong> ice cream base.</li>
	<li>You can add <strong>one or more</strong> types of topping or have no toppings at all.</li>
	<li>There are <strong>at most two</strong> of <strong>each type</strong> of topping.</li>
</ul>

<p>You are given three inputs:</p>

<ul>
	<li><code>baseCosts</code>, an integer array of length <code>n</code>, where each <code>baseCosts[i]</code> represents the price of the <code>i<sup>th</sup></code> ice cream base flavor.</li>
	<li><code>toppingCosts</code>, an integer array of length <code>m</code>, where each <code>toppingCosts[i]</code> is the price of <strong>one</strong> of the <code>i<sup>th</sup></code> topping.</li>
	<li><code>target</code>, an integer representing your target price for dessert.</li>
</ul>

<p>You want to make a dessert with a total cost as close to <code>target</code> as possible.</p>

<p>Return <em>the closest possible cost of the dessert to </em><code>target</code>. If there are multiple, return <em>the <strong>lower</strong> one.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> baseCosts = [1,7], toppingCosts = [3,4], target = 10
<strong>Output:</strong> 10
<strong>Explanation:</strong> Consider the following combination (all 0-indexed):
- Choose base 1: cost 7
- Take 1 of topping 0: cost 1 x 3 = 3
- Take 0 of topping 1: cost 0 x 4 = 0
Total: 7 + 3 + 0 = 10.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> baseCosts = [2,3], toppingCosts = [4,5,100], target = 18
<strong>Output:</strong> 17
<strong>Explanation:</strong> Consider the following combination (all 0-indexed):
- Choose base 1: cost 3
- Take 1 of topping 0: cost 1 x 4 = 4
- Take 2 of topping 1: cost 2 x 5 = 10
- Take 0 of topping 2: cost 0 x 100 = 0
Total: 3 + 4 + 10 + 0 = 17. You cannot make a dessert with a total cost of 18.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> baseCosts = [3,10], toppingCosts = [2,5], target = 9
<strong>Output:</strong> 8
<strong>Explanation:</strong> It is possible to make desserts with cost 8 and 10. Return 8 as it is the lower cost.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == baseCosts.length</code></li>
	<li><code>m == toppingCosts.length</code></li>
	<li><code>1 &lt;= n, m &lt;= 10</code></li>
	<li><code>1 &lt;= baseCosts[i], toppingCosts[i] &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= target &lt;= 10<sup>4</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def closestCost(self, baseCosts: List[int], toppingCosts: List[int], target: int) -> int:
        def dfs(i, t):
            if i >= len(toppingCosts):
                arr.append(t)
                return
            dfs(i + 1, t)
            dfs(i + 1, t + toppingCosts[i])

        arr = []
        dfs(0, 0)
        arr.sort()
        d = ans = inf
        for x in baseCosts:
            for y in arr:
                i = bisect_left(arr, target - x - y)
                for j in (i, i - 1):
                    if 0 <= j < len(arr):
                        t = abs(x + y + arr[j] - target)
                        if d > t or (d == t and ans > x + y + arr[j]):
                            d = t
                            ans = x + y + arr[j]
        return ans
```

### **Java**

```java
class Solution {
    private List<Integer> arr = new ArrayList<>();
    private int[] ts;
    private int inf = 1 << 30;

    public int closestCost(int[] baseCosts, int[] toppingCosts, int target) {
        ts = toppingCosts;
        dfs(0, 0);
        Collections.sort(arr);
        int d = inf, ans = inf;
        for (int x : baseCosts) {
            for (int y : arr) {
                int i = search(target - x - y);
                for (int j : new int[] {i, i - 1}) {
                    if (j >= 0 && j < arr.size()) {
                        int t = Math.abs(x + y + arr.get(j) - target);
                        if (d > t || (d == t && ans > x + y + arr.get(j))) {
                            d = t;
                            ans = x + y + arr.get(j);
                        }
                    }
                }
            }
        }
        return ans;
    }

    private int search(int x) {
        int left = 0, right = arr.size();
        while (left < right) {
            int mid = (left + right) >> 1;
            if (arr.get(mid) >= x) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private void dfs(int i, int t) {
        if (i >= ts.length) {
            arr.add(t);
            return;
        }
        dfs(i + 1, t);
        dfs(i + 1, t + ts[i]);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    const int inf = INT_MAX;
    int closestCost(vector<int>& baseCosts, vector<int>& toppingCosts, int target) {
        vector<int> arr;
        function<void(int, int)> dfs = [&](int i, int t) {
            if (i >= toppingCosts.size()) {
                arr.push_back(t);
                return;
            }
            dfs(i + 1, t);
            dfs(i + 1, t + toppingCosts[i]);
        };
        dfs(0, 0);
        sort(arr.begin(), arr.end());
        int d = inf, ans = inf;
        for (int x : baseCosts) {
            for (int y : arr) {
                int i = lower_bound(arr.begin(), arr.end(), target - x - y) - arr.begin();
                for (int j = i - 1; j < i + 1; ++j) {
                    if (j >= 0 && j < arr.size()) {
                        int t = abs(x + y + arr[j] - target);
                        if (d > t || (d == t && ans > x + y + arr[j])) {
                            d = t;
                            ans = x + y + arr[j];
                        }
                    }
                }
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func closestCost(baseCosts []int, toppingCosts []int, target int) int {
    arr := []int{}
    var dfs func(int, int)
    dfs = func(i, t int) {
        if i >= len(toppingCosts) {
            arr = append(arr, t)
            return
        }
        dfs(i + 1, t)
        dfs(i + 1, t + toppingCosts[i])
    }
    dfs(0, 0)
    sort.Ints(arr)
    const inf = 1 << 30
    ans, d := inf, inf
    for _, x := range baseCosts {
        for _, y := range arr {
            i := sort.Search(len(arr), func(i int) bool { return arr[i] >= target - x - y })
            for j := i - 1; j < i + 1; j++ {
                if j >= 0 && j < len(arr) {
                    t := abs(x + y + arr[j] - target)
                    if d > t || (d == t && ans > x + y + arr[j]) {
                        d = t
                        ans = x + y + arr[j]
                    }
                }
            }
        }
    }
    return ans
}

func abs(x int) int {
    if x < 0 {
        return -x
    }
    return x
}
```

### **...**

```

```

<!-- tabs:end -->
