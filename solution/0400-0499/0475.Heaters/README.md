# [475. 供暖器](https://leetcode.cn/problems/heaters)

[English Version](/solution/0400-0499/0475.Heaters/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>冬季已经来临。 你的任务是设计一个有固定加热半径的供暖器向所有房屋供暖。</p>

<p>在加热器的加热半径范围内的每个房屋都可以获得供暖。</p>

<p>现在，给出位于一条水平线上的房屋 <code>houses</code> 和供暖器 <code>heaters</code> 的位置，请你找出并返回可以覆盖所有房屋的最小加热半径。</p>

<p><strong>说明</strong>：所有供暖器都遵循你的半径标准，加热的半径也一样。</p>

<p> </p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> houses = [1,2,3], heaters = [2]
<strong>输出:</strong> 1
<strong>解释:</strong> 仅在位置2上有一个供暖器。如果我们将加热半径设为1，那么所有房屋就都能得到供暖。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> houses = [1,2,3,4], heaters = [1,4]
<strong>输出:</strong> 1
<strong>解释:</strong> 在位置1, 4上有两个供暖器。我们需要将加热半径设为1，这样所有房屋就都能得到供暖。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>houses = [1,5], heaters = [2]
<strong>输出：</strong>3
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= houses.length, heaters.length <= 3 * 10<sup>4</sup></code></li>
	<li><code>1 <= houses[i], heaters[i] <= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

排序 + 二分查找 + 双指针。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def findRadius(self, houses: List[int], heaters: List[int]) -> int:
        houses.sort()
        heaters.sort()

        def check(r):
            m, n = len(houses), len(heaters)
            i = j = 0
            while i < m:
                if j >= n:
                    return False
                mi = heaters[j] - r
                mx = heaters[j] + r
                if houses[i] < mi:
                    return False
                if houses[i] > mx:
                    j += 1
                else:
                    i += 1
            return True

        left, right = 0, int(1e9)
        while left < right:
            mid = (left + right) >> 1
            if check(mid):
                right = mid
            else:
                left = mid + 1
        return left
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(heaters);
        int res = 0;
        for (int x : houses) {
            int i = Arrays.binarySearch(heaters, x);
            if (i < 0) {
                i = ~i;
            }
            int dis1 = i > 0 ? x - heaters[i - 1] : Integer.MAX_VALUE;
            int dis2 = i < heaters.length ? heaters[i] - x : Integer.MAX_VALUE;
            res = Math.max(res, Math.min(dis1, dis2));
        }
        return res;
    }
}
```

### **TypeScript**

```ts
function findRadius(houses: number[], heaters: number[]): number {
    houses.sort((a, b) => a - b);
    heaters.sort((a, b) => a - b);
    const m = houses.length,
        n = heaters.length;
    let ans = 0;
    for (let i = 0, j = 0; i < m; i++) {
        let cur = Math.abs(houses[i] - heaters[j]);
        while (
            j + 1 < n &&
            Math.abs(houses[i] - heaters[j]) >=
                Math.abs(houses[i] - heaters[j + 1])
        ) {
            cur = Math.min(Math.abs(houses[i] - heaters[++j]), cur);
        }
        ans = Math.max(cur, ans);
    }
    return ans;
}
```

### **C++**

```cpp
class Solution {
public:
    int findRadius(vector<int>& houses, vector<int>& heaters) {
        sort(houses.begin(), houses.end());
        sort(heaters.begin(), heaters.end());
        int left = 0, right = 1e9;
        while (left < right) {
            int mid = left + right >> 1;
            if (check(houses, heaters, mid))
                right = mid;
            else
                left = mid + 1;
        }
        return left;
    }

    bool check(vector<int>& houses, vector<int>& heaters, int r) {
        int m = houses.size(), n = heaters.size();
        int i = 0, j = 0;
        while (i < m) {
            if (j >= n) return false;
            int mi = heaters[j] - r;
            int mx = heaters[j] + r;
            if (houses[i] < mi) return false;
            if (houses[i] > mx)
                ++j;
            else
                ++i;
        }
        return true;
    }
};
```

### **Go**

```go
func findRadius(houses []int, heaters []int) int {
	sort.Ints(houses)
	sort.Ints(heaters)
	m, n := len(houses), len(heaters)

	check := func(r int) bool {
		var i, j int
		for i < m {
			if j >= n {
				return false
			}
			mi, mx := heaters[j]-r, heaters[j]+r
			if houses[i] < mi {
				return false
			}
			if houses[i] > mx {
				j++
			} else {
				i++
			}
		}
		return true
	}
	left, right := 0, int(1e9)
	for left < right {
		mid := (left + right) >> 1
		if check(mid) {
			right = mid
		} else {
			left = mid + 1
		}
	}
	return left
}
```

### **...**

```

```

<!-- tabs:end -->
