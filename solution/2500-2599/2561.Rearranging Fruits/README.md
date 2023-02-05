# [2561. 重排水果](https://leetcode.cn/problems/rearranging-fruits)

[English Version](/solution/2500-2599/2561.Rearranging%20Fruits/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>你有两个果篮，每个果篮中有 <code>n</code> 个水果。给你两个下标从 <strong>0</strong> 开始的整数数组 <code>basket1</code> 和 <code>basket2</code> ，用以表示两个果篮中每个水果的成本。</p>

<p>你希望两个果篮相等。为此，可以根据需要多次执行下述操作：</p>

<ul>
	<li>选中两个下标 <code>i</code> 和 <code>j</code> ，并交换 <code>basket1</code> 中的第 <code>i</code> 个水果和 <code>basket2</code> 中的第 <code>j</code> 个水果。</li>
	<li>交换的成本是 <code>min(basket1<sub>i</sub>,basket2<sub>j</sub>)</code> 。</li>
</ul>

<p>根据果篮中水果的成本进行排序，如果排序后结果完全相同，则认为两个果篮相等。</p>

<p>返回使两个果篮相等的最小交换成本，如果无法使两个果篮相等，则返回<em> </em><code>-1</code><em> </em>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>basket1 = [4,2,2,2], basket2 = [1,4,1,2]
<strong>输出：</strong>1
<strong>解释：</strong>交换 basket1 中下标为 1 的水果和 basket2 中下标为 0 的水果，交换的成本为 1 。此时，basket1 = [4,1,2,2] 且 basket2 = [2,4,1,2] 。重排两个数组，发现二者相等。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>basket1 = [2,3,4,1], basket2 = [3,2,5,1]
<strong>输出：</strong>-1
<strong>解释：</strong>可以证明无法使两个果篮相等。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>basket1.length == bakste2.length</code></li>
	<li><code>1 &lt;= basket1.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= basket1<sub>i</sub>,basket2<sub>i</sub> &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：贪心 + 构造**

我们可以先将两个数组中共有的元素去掉，对于剩下的每个数字，其出现的次数必须为偶数，否则无法构造出相同的数组。不妨记去除共有元素后，两数组分别为 $a$ 和 $b$。

接下来，我们考虑如何进行交换。

如果我们要交换 $a$ 中最小的数，那么我们要在 $b$ 中找到最大的数，与其进行交换；同理，如果我们要交换 $b$ 中最小的数，那么我们要在 $a$ 中找到最大的数，与其进行交换。可以通过排序来实现。

不过，还有另一种交换的方案，我们可以借助一个最小的数 $mi$ 作为过渡元素，将 $a$ 中的数先与 $mi$ 进行交换，再将 $mi$ 与 $b$ 中的数进行交换。这样，交换的成本为 $2 \times mi$。

在代码实现上，我们可以直接将数组 $a$ 和 $b$ 合并成数组 $nums$，然后对数组 $nums$ 进行排序。接下来枚举前一半的数，计算每次的最小成本，累加到答案中即可。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 为数组长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minCost(self, basket1: List[int], basket2: List[int]) -> int:
        cnt = Counter()
        for a, b in zip(basket1, basket2):
            cnt[a] += 1
            cnt[b] -= 1
        mi = min(cnt)
        nums = []
        for x, v in cnt.items():
            if v % 2:
                return -1
            nums.extend([x] * (abs(v) // 2))
        nums.sort()
        m = len(nums) // 2
        return sum(min(x, mi * 2) for x in nums[:m])
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public long minCost(int[] basket1, int[] basket2) {
        int n = basket1.length;
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            cnt.merge(basket1[i], 1, Integer::sum);
            cnt.merge(basket2[i], -1, Integer::sum);
        }
        int mi = 1 << 30;
        List<Integer> nums = new ArrayList<>();
        for (var e : cnt.entrySet()) {
            int x = e.getKey(), v = e.getValue();
            if (v % 2 != 0) {
                return -1;
            }
            for (int i = Math.abs(v) / 2; i > 0; --i) {
                nums.add(x);
            }
            mi = Math.min(mi, x);
        }
        Collections.sort(nums);
        int m = nums.size();
        long ans = 0;
        for (int i = 0; i < m / 2; ++i) {
            ans += Math.min(nums.get(i), mi * 2);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    long long minCost(vector<int>& basket1, vector<int>& basket2) {
        int n = basket1.size();
        unordered_map<int, int> cnt;
        for (int i = 0; i < n; ++i) {
            cnt[basket1[i]]++;
            cnt[basket2[i]]--;
        }
        int mi = 1 << 30;
        vector<int> nums;
        for (auto& [x, v] : cnt) {
            if (v % 2) {
                return -1;
            }
            for (int i = abs(v) / 2; i; --i) {
                nums.push_back(x);
            }
            mi = min(mi, x);
        }
        sort(nums.begin(), nums.end());
        int m = nums.size();
        long long ans = 0;
        for (int i = 0; i < m / 2; ++i) {
            ans += min(nums[i], mi * 2);
        }
        return ans;
    }
};
```

### **Go**

```go
func minCost(basket1 []int, basket2 []int) (ans int64) {
	cnt := map[int]int{}
	for i, a := range basket1 {
		cnt[a]++
		cnt[basket2[i]]--
	}
	mi := 1 << 30
	nums := []int{}
	for x, v := range cnt {
		if v%2 != 0 {
			return -1
		}
		for i := abs(v) / 2; i > 0; i-- {
			nums = append(nums, x)
		}
		mi = min(mi, x)
	}
	sort.Ints(nums)
	m := len(nums)
	for i := 0; i < m/2; i++ {
		ans += int64(min(nums[i], mi*2))
	}
	return
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
