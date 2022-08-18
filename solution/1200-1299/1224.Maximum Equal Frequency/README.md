# [1224. 最大相等频率](https://leetcode.cn/problems/maximum-equal-frequency)

[English Version](/solution/1200-1299/1224.Maximum%20Equal%20Frequency/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个正整数数组&nbsp;<code>nums</code>，请你帮忙从该数组中找出能满足下面要求的 <strong>最长</strong> 前缀，并返回该前缀的长度：</p>

<ul>
	<li>从前缀中 <strong>恰好删除一个</strong> 元素后，剩下每个数字的出现次数都相同。</li>
</ul>

<p>如果删除这个元素后没有剩余元素存在，仍可认为每个数字都具有相同的出现次数（也就是 0 次）。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [2,2,1,1,5,3,3,5]
<strong>输出：</strong>7
<strong>解释：</strong>对于长度为 7 的子数组 [2,2,1,1,5,3,3]，如果我们从中删去 nums[4] = 5，就可以得到 [2,2,1,1,3,3]，里面每个数字都出现了两次。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,1,1,2,2,2,3,3,3,4,4,4,5]
<strong>输出：</strong>13
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：数组/哈希表**

用 $cnt$ 记录 $nums$ 中每个元素 $v$ 出现的次数，而 $ccnt$ 记录每个次数出现的次数，元素出现的最大次数用 $mx$ 表示。

遍历 $nums$：

-   若最大次数 $mx=1$，说明当前前缀中每个数字均出现 $1$ 次，删除任意一个后，都满足剩余数字次数相同；
-   若所有数字出现的次数均为 $mx$ 和 $mx-1$，并且只有一个数字的出现次数为 $mx$，那么我们删除出现 $mx$ 次数的一个数字，剩余数字次数均为 $mx-1$，满足条件；
-   若除了一个数字，其它所有数字出现次数均为 $mx$ 次，那么我们删除出现一次的数字，剩余数字次数均为 $mx$，满足条件。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 表示 $nums$ 数组的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maxEqualFreq(self, nums: List[int]) -> int:
        cnt = Counter()
        ccnt = Counter()
        ans = mx = 0
        for i, v in enumerate(nums, 1):
            if v in cnt:
                ccnt[cnt[v]] -= 1
            cnt[v] += 1
            mx = max(mx, cnt[v])
            ccnt[cnt[v]] += 1
            if mx == 1:
                ans = i
            elif ccnt[mx] * mx + ccnt[mx - 1] * (mx - 1) == i and ccnt[mx] == 1:
                ans = i
            elif ccnt[mx] * mx + 1 == i and ccnt[1] == 1:
                ans = i
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private static int[] cnt = new int[100010];
    private static int[] ccnt = new int[100010];

    public int maxEqualFreq(int[] nums) {
        Arrays.fill(cnt, 0);
        Arrays.fill(ccnt, 0);
        int ans = 0;
        int mx = 0;
        for (int i = 1; i <= nums.length; ++i) {
            int v = nums[i - 1];
            if (cnt[v] > 0) {
                --ccnt[cnt[v]];
            }
            ++cnt[v];
            mx = Math.max(mx, cnt[v]);
            ++ccnt[cnt[v]];
            if (mx == 1) {
                ans = i;
            } else if (ccnt[mx] * mx + ccnt[mx - 1] * (mx - 1) == i && ccnt[mx] == 1) {
                ans = i;
            } else if (ccnt[mx] * mx + 1 == i && ccnt[1] == 1) {
                ans = i;
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maxEqualFreq(vector<int>& nums) {
        unordered_map<int, int> cnt;
        unordered_map<int, int> ccnt;
        int ans = 0, mx = 0;
        for (int i = 1; i <= nums.size(); ++i) {
            int v = nums[i - 1];
            if (cnt[v]) --ccnt[cnt[v]];
            ++cnt[v];
            mx = max(mx, cnt[v]);
            ++ccnt[cnt[v]];
            if (mx == 1)
                ans = i;
            else if (ccnt[mx] * mx + ccnt[mx - 1] * (mx - 1) == i && ccnt[mx] == 1)
                ans = i;
            else if (ccnt[mx] * mx + 1 == i && ccnt[1] == 1)
                ans = i;
        }
        return ans;
    }
};
```

### **Go**

```go
func maxEqualFreq(nums []int) int {
	cnt := map[int]int{}
	ccnt := map[int]int{}
	ans, mx := 0, 0
	for i, v := range nums {
		i++
		if cnt[v] > 0 {
			ccnt[cnt[v]]--
		}
		cnt[v]++
		mx = max(mx, cnt[v])
		ccnt[cnt[v]]++
		if mx == 1 {
			ans = i
		} else if ccnt[mx]*mx+ccnt[mx-1]*(mx-1) == i && ccnt[mx] == 1 {
			ans = i
		} else if ccnt[mx]*mx+1 == i && ccnt[1] == 1 {
			ans = i
		}
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **TypeScript**

```ts
function maxEqualFreq(nums: number[]): number {
    const n = nums.length;
    const map = new Map();
    for (const num of nums) {
        map.set(num, (map.get(num) ?? 0) + 1);
    }

    for (let i = n - 1; i > 0; i--) {
        for (const k of map.keys()) {
            map.set(k, map.get(k) - 1);
            let num = 0;
            for (const v of map.values()) {
                if (v !== 0) {
                    num = v;
                    break;
                }
            }
            let isOk = true;
            let sum = 1;
            for (const v of map.values()) {
                if (v !== 0 && v !== num) {
                    isOk = false;
                    break;
                }
                sum += v;
            }
            if (isOk) {
                return sum;
            }
            map.set(k, map.get(k) + 1);
        }
        map.set(nums[i], map.get(nums[i]) - 1);
    }
    return 1;
}
```

### **...**

```

```

<!-- tabs:end -->
