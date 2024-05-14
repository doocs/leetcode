# [3005. 最大频率元素计数](https://leetcode.cn/problems/count-elements-with-maximum-frequency)

[English Version](/solution/3000-3099/3005.Count%20Elements%20With%20Maximum%20Frequency/README_EN.md)

<!-- tags:数组,哈希表,计数 -->

<!-- difficulty:简单 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个由 <strong>正整数 </strong>组成的数组 <code>nums</code> 。</p>

<p>返回数组 <code>nums</code> 中所有具有 <strong>最大 </strong>频率的元素的 <strong>总频率 </strong>。</p>

<p>元素的 <strong>频率 </strong>是指该元素在数组中出现的次数。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,2,3,1,4]
<strong>输出：</strong>4
<strong>解释：</strong>元素 1 和 2 的频率为 2 ，是数组中的最大频率。
因此具有最大频率的元素在数组中的数量是 4 。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,3,4,5]
<strong>输出：</strong>5
<strong>解释：</strong>数组中的所有元素的频率都为 1 ，是最大频率。
因此具有最大频率的元素在数组中的数量是 5 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 100</code></li>
</ul>

## 解法

### 方法一：计数

我们可以用一个哈希表或数组 $cnt$ 记录每个元素出现的次数。

然后我们遍历 $cnt$，找到出现次数最多的元素，记其出现次数为 $mx$，累加出现次数等于 $mx$ 的元素的出现次数，即为答案。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $nums$ 的长度。

<!-- tabs:start -->

```python
class Solution:
    def maxFrequencyElements(self, nums: List[int]) -> int:
        cnt = Counter(nums)
        mx = max(cnt.values())
        return sum(x for x in cnt.values() if x == mx)
```

```java
class Solution {
    public int maxFrequencyElements(int[] nums) {
        int[] cnt = new int[101];
        for (int x : nums) {
            ++cnt[x];
        }
        int ans = 0, mx = -1;
        for (int x : cnt) {
            if (mx < x) {
                mx = x;
                ans = x;
            } else if (mx == x) {
                ans += x;
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int maxFrequencyElements(vector<int>& nums) {
        int cnt[101]{};
        for (int x : nums) {
            ++cnt[x];
        }
        int ans = 0, mx = -1;
        for (int x : cnt) {
            if (mx < x) {
                mx = x;
                ans = x;
            } else if (mx == x) {
                ans += x;
            }
        }
        return ans;
    }
};
```

```go
func maxFrequencyElements(nums []int) (ans int) {
	cnt := [101]int{}
	for _, x := range nums {
		cnt[x]++
	}
	mx := -1
	for _, x := range cnt {
		if mx < x {
			mx, ans = x, x
		} else if mx == x {
			ans += x
		}
	}
	return
}
```

```ts
function maxFrequencyElements(nums: number[]): number {
    const cnt: number[] = Array(101).fill(0);
    for (const x of nums) {
        ++cnt[x];
    }
    let [ans, mx] = [0, -1];
    for (const x of cnt) {
        if (mx < x) {
            mx = x;
            ans = x;
        } else if (mx === x) {
            ans += x;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
