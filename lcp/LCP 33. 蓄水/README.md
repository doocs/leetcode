---
comment: true
edit_url: https://github.com/doocs/leetcode/edit/main/lcp/LCP%2033.%20%E8%93%84%E6%B0%B4/README.md
---

# [LCP 33. 蓄水](https://leetcode.cn/problems/o8SXZn)

## 题目描述

<!-- 这里写题目描述 -->

给定 N 个无限容量且初始均空的水缸，每个水缸配有一个水桶用来打水，第 `i` 个水缸配备的水桶容量记作 `bucket[i]`。小扣有以下两种操作：

-   升级水桶：选择任意一个水桶，使其容量增加为 `bucket[i]+1`
-   蓄水：将全部水桶接满水，倒入各自对应的水缸

每个水缸对应最低蓄水量记作 `vat[i]`，返回小扣至少需要多少次操作可以完成所有水缸蓄水要求。

注意：实际蓄水量 **达到或超过** 最低蓄水量，即完成蓄水要求。

**示例 1：**

> 输入：`bucket = [1,3], vat = [6,8]`
>
> 输出：`4`
>
> 解释：
> 第 1 次操作升级 bucket[0]；
> 第 2 ~ 4 次操作均选择蓄水，即可完成蓄水要求。
> ![vat1.gif](https://fastly.jsdelivr.net/gh/doocs/leetcode@main/lcp/LCP%2033.%20蓄水/images/1616122992-RkDxoL-vat1.gif)

**示例 2：**

> 输入：`bucket = [9,0,1], vat = [0,2,2]`
>
> 输出：`3`
>
> 解释：
> 第 1 次操作均选择升级 bucket[1]
> 第 2~3 次操作选择蓄水，即可完成蓄水要求。

**提示：**

-   `1 <= bucket.length == vat.length <= 100`
-   `0 <= bucket[i], vat[i] <= 10^4`

## 解法

### 方法一：贪心 + 枚举

题目中涉及两个操作：升级水桶、蓄水。我们应该贪心地把升级水桶的操作放在前面，这样在蓄水时，每次能蓄水的量就会更多，操作次数就会更少。

首先，如果最低蓄水量 $vat$ 中所有元素都为 $0$，说明不需要蓄水，直接返回 $0$ 即可。

接下来，我们可以枚举蓄水的次数 $x$，其中 $x \in [1, \max(vat)]$，那么在开始蓄水前，每个水桶的容量至少应该为 $\lceil \frac{vat_i}{x} \rceil$，其中 $\lceil  \rceil$ 表示向上取整。因此，每个水桶的升级次数为 $\max(0, \lceil \frac{vat_i}{x} \rceil - bucket_i)$，我们将所有水桶的升级次数累加，记为 $y$，再加上蓄水的次数 $x$，就是总的操作次数。答案为所有 $x + y$ 中的最小值。

时间复杂度 $O(n \times M)$，其中 $n$ 和 $M$ 分别为数组 $vat$ 的长度和数组 $vat$ 中的最大值。空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def storeWater(self, bucket: List[int], vat: List[int]) -> int:
        mx = max(vat)
        if mx == 0:
            return 0
        ans = inf
        for x in range(1, mx + 1):
            y = sum(max(0, (v + x - 1) // x - b) for v, b in zip(vat, bucket))
            ans = min(ans, x + y)
        return ans
```

```java
class Solution {
    public int storeWater(int[] bucket, int[] vat) {
        int mx = Arrays.stream(vat).max().getAsInt();
        if (mx == 0) {
            return 0;
        }
        int n = vat.length;
        int ans = 1 << 30;
        for (int x = 1; x <= mx; ++x) {
            int y = 0;
            for (int i = 0; i < n; ++i) {
                y += Math.max(0, (vat[i] + x - 1) / x - bucket[i]);
            }
            ans = Math.min(ans, x + y);
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int storeWater(vector<int>& bucket, vector<int>& vat) {
        int mx = *max_element(vat.begin(), vat.end());
        if (mx == 0) {
            return 0;
        }
        int ans = 1 << 30;
        int n = bucket.size();
        for (int x = 1; x <= mx; ++x) {
            int y = 0;
            for (int i = 0; i < n; ++i) {
                y += max(0, (vat[i] + x - 1) / x - bucket[i]);
            }
            ans = min(ans, x + y);
        }
        return ans;
    }
};
```

```go
func storeWater(bucket []int, vat []int) int {
	mx := slices.Max(vat)
	if mx == 0 {
		return 0
	}
	ans := 1 << 30
	for x := 1; x <= mx; x++ {
		y := 0
		for i, v := range vat {
			y += max(0, (v+x-1)/x-bucket[i])
		}
		ans = min(ans, x+y)
	}
	return ans
}
```

```ts
function storeWater(bucket: number[], vat: number[]): number {
    const mx = Math.max(...vat);
    if (mx === 0) {
        return 0;
    }
    const n = vat.length;
    let ans = 1 << 30;
    for (let x = 1; x <= mx; ++x) {
        let y = 0;
        for (let i = 0; i < n; ++i) {
            y += Math.max(0, Math.ceil(vat[i] / x) - bucket[i]);
        }
        ans = Math.min(ans, x + y);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
