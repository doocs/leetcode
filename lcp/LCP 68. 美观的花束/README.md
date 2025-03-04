---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/lcp/LCP%2068.%20%E7%BE%8E%E8%A7%82%E7%9A%84%E8%8A%B1%E6%9D%9F/README.md
---

<!-- problem:start -->

# [LCP 68. 美观的花束](https://leetcode.cn/problems/1GxJYY)

## 题目描述

<!-- description:start -->

力扣嘉年华的花店中从左至右摆放了一排鲜花，记录于整型一维矩阵 `flowers` 中每个数字表示该位置所种鲜花的品种编号。你可以选择一段区间的鲜花做成插花，且不能丢弃。
在你选择的插花中，如果每一品种的鲜花数量都不超过 `cnt` 朵，那么我们认为这束插花是 「美观的」。

> -   例如：`[5,5,5,6,6]` 中品种为 `5` 的花有 `3` 朵， 品种为 `6` 的花有 `2` 朵，**每一品种** 的数量均不超过 `3`

请返回在这一排鲜花中，共有多少种可选择的区间，使得插花是「美观的」。

**注意：**

-   答案需要以 `1e9 + 7 (1000000007)` 为底取模，如：计算初始结果为：`1000000008`，请返回 `1`

**示例 1：**

> 输入：`flowers = [1,2,3,2], cnt = 1`
>
> 输出：`8`
>
> 解释：相同的鲜花不超过 `1` 朵，共有 `8` 种花束是美观的；
> 长度为 `1` 的区间 `[1]、[2]、[3]、[2]` 均满足条件，共 `4` 种可选择区间
> 长度为 `2` 的区间 `[1,2]、[2,3]、[3,2]` 均满足条件，共 `3` 种可选择区间
> 长度为 `3` 的区间 `[1,2,3]` 满足条件，共 `1` 种可选择区间。
> 区间 `[2,3,2],[1,2,3,2]` 都包含了 `2` 朵鲜花 `2` ，不满足条件。
> 返回总数 `4+3+1 = 8`

**示例 2：**

> 输入：`flowers = [5,3,3,3], cnt = 2`
>
> 输出：`8`

**提示：**

-   `1 <= flowers.length <= 10^5`
-   `1 <= flowers[i] <= 10^5`
-   `1 <= cnt <= 10^5`

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：双指针

我们用双指针 $j$ 和 $i$ 分别指向当前窗口的左右端点，用数组或哈希表 $d$ 记录当前窗口内的元素以及出现的次数。

遍历数组 $flowers$，每一次我们将 $flowers[i]$ 加入到窗口中，即 $d[flowers[i]]++$，然后判断 $d[flowers[i]]$ 是否大于 $cnt$，如果大于 $cnt$，则我们需要将 $flowers[j]$ 从窗口中移除，即 $d[flowers[j]]--$，并将 $j$ 右移，直到 $d[flowers[i]] \leq cnt$。此时窗口内的元素都不超过 $cnt$ 个，因此我们可以将 $i - j + 1$ 加到答案中。

最后返回答案即可。

时间复杂度 $O(n)$，空间复杂度 $O(m)$。其中 $n$ 和 $m$ 分别为数组 $flowers$ 的长度以及数组 $flowers$ 中的最大值。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def beautifulBouquet(self, flowers: List[int], cnt: int) -> int:
        mod = 10**9 + 7
        d = Counter()
        ans = j = 0
        for i, x in enumerate(flowers):
            d[x] += 1
            while d[x] > cnt:
                d[flowers[j]] -= 1
                j += 1
            ans = (ans + i - j + 1) % mod
        return ans
```

#### Java

```java
class Solution {
    public int beautifulBouquet(int[] flowers, int cnt) {
        int mx = 0;
        for (int x : flowers) {
            mx = Math.max(mx, x);
        }
        int[] d = new int[mx + 1];
        long ans = 0;
        final int mod = (int) 1e9 + 7;
        for (int i = 0, j = 0; i < flowers.length; ++i) {
            ++d[flowers[i]];
            while (d[flowers[i]] > cnt) {
                --d[flowers[j++]];
            }
            ans = (ans + i - j + 1) % mod;
        }
        return (int) ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int beautifulBouquet(vector<int>& flowers, int cnt) {
        int mx = *max_element(flowers.begin(), flowers.end());
        int d[mx + 1];
        memset(d, 0, sizeof(d));
        long long ans = 0;
        const int mod = 1e9 + 7;
        for (int i = 0, j = 0; i < flowers.size(); ++i) {
            ++d[flowers[i]];
            while (d[flowers[i]] > cnt) {
                --d[flowers[j++]];
            }
            ans = (ans + i - j + 1) % mod;
        }
        return ans;
    }
};
```

#### Go

```go
func beautifulBouquet(flowers []int, cnt int) (ans int) {
	mx := slices.Max(flowers)
	d := make([]int, mx+1)
	j := 0
	const mod = 1e9 + 7
	for i, x := range flowers {
		d[x]++
		for d[x] > cnt {
			d[flowers[j]]--
			j++
		}
		ans = (ans + i - j + 1) % mod
	}
	return
}
```

#### Swift

```swift
class Solution {
    func beautifulBouquet(_ flowers: [Int], _ cnt: Int) -> Int {
        let mod = Int(1e9 + 7)
        var maxFlower = 0
        for flower in flowers {
            maxFlower = max(maxFlower, flower)
        }

        var flowerCount = [Int](repeating: 0, count: maxFlower + 1)
        var ans = 0
        var j = 0

        for i in 0..<flowers.count {
            flowerCount[flowers[i]] += 1

            while flowerCount[flowers[i]] > cnt {
                flowerCount[flowers[j]] -= 1
                j += 1
            }

            ans = (ans + (i - j + 1)) % mod
        }

        return ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
